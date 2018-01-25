package com.revature.day3.fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Driver {
	/*
	 * Note: When specifying directories, if your directory starts with a
	 * forward slash (/) you are specifying an absolute path. (Meaning you place
	 * the full path starting from the drive) If you do not start the path with
	 * a forward slash, you are using a relative path and it will start
	 * searching from the root project folder (If set to default)
	 */

	private String FILE_NAME = "myFile";
	private FileOutputStream fos;
	private FileInputStream fis;
	private FileWriter fw;
	private FileReader fr;
	private BufferedWriter bw;
	private BufferedReader br;

	/*
	 * Java utilizes a entity called streams whenever you are dealing with data
	 * going in or out. These streams must be opened AND CLOSED. Though java is
	 * pretty reliable in handling memory, you should still make every effort to
	 * close all streams to avoid data leaks. Keep in mind, java still close
	 * streams for you, but risks still exist where it might not. In regards to
	 * reading/writing files, java provides a few different ways, three
	 * categories include: 
	 * -FileInputStream/FileOutputStream
	 * -FileReader/FileWriter 
	 * -BufferedReader/BufferedWriter
	 */
	public static void main(String[] args) {
		Driver d = new Driver();

		try {
			// By default (Hopefully) unless you specify a directory,
			// all files will save in the root project folder.
			d.fosExample();
			d.fisExample();
			d.fileWriterAndFileReaderExample();
			d.BufferedExample();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Execution finishes successfully.");
	}

	/*
	 * FileInput/OutputStreams 
	 * -These streams are able to write data/read data
	 * one BYTE at a time.
	 */
	public void fosExample() throws IOException {
		try {
			fos = new FileOutputStream(FILE_NAME);
			char rand;

			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 30; j++) {
					rand = (char) ((Math.random() * 26) + 'A');
					fos.write((byte) rand);
				}
				fos.write('\n');
//				fos.write('á');
//				fos.write('á');
//				fos.write('á');
//				fos.write('á');
//				fos.write('á');
//				fos.write('\n');
				/*
				 * Since Input/Output stream reads in only bytes, is it limited
				 * to the generic ascii table of characters that you can write.
				 * If you write anything that is greater than 127 in value, you
				 * will overflow into negative space and get weird characters in
				 * return.
				 */
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				/*
				 * ALWAYS CLOSE STREAMS!
				 */
				fos.close();
			}
		}
	}

	public void fisExample() throws IOException{
		try {
			fis = new FileInputStream(FILE_NAME);
			
			byte in;
			
			while((in = (byte)fis.read())!= -1){
				System.out.print((char)in);
			}
			System.out.println();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(fis != null){
				/*
				 * ALWAYS CLOSE STREAMS!
				 */
				fis.close();
			}
		}
		
	}

	/*
	 * FileReader/FileWriters 
	 * -The underlying difference between a
	 * filereader/writer and an input/output stream is that the
	 * filereader/writer reads/writes one character at a time.
	 */

	public void fileWriterAndFileReaderExample() throws IOException{
		try {
			fr = new FileReader(FILE_NAME);
			fw = new FileWriter(FILE_NAME + "_lowerCase");
			
			int in;
			while((in= fr.read()) != -1){
				if(in == 10){
					fw.write((char)(in));
				} else {
					fw.write((char)(in + 32));
					fw.write((char)165);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(fr != null){
				/*
				 * ALWAYS CLOSE STREAMS!
				 */
				fr.close();
			}
			if(fw != null){
				fw.close();
			}
		}		
	}

	/*
	 * Buffered readers/writes
	 * -These classes are especially useful for reading and writing files
	 * since they use a buffer to read and write with. What this means is you
	 * can set the amount of characters you read/write at a time.
	 * By default, you read by entire lines at a time.
	 * Or you can write entire strings at a time.
	 */
	
	public void BufferedExample() throws IOException{
		try {
			br = new BufferedReader(new FileReader(FILE_NAME));
			bw = new BufferedWriter(new FileWriter(FILE_NAME + "_chaos"));
			
			String input = "";
			
			while((input = br.readLine())!=null){
				
				input = input.replace("A", "Hey I found an A over here");
				System.out.println(input);
				input += '\n';
				bw.write(input);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(bw != null){
				/*
				 * ALWAYS CLOSE STREAMS!
				 */
				bw.close();
			}
			if(br != null){
				br.close();
			}
		}			
	}

}