package org.happy.artist.mavlink.messages;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.happy.artist.mavlink.messages.generator.MAVLinkCommonXMLReader;
import org.happy.artist.rmdmia.rcsm.provider.message.MessageCompiler;

/** Read and process MAVLink Message Definitions from XML to Java SRC code, Classes, and Documentation. If only the URL is written all options will be defaulted enabled src, classes, docs, and jars.
*<br>
* 		<br>Application syntax (enclose output paths in double quotes ""): 
*       <br>MAVLinkMessageToolkit <message definition xml URL> -<options>
*		<br><ol> Options: 
*		<li>     -src "<output path>" (This enables source code file generation)
*		<li>     -classes "<output path>" (This enables class file generation)
*		<li>     -docs "<output path>" (This enables API documentation file generation)
*		<li>     -jars "<output path>" (This enables Jar file generation)
*		</ol><br>
*		<br> Note: including options without output paths will set default output paths of (./src_mavlink-message-definitions,./bin,./docs,./jars)\n");
*		<br>Example (fill in your classpath information): java org.happy.artist.mavlink.messages.MAVLinkMessageToolkit -classes \"./bin\" -docs \"./docs\"");	
*
* @author Happy Artist
* Copyright (C) 2020 Happy Artist - All Rights Reserved
* Unauthorized copying of this file, via any medium is strictly prohibited
* Proprietary and confidential
* Written by Happy Artist &lt;aaronsims2020@gmail.com&gt;, June 2020
*/
public class MAVLinkMessageToolkit {
	
	public static void main(String[] args) {		
		Logger logger = Logger.getLogger(MAVLinkMessageToolkit.class.getName());
		StringBuilder commandParamsMessage = new StringBuilder();
		String urlMAVLinkMessageDefinitionsXML="https://raw.githubusercontent.com/mavlink/mavlink/master/message_definitions/v1.0/common.xml";
		boolean generateSRC=false;
		String srcOutputPath="./src_mavlink-message-definitions";
		boolean generateClasses=false;
		String classesOutputPath="./bin";
		boolean generateAPIDocs=false;
		String docsOutputPath="./docs";
		boolean generateJars=false;
		String jarsOutputPath="./jars";
		//https://raw.githubusercontent.com/mavlink/mavlink/master/message_definitions/v1.0/common.xml -src "c:/src" -classes "c:/classes" -docs "c:/docs" -jars "c:/jars"
		try 
		{
			// Define the command params message
			commandParamsMessage.append("\nIncorrect syntax:\nMAVLinkMessageToolkit <message definition xml URL> -<options>");
			commandParamsMessage.append("\n Options: ");
			commandParamsMessage.append("\n     -src <output path> ");
			commandParamsMessage.append("\n     -classes <output path> ");
			commandParamsMessage.append("\n     -docs <output path> ");
			commandParamsMessage.append("\n     -jars <output path>");
			commandParamsMessage.append("\n Note: including options without output paths will set default output paths of (./src_mavlink-message-definitions,./bin,./docs,./jars)\n");
			commandParamsMessage.append("\nExample (fill in your classpath information): java org.happy.artist.mavlink.messages.MAVLinkMessageToolkit -classes \"./bin\" -docs \"./docs\"");		
			// Validate the URL
			URL url;
			url=null;
			if(args.length==0)
			{ 
		        System.err.println(commandParamsMessage.toString());            							
			}
			else if(args.length==1)
			{
				url = new URL(args[0]);
				urlMAVLinkMessageDefinitionsXML=args[0];
				url=null;
				generateSRC=true;
				generateClasses=true;
				generateAPIDocs=true;
				generateJars=true;
				// call generateMessageDefinitions with only URL, default to all options with default output directories.
				MAVLinkMessageToolkit.generateMessageDefinitions(urlMAVLinkMessageDefinitionsXML, generateSRC, srcOutputPath, generateClasses, classesOutputPath, generateAPIDocs, docsOutputPath, generateJars, jarsOutputPath);
			}
			else
			{
				// process command line options
				url = new URL(args[0]);
				urlMAVLinkMessageDefinitionsXML=args[0];
				url=null;
				// Process the arguments.
				for(int i = 1;i<args.length;i++)
				{
					StringBuilder sb = new StringBuilder();
					if(args[i].equals("-src"))
					{
						generateSRC = true;
						// This element is an outputPath
						for(int j = i;j<args.length;j++)
						{
							if(args.length > j+1&&args[j+1].startsWith("-", 0)==false)
							{							
								sb.append(args[j+1]);
							}
							else
							{
								i=j;
								j=args.length;
							}
						}
						srcOutputPath=sb.toString().replace("\"","");
					}
					else if(args[i].equals("-classes"))
					{
						generateClasses = true;		
						// This element is an outputPath
						for(int j = i;j<args.length;j++)
						{
							if(args.length > j+1&&args[j+1].startsWith("-", 0)==false)
							{							
								sb.append(args[j+1]);
							}
							else
							{
								i=j;
								j=args.length;
							}
						}
						classesOutputPath=sb.toString().replace("\"","");						
						
					}		
					else if(args[i].equals("-docs"))
					{
						generateAPIDocs = true;		
						// This element is an outputPath
						for(int j = i;j<args.length;j++)
						{
							if(args.length > j+1&&args[j+1].startsWith("-", 0)==false)
							{							
								sb.append(args[j+1]);
							}
							else
							{
								i=j;
								j=args.length;
							}
						}
						docsOutputPath=sb.toString().replace("\"","");						
					}	
					else if(args[i].equals("-jars"))
					{
						generateJars = true;
						// This element is an outputPath
						for(int j = i;j<args.length;j++)
						{
							if(args.length > j+1&&args[j+1].startsWith("-", 0)==false)
							{							
								sb.append(args[j+1]);
							}
							else
							{
								i=j;
								j=args.length;
							}
						}
						jarsOutputPath=sb.toString().replace("\"","");						
					}						
				}
				MAVLinkMessageToolkit.generateMessageDefinitions(urlMAVLinkMessageDefinitionsXML, generateSRC, srcOutputPath, generateClasses, classesOutputPath, generateAPIDocs, docsOutputPath, generateJars, jarsOutputPath);
			}
		} 
		catch (MalformedURLException e) 
		{
			logger.log(Level.SEVERE,"Invalid MAVLink Message Definition URL: ".concat(urlMAVLinkMessageDefinitionsXML));			
		}
	}

	// TODO: Implement Jar functionality
	// TODO: Implement Generated Base Class with MAVLink Version, and Dialect
	// TODO: Implement a base class with pointers to all Message Definition Sub classes. Primarily for ease of development, or a template of all current values for GUI Ground Station implementations. 
	
	/**
	 * Generate message definitions.
	 *
	 * @param urlMAVLinkMessageDefinitionsXML The MAVLink message definitions XML URL. On null defaults to: "https://raw.githubusercontent.com/mavlink/mavlink/master/message_definitions/v1.0/common.xml
	 * @param generateSRC The boolean option to generate source code
	 * @param srcOutputPath The source code output path. If null defaults to "./src_mavlink-message-definitions"
	 * @param generateClasses The boolean option to generate classes
	 * @param classesOutputPath The classes output path. If null defaults to "./bin"
	 * @param generateAPIDocs The boolean option to generate API docs
	 * @param docsOutputPath The message definition documentation output path. If null defaults to "./docs"
	 * @param generateJars The boolean option to generate Jar files for SRC, Docs, and Classes.
	 * @param jarsOutputPath The message definition Jars output path. If null defaults to "./jars"
	 * @return true, if successful, if failed there could still be partially generated files created. 
	 */
	public static boolean generateMessageDefinitions(String urlMAVLinkMessageDefinitionsXML, boolean generateSRC, String srcOutputPath, boolean generateClasses, String classesOutputPath, boolean generateAPIDocs, String docsOutputPath, boolean generateJars, String jarsOutputPath)
	{
		Logger logger = Logger.getLogger(MAVLinkMessageToolkit.class.getName());
        List<MessageCompiler.DynamicSourceCodeObject> srcObjects = null;
        File absolutePath;
		boolean success = true;
		boolean srcSuccess = true;
		boolean classesSuccess = true;
		boolean docsSuccess = true;
		
		// set defaults
		if(urlMAVLinkMessageDefinitionsXML==null)
		{
			// set default to "https://raw.githubusercontent.com/mavlink/mavlink/master/message_definitions/v1.0/common.xml" on null
			urlMAVLinkMessageDefinitionsXML = "https://raw.githubusercontent.com/mavlink/mavlink/master/message_definitions/v1.0/common.xml";
		}
		if(srcOutputPath==null)
		{
			srcOutputPath="./src_mavlink-message-definitions";
		}
		if(classesOutputPath==null)
		{
			classesOutputPath="./bin";
		}	
		if(docsOutputPath==null)
		{
			docsOutputPath="./docs";
		}			
        try 
        {		
			// Read MAVLink Message Definitions by URL, and generate and return MessageCompiler.DynamicSourceCodeObject List.
	        System.out.println("Generating MAVLink Common Messages src code...");            
	        srcObjects = MAVLinkCommonXMLReader.parseXML(urlMAVLinkMessageDefinitionsXML);
	        
			// Generate source code if generateSRC is true. The source code (src) is already created, but must be written to files for this to make sense to API user.
			if(generateSRC)
			{
	            absolutePath = new File(srcOutputPath);
	            System.out.println("SRC output directory: ".concat(absolutePath.getCanonicalPath()));            
	            try
	            {
	            	MessageCompiler.writeSRCFiles(srcObjects, srcOutputPath, true);
	            }
	            catch(IOException e)
	            {
	            	srcSuccess=false;
	            	success=false;
	                logger.log(Level.SEVERE, "Failed to write all Java files.", e);
	            }
	            System.out.println("Success: ".concat(String.valueOf(srcSuccess))); 	            
			}
			
			// Generate Classes if generateSRC is true.		
			if(generateClasses)
			{
	            absolutePath = new File(classesOutputPath);
	            System.out.println("Classes output directory: ".concat(absolutePath.getCanonicalPath()));            
	            if(MessageCompiler.compile(srcObjects, classesOutputPath)==false)
	            {
	            	classesSuccess=false;
	            	success=false;
	            }
	            System.out.println("Success: ".concat(String.valueOf(classesSuccess)));            				
			}
			
			// Generate API Documentation if generateAPIDocs is true.		
			if(generateAPIDocs)
			{
	            absolutePath = new File(docsOutputPath);
	            System.out.println("Javadocs output directory: ".concat(absolutePath.getCanonicalPath()));            
	            System.out.println("Generating Javadocs for Java MAVLink Common Messages classes... If you receive an error here, ensure tools.jar from the JDK is in the classpath.");            
	            if(MessageCompiler.generateAPIDocs(srcObjects, docsOutputPath
	            		)==false)
	            {
	            	docsSuccess=false;
	            	success=false;
	            }
	            System.out.println("Javadocs created success: ".concat(String.valueOf(docsSuccess)));  				
			}
			// Generate Jars
			if(generateJars)
			{
		        System.out.println("Jar file generation is not implemented.");            							
			}
        }
        catch(URISyntaxException ex)
        {
            logger.log(Level.SEVERE, "URI Syntax Exception on include", ex);
            success=false;
        }
        catch (MalformedURLException ex) 
        {
            logger.log(Level.SEVERE, "Malformed URL", ex);
            success=false;
        } 
        catch (Exception ex) 
        {
            logger.log(Level.SEVERE, "Compiler Exception", ex);
            success=false;
        }		
		return success;
	}
	
}
