/**
 * 
 */
package com.jobsity.bowling.service.input;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.jobsity.bowling.exception.BowlingCodeException;
import com.jobsity.bowling.exception.BowlingGameException;

/**
 * @author Adolfo Miguel Iglesias
 *
 */
public class FileInputServiceImpl implements InputService {
	

	@Override
	public File getResource(String pathFile) throws BowlingGameException {
		File file = new File(pathFile);
		
		if(!file.exists()) {
			//throw new FileNotFoundException();
			throw new BowlingGameException("The absolute file path is incorrect. "
					+ "Please enter a right absolute file path", 
						BowlingCodeException.NO_FILE.name());
		}
	
		Path path = Paths.get(pathFile);
		if(!Files.isReadable(path)) {
			throw new BowlingGameException("There is no enough permission to read the file. Please change permissions and enter a right absolute file path",
					BowlingCodeException.NO_READABLE.name());
		}
		
		if(Files.isDirectory(path)) {
			throw new BowlingGameException("The path is not file. Please enter a right absolute file path",
					BowlingCodeException.NO_FILE.name()); 
		}
		
		isWriteable(path);
		
		return file;
	}
	
	protected void isWriteable(Path path) throws  BowlingGameException {
	
		if(path.getParent() != null && !Files.isWritable(path.getParent())) {
			throw new BowlingGameException("There is no enough permission to write the score at " 
					+ path.getParent().getFileName() + ". Please change permissions and enter a right absolute file path",BowlingCodeException.NO_WRITTEN.name());
		}		
	}
	
	

}
