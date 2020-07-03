package org.happy.artist.rmdmia.rcsm.provider.message;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale; 
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.DocumentationTool;
import javax.tools.DocumentationTool.DocumentationTask;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.happy.artist.file.utils.DeleteDirectory;
import org.happy.artist.file.utils.WriteFile;

/**
 * MessageCompiler - Compiler for dynamically generated Java source code. 
 *
 * @author Happy Artist
 * Copyright 2014-2020 Happy Artist. All rights reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Happy Artist &lt;aaronsims2020@gmail.com&gt;, 2014-2020 July
 */
public class MessageCompiler
{
    
    /** The Constant logger. */
    // Class Logger define & instantiation
    private final static Logger logger = Logger.getLogger(MessageCompiler.class.getName()); 
    
    /**
     * The listener interface for receiving messageDiagnostic events.
     * The class that is interested in processing a messageDiagnostic
     * event implements this interface, and the object created
     * with that class is registered with a component using the
     * component's <code>addMessageDiagnosticListener<code> method. When
     * the messageDiagnostic event occurs, that object's appropriate
     * method is invoked.
     *
     * @see MessageDiagnosticEvent
     */
    private static class MessageDiagnosticListener implements DiagnosticListener<JavaFileObject>
    {
        
        /**
         * Report.
         *
         * @param diagnostic the diagnostic
         */
        @Override
        public void report(Diagnostic<? extends JavaFileObject> diagnostic)
        {
            final StringBuilder sb=new StringBuilder();
            sb.append("Line Number->");
            sb.append(diagnostic.getLineNumber());
            sb.append("\ncode->");
            sb.append(diagnostic.getCode());
            sb.append("\nMessage->");
            sb.append(diagnostic.getMessage(Locale.ENGLISH));
            sb.append("\nLine Number->");
            sb.append(diagnostic.getLineNumber());
            sb.append("\nSource->");
            sb.append(diagnostic.getSource());
            sb.append("\n ");
            logger.log(Level.WARNING, sb.toString()); 
        }
    }
 
    /** Java source file is in memory to avoid putting source file on hard disk. */
    public static class DynamicSourceCodeObject extends SimpleJavaFileObject
    {
        
        /** The contents. */
        public String contents = null;
 
        /**
         * Instantiates a new dynamic source code object.
         *
         * @param class_name the class name
         * @param contents the contents
         * @throws Exception the exception
         */
        public DynamicSourceCodeObject(String class_name, String contents) throws Exception
        {
            super(URI.create("string:///" + class_name.replace('.', '/')
                             + Kind.SOURCE.extension), Kind.SOURCE);
            this.contents = contents;
        }
 
        /**
         * Gets the char content.
         *
         * @param ignoreEncodingErrors the ignore encoding errors
         * @return the char content
         * @throws IOException Signals that an I/O exception has occurred.
         */
        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors)
                throws IOException
        {
            return contents;
        }
    }

    /**
     *  Write Java SRC to Files.
     *
     * @param files the files
     * @param srcOutputFolder the src output folder
     * @param removeExistingSRCDirIfExists the remove existing SRC dir if exists
     * @throws IOException Signals that an I/O exception has occurred.
     */
 	public static void writeSRCFiles(List<MessageCompiler.DynamicSourceCodeObject> files, String srcOutputFolder, boolean removeExistingSRCDirIfExists) throws IOException
     {
 		File srcFolder = new File(srcOutputFolder);
 		if(srcOutputFolder.isEmpty())
 		{
 			return;
 		}
 		
 		if(removeExistingSRCDirIfExists)
 		{
 			// Remove source directory if exists
 			DeleteDirectory.deleteDirectory(srcFolder.toPath());
 		}
 		// Write source code files, could probably be more efficient if writing multiple files at same time.
 		for(int i=0;i<files.size();i++)
 		{
 			WriteFile.writeCharSequenceToUTF8File(files.get(i).contents, srcFolder.getCanonicalPath().concat("/").concat(files.get(i).toUri().getPath()));
 		}
     }

 	/**
	  *  Generate Java SRC Jar file. 
	  *
	  * @param files the files
	  * @param jarsOutputFolder the jars output folder
	  * @param removeExistingJARFileIfExists the remove existing JAR file if exists
	  * @param jarFileName the jar file name
	  * @throws IOException Signals that an I/O exception has occurred.
	  */
 	public static void generateSRCJAR(List<MessageCompiler.DynamicSourceCodeObject> files, String jarsOutputFolder, boolean removeExistingJARFileIfExists, String jarFileName) throws IOException
 	{
        Map<String, String> env = new HashMap<>(); 
        env.put("create", "true");
        // locate file system by using the syntax 
        // defined in java.net.JarURLConnection
        File jarFolder = new File(jarsOutputFolder);
        if(jarFolder.exists()==false)
        {
        	jarFolder.mkdirs();
        }

        URI uri = URI.create("jar:".concat(jarFolder.toURI().toString()).concat(jarFileName));

        try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
        	for(int i =0;i<files.size();i++)
        	{
	            Path pathInZipfile = zipfs.getPath(files.get(i).toUri().getPath());
	    		ByteBuffer byteBuff = StandardCharsets.UTF_8.encode(files.get(i).contents.toString());
	    		String utf8String = new String(byteBuff.array(), StandardCharsets.UTF_8);
	            InputStream inputStream = new ByteArrayInputStream(utf8String.getBytes(StandardCharsets.UTF_8));
	            
	            // copy a file into the zip file
	            if(Files.exists(pathInZipfile.getParent(), LinkOption.NOFOLLOW_LINKS)==false)
	            {
	            	Files.createDirectories(pathInZipfile.getParent(), null);
	            }
	            Files.copy(inputStream, pathInZipfile, StandardCopyOption.REPLACE_EXISTING ); 
        	}
        }  			
 	}

 	/**
	  *  Generate Java classes Jar file. 
	  *
	  * @param files the files
	  * @param classesOutputFolder the classes output folder
	  * @param jarsOutputFolder the jars output folder
	  * @param removeExistingJARFileIfExists the remove existing JAR file if exists
	  * @param jarFileName the jar file name
	  * @throws IOException Signals that an I/O exception has occurred.
	  */
 	public static void generateClassesJAR(List<MessageCompiler.DynamicSourceCodeObject> files, String classesOutputFolder, String jarsOutputFolder, boolean removeExistingJARFileIfExists, String jarFileName) throws IOException
 	{
        Map<String, String> env = new HashMap<>(); 
        env.put("create", "true");
        // locate file system by using the syntax 
        // defined in java.net.JarURLConnection
        File jarFolder = new File(jarsOutputFolder);
        if(jarFolder.exists()==false)
        {
        	jarFolder.mkdirs();
        }

        URI uri = URI.create("jar:".concat(jarFolder.toURI().toString()).concat(jarFileName));

        try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
        	for(int i =0;i<files.size();i++)
        	{
        		Path pathExternalDirectory = new File(classesOutputFolder).toPath();
	            Path pathInZipfile = zipfs.getPath(files.get(i).toUri().getPath());
	            
	            // copy a file into the zip file
	            if(Files.exists(pathInZipfile.getParent(), LinkOption.NOFOLLOW_LINKS)==false)
	            {
	            	Files.createDirectories(pathInZipfile.getParent(), null);
	            }	            
	            Files.copy(pathExternalDirectory, pathInZipfile, StandardCopyOption.REPLACE_EXISTING ); 
        	}
        }  			
 	} 	
 	
 	/**
	  *  Generate Javadocs Jar file. 
	  *
	  * @param files the files
	  * @param docsOutputFolder the docs output folder
	  * @param jarsOutputFolder the jars output folder
	  * @param removeExistingJARFileIfExists the remove existing JAR file if exists
	  * @param jarFileName the jar file name
	  * @throws IOException Signals that an I/O exception has occurred.
	  */
 	public static void generateDocsJAR(List<MessageCompiler.DynamicSourceCodeObject> files, String docsOutputFolder, String jarsOutputFolder, boolean removeExistingJARFileIfExists, String jarFileName) throws IOException
 	{
        Map<String, String> env = new HashMap<>(); 
        env.put("create", "true");
        // locate file system by using the syntax 
        // defined in java.net.JarURLConnection
        File jarFolder = new File(jarsOutputFolder);
        if(jarFolder.exists()==false)
        {
        	jarFolder.mkdirs();
        }

        URI uri = URI.create("jar:".concat(jarFolder.toURI().toString()).concat(jarFileName));

        try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
        	for(int i =0;i<files.size();i++)
        	{
        		Path pathExternalDirectory = new File(docsOutputFolder).toPath();
	            Path pathInZipfile = zipfs.getPath(files.get(i).toUri().getPath());
	            
	            // copy a file into the zip file
	            if(Files.exists(pathInZipfile.getParent(), LinkOption.NOFOLLOW_LINKS)==false)
	            {
	            	Files.createDirectories(pathInZipfile.getParent(), null);
	            }	            
	            Files.copy(pathExternalDirectory, pathInZipfile, StandardCopyOption.REPLACE_EXISTING ); 
        	}
        }  	 		
 	} 	 	
 	
    /**
     *  Return a DynamicSourceCodeObject array with no duplicates. Duplicates are not allowed by the compiler.
     *
     * @param dynamicSourceCodeObjectList the dynamic source code object list
     * @return the non duplicated source code object array
     */
    private static MessageCompiler.DynamicSourceCodeObject[] getNonDuplicatedSourceCodeObjectArray(List<MessageCompiler.DynamicSourceCodeObject> dynamicSourceCodeObjectList)
    {
        final Map<String, MessageCompiler.DynamicSourceCodeObject> linkedHashMap = new LinkedHashMap<String, MessageCompiler.DynamicSourceCodeObject>();
        final Iterator itr = dynamicSourceCodeObjectList.iterator();
        while(itr.hasNext()) 
        {
            MessageCompiler.DynamicSourceCodeObject element = (MessageCompiler.DynamicSourceCodeObject)itr.next();
            linkedHashMap.put(element.contents, element);
        }
        final Collection<MessageCompiler.DynamicSourceCodeObject> values = linkedHashMap.values();
        final MessageCompiler.DynamicSourceCodeObject[] dynamic_source_code_object_array = new MessageCompiler.DynamicSourceCodeObject[values.size()];
        return values.toArray(dynamic_source_code_object_array);
    }    
    
    
    
    /**
     *  Compile Java Source. Input parameter elements in dynamic_source_code_object_array.
     *
     * @param dynamic_source_code_object_list the dynamic source code object list
     * @param class_output_folder the class output folder
     * @return true, if successful
     * @throws Exception the exception
     */
    public static boolean compile(List<MessageCompiler.DynamicSourceCodeObject> dynamic_source_code_object_list, String class_output_folder) throws Exception
    {
        final MessageCompiler.DynamicSourceCodeObject[] dynamic_source_code_object_array=getNonDuplicatedSourceCodeObjectArray(dynamic_source_code_object_list);
        logger.log(Level.FINEST, "Compiling List<MessageCompiler.DynamicSourceCodeObject>: ".concat(Arrays.deepToString(dynamic_source_code_object_array)));
        // If input parameters are null return false.
        if(dynamic_source_code_object_array==null||class_output_folder==null)
        {
            return false;
        }
        
        Iterable<? extends JavaFileObject> files = Arrays.asList(dynamic_source_code_object_array);
        //get system compiler:
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
 
        // for compilation diagnostic message processing on compilation WARNING/ERROR
        MessageDiagnosticListener c = new MessageDiagnosticListener();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(c,Locale.ENGLISH,null);
        // create directories if they do not exist.
        File outDir = new File(class_output_folder);
        if(outDir.exists()==false)
        {
        	outDir.mkdirs();
        }
        //specify classes output folder
        Iterable options = Arrays.asList("-d", class_output_folder);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
                                                             c, options, null,
                                                             files);
        return task.call();
    }      
    
    /**
     *  Compile Java Source. Input parameter elements in dynamic_source_code_object_array.
     *
     * @param dynamic_source_code_object_array the dynamic source code object array
     * @param class_output_folder the class output folder
     * @return true, if successful
     * @throws Exception the exception
     */
    public static boolean compile(JavaFileObject[] dynamic_source_code_object_array, String class_output_folder) throws Exception
    {
        // If input parameters are null return false.
        if(dynamic_source_code_object_array==null||class_output_folder==null)
        {
            return false;
        }
        
        Iterable<? extends JavaFileObject> files = Arrays.asList(dynamic_source_code_object_array);
        //get system compiler:
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
 
        // for compilation diagnostic message processing on compilation WARNING/ERROR
        MessageDiagnosticListener c = new MessageDiagnosticListener();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(c,
                                                                              Locale.ENGLISH,
                                                                              null);
        //specify classes output folder
        Iterable options = Arrays.asList("-d", class_output_folder);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
                                                             c, options, null,
                                                             files);
        return task.call();
    }    
    
    /**
     *  Compile Java Source. Input parameter elements in source_code_object_array, and class_names must match up.
     *
     * @param source_code_object_array the source code object array
     * @param class_names the class names
     * @param class_output_folder the class output folder
     * @return true, if successful
     * @throws Exception the exception
     */
    public static boolean compile(String[] source_code_object_array, String[] class_names, String class_output_folder) throws Exception
    {
        // If input parameters are null return false.
        if(source_code_object_array==null||class_output_folder==null||class_names==null)
        {
            return false;
        }
        // convert source code string array to JavaFileObject array.
        JavaFileObject[] jfo_array=new JavaFileObject[source_code_object_array.length];
        for(int i=0;i<jfo_array.length;i++)
        {
            jfo_array[i]=new DynamicSourceCodeObject(class_names[i],source_code_object_array[i]);
        }
        
        Iterable<? extends JavaFileObject> files = Arrays.asList(jfo_array);
        //get system compiler:
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
 
        // for compilation diagnostic message processing on compilation WARNING/ERROR
        MessageDiagnosticListener c = new MessageDiagnosticListener();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(c,
                                                                              Locale.ENGLISH,
                                                                              null);
        //specify classes output folder
        Iterable options = Arrays.asList("-d", class_output_folder);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
                                                             c, options, null,
                                                             files);
        return task.call();
    }
    
    /**
     *  Compile Java Source. Input parameter elements in source_code_object_array, and class_names must match up.
     *
     * @param source_code the source code
     * @param class_name the class name
     * @param class_output_folder the class output folder
     * @return true, if successful
     * @throws Exception the exception
     */
    public static boolean compile(String source_code, String class_name, String class_output_folder) throws Exception
    {
        // If input parameters are null return false.
        if(source_code==null||class_output_folder==null||class_name==null)  
        {
            return false;
        }
        //System.out.println(source_code);
        // convert source code string array to JavaFileObject array.
        JavaFileObject jfo=new DynamicSourceCodeObject(class_name,source_code);        
        Iterable<? extends JavaFileObject> files = Arrays.asList(jfo);
        //get system compiler:
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
 
        // for compilation diagnostic message processing on compilation WARNING/ERROR
        MessageDiagnosticListener c = new MessageDiagnosticListener();
        StandardJavaFileManager fileManager = null;
        try
        {
        	fileManager = compiler.getStandardFileManager(c,Locale.ENGLISH,null);
        }
        catch(NullPointerException e)
        {
        	System.out.println("Java JDK Required to compile. Change \"java.home\" system property from ".concat(System.getProperty("java.home")).concat(" to a JDK."));
        	e.printStackTrace();
        	throw e;
        }
        //specify classes output folder
        Iterable options = Arrays.asList("-d", class_output_folder, "-cp", System.getProperty("java.class.path").concat(System.getProperty("path.separator")).concat(class_output_folder));


        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
                                                             c, options, null,
                                                             files);
        return task.call();
    }    
 
    /**
     *  generate Java API Documentation. Input parameter elements in dynamic_source_code_object_array.
     *
     * @param dynamic_source_code_object_list the dynamic source code object list
     * @param docs_output_folder the docs output folder
     * @return true, if successful
     * @throws Exception the exception
     */
    public static boolean generateAPIDocs(List<MessageCompiler.DynamicSourceCodeObject> dynamic_source_code_object_list, String docs_output_folder) throws Exception
    {
        final MessageCompiler.DynamicSourceCodeObject[] dynamic_source_code_object_array=getNonDuplicatedSourceCodeObjectArray(dynamic_source_code_object_list);
        logger.log(Level.FINEST, "Generating API Documentation List<MessageCompiler.DynamicSourceCodeObject>: ".concat(Arrays.deepToString(dynamic_source_code_object_array)));
        // If input parameters are null return false.
        if(dynamic_source_code_object_array==null||docs_output_folder==null)
        {
            return false;
        }
        
        Iterable<? extends JavaFileObject> files = Arrays.asList(dynamic_source_code_object_array);
        //get system documentation tool:
        //JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        // for compilation diagnostic message processing on compilation WARNING/ERROR
        MessageDiagnosticListener c = new MessageDiagnosticListener();
        DocumentationTool tool = ToolProvider.getSystemDocumentationTool();
        StandardJavaFileManager fm = tool.getStandardFileManager(c,
                Locale.ENGLISH,
                null);
            File outDir = new File(docs_output_folder);
            if(outDir.exists()==false)
            {
            	outDir.mkdirs();
            }
            fm.setLocation(DocumentationTool.Location.DOCUMENTATION_OUTPUT, Arrays.asList(outDir));
            Iterable options = Arrays.asList("-d", docs_output_folder);
            DocumentationTask task=null;
            try 
            {           
            	task = tool.getTask(null, fm, null, null, null, files);
		    } 
            catch (IllegalArgumentException e) 
            {
		        System.err.println("exception caught as expected: " + e);
		    }          
            return new Boolean(task.call()).booleanValue();      
            
    } 

    /**
     * The main method.
     *
     * @param args the arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception
    {
        // Compile Java source code by String.
        String[] sourceCode = {"class HelloWorld{"
        + "public static void main (String args[]){"
        + "System.out.println (\"Hello world!\");"
        + "}"
        + "}"};
        String[] class_names={"org.test.HelloWorld"};
        // Test Java Compiler
  //      compile(sourceCode,class_names,"./");
        // Compile Java source code by String.
        // Test Java Compiler
        compile(sourceCode[0],class_names[0],"./");        
      }
}