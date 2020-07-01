package org.happy.artist.file.utils;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
/** Method based on response by user Javo in https://stackoverflow.com/questions/20281835/how-to-delete-a-folder-with-files-using-java */
public class DeleteDirectory {
	
	/** Delete the directory and its contents if it exists
	 * 
	 * @param folder NIO Path of folder to delete.
	 */
	public static void deleteDirectory(final Path folder) throws IOException 
	{
		if(folder.toFile().exists())
		{
		    Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
		        @Override
		        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		            Files.delete(file);
		            return FileVisitResult.CONTINUE;
		        }
	
		        @Override
		        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		            if (exc != null) {
		                throw exc;
		            }
		            Files.delete(dir);
		            return FileVisitResult.CONTINUE;
		        }
		    });	
		}
	}
}
