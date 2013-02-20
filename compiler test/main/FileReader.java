package oop.ex2.main;

import java.io.*;

/**
 * File Reader class enables an easy line by line read
 * @author yaelcohen
 */
public class FileReader {
	private DataInputStream inStream;
	private BufferedReader buffer;
	private String currentLine;
	
	/**
	 * file reader constructor
	 * @param path - the file path you want to read from in String format
	 * @throws Exception - throws an exception of there is a problem with reading the file
	 */
	public FileReader(String path) throws Exception{
		try{
			FileInputStream fileStream= new FileInputStream(path);
			inStream = new DataInputStream(fileStream);
			buffer = new BufferedReader(new InputStreamReader(inStream));
		}
		catch (Exception e){
			throw(e);
		}
	}
	
	/**
	 * reads the from the file path line by line
	 * @return line (String) from the file "-1" of EOF
	 * @throws IOException 
	 */
	public String readLine() throws IOException{
		  while ((currentLine = buffer.readLine()) != null)   {
			  return currentLine;
		  }
		  inStream.close();	  
		  return "-1";
	}

}

