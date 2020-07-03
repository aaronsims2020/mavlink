package org.happy.artist.file.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class WriteFile.
 */
public class WriteFile {
	
	/**
	 *  Write UTF-16 Java String as UTF-8 file. 
	 *
	 * @param chars CharSequence from the MessageCompiler.DynamicSourceCodeObject
	 * @param outputLocation of file to write.
	 * @throws IOException throw when Files or Directory cannot be written.
	 */
	public static void writeCharSequenceToUTF8File(CharSequence chars, String outputLocation) throws IOException
	{
		// convert CharSequence to UTF-8
		ByteBuffer byteBuff = StandardCharsets.UTF_8.encode(chars.toString());
		String utf8String = new String(byteBuff.array(), StandardCharsets.UTF_8);
		File file = new File(outputLocation);
		if(file.exists()==false)
		{
			if(file.getParentFile().exists()==false)
			{
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		
        // If the file doesn't exists, create and write to it. If the file exists, truncate (remove all content) and write to it
        try (FileWriter writer = new FileWriter(outputLocation);BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(utf8String);
        } catch (IOException e) {
            Logger.getLogger(WriteFile.class.getName()).log(Level.SEVERE,null, e);        	
            System.err.format("IOException: %s%n", e);
        }
	}
}
