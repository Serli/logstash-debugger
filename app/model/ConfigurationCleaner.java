package model;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ConfigurationCleaner {
	
	private final String NEW_LINE = System.getProperty("line.separator" );
	
	/**
	 * Create a clean configuration file (containing only the filters) and return his absolute path
	 * @param uri
	 * @return the absolute path of the clean configuration file
	 */
	public String createCleanConfigFile(String uri){
		//File cleanConfFile = null;
		String output="";
		try{
			File tempConfFile =writeTempConfFile(uri);
			//cleanConfFile = eraseInputOutput(tempConfFile);
			output = eraseInputOutput(tempConfFile);
		}catch (IOException e){
			System.out.println("Error while getting/creating files during creation of clean configuration file : "+e.getMessage());
		}catch (Exception e){
			System.out.println("Error while creating the clean configuration file : "+e.toString() + e.getStackTrace()[0]);
		}
		//return cleanConfFile.getAbsolutePath();
		return output;
	}
	
	/**create a temporary conf file which copies the conf file
	 * in a right way to parse it later (multilines)
	 * @param uri : the uri to the conf file
	 */
	private File writeTempConfFile(String uri) throws IOException{
		
		Scanner scan = null;
		FileOutputStream writeOnTempFile = null;
		File temp = null;
		
		try{
			//getting the config file
			Path path = Paths.get(uri);
			
			//creating a temporary config file
			temp = createTempConfFile();
			
			//copying the configuration on a right way to parse it later
			scan = new Scanner(path).useDelimiter("}");			
			writeOnTempFile = new FileOutputStream(temp);			
			while (scan.hasNext()){
				String s = scan.next()+"}"+NEW_LINE;
				writeOnTempFile.write(s.getBytes());
			}
		}finally{
			writeOnTempFile.close();
			scan.close();
		}
		return temp;
	}
	
	//create a temp file to host the temporary conf file
	private File createTempConfFile() throws IOException{
		File temp = File.createTempFile("logstach.logbacktemp",".conf");
		temp.deleteOnExit();
		return temp;
	}
	
	/**
	 * erase the input/output of the temp conf file
	 * leaving only the filters for the main program to use
	 */
	private String eraseInputOutput(File confFile) throws IOException{
		
		Scanner scan = null;
		boolean token = false;
		String output="";
		
		try{
			
			scan = new Scanner(confFile);
			/*if the current read line is an input/output block or contained in one
			 * the line is deleted. 
			 * if the current read line is a filter block or contained in one
			 * the line is written on the new configuration file
			 */
			while (scan.hasNext()){
				String s = scan.next();
				if (!token && !(s.contains("input")) && !(s.contains("output")) && !(s.contains("filter"))){
					s+=NEW_LINE;
					output+=s;
				}else if (!token && !(s.contains("input") && !(s.contains("output"))) && s.contains("filter")){
					s+=NEW_LINE;
					output+=s;
				}else if (token && !(s.contains("input") && !(s.contains("output"))) && s.contains("filter")){
					token = false;
					s+=NEW_LINE;
					output+=s;
				}else{
					token = true;				
				}
			}
		}finally{
				scan.close();
		}
		return output;
	}
	
	

}
