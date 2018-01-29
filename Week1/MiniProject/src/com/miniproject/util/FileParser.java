package com.miniproject.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
 * A file used for reading files and parsing the specific input need for a task
 */
public class FileParser {
	public static String parseFile(String fileName, String option) {
		String setting = null;
		FileReader fReader = null;
		BufferedReader bReader = null;
		try {
			File file = new File(fileName);
			
			if(file.exists()) {
				String line = null;
				fReader = new FileReader(file);
				bReader = new BufferedReader(fReader);
				while((line = bReader.readLine()) != null) {
					if(line.contains(option)) {
						int begin = line.indexOf('=');
						setting = line.substring(begin+1);
					}
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fReader.close();
				bReader.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return setting;
	}
}
