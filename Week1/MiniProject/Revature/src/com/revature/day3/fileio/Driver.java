package com.revature.day3.fileio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Java utilizes a entity called streams whenever you are dealing with data going in or out.
 * These streams must be opened and closed. Though java is pretty reliable in handling memory,
 * you should make every effort to close all streams to avoid data leaks. Keep in mind, java
 * closes streams for you, but risks still exist where it might not. 
 * In reagards to reading/writting files, java provides a few diff ways, 
 * 
 * Three categories:
 * 
 * -FileInputStream/FileOutputStream (one byte at a time)
 * 
 * -FileReader/FileWriter (by chars)
 * 
 * -BufferedReader/BufferedWritter (by lines or specified IO source)
 */
public class Driver {
	
	/*
	 * When specifying directories if your 
	 * directory starts with a "/" you are specifying an absolute path. 
	 * 
	 */
	private FileOutputStream fos;
	private FileInputStream fis;
	private FileReader fr;
	private FileWriter fw;
	private String FILE_NAME = "C:/Users/Parth/Desktop";
	
	
	public static void main(String[] args) {

		Driver d = new Driver();
		
		try {
			d.fosExample();
			d.fileWandReadExample();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void fosExample() throws IOException{
		try {
			fos = new FileOutputStream(FILE_NAME);
			char rand;
			for(int i=0; i<4; i++){
				for (int j=0; j<30; j++){
					
					rand = (char)((Math.random()*26) + 'A');
					fos.write((byte)rand);
				}
				fos.write('\n');
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(fos != null){
				fos.close(); // Always close streams.
			}
		}
		
	}
	public void fileWandReadExample() throws IOException{
		try {
			fr = new FileReader(FILE_NAME);
			fw = new FileWriter(FILE_NAME + "_lowerCase");
			
			int in;
			while((in = fr.read())!= -1){
				if(in == 10){
					fw.write((char)(in));
				}else{
					fw.write((char)(in + 32)); // you just change one bit to make it lowercase that is why +32. 
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(fr!=null){
				fr.close();
			}if(fw!=null){
				fw.close();
			}
		}
		
	}
}
