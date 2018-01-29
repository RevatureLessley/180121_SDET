package com.miniproject.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

/*
 * Class used to serialize and deserialize classes
 */
public class Serializer {
	final static Logger logger = Logger.getLogger(Serializer.class);
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	
	public Serializer() {
		
	}
	
	public void serialize(Object o, String fileName) throws IOException {
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fileName));
			oos.writeObject(o);
			logger.info("Write to: " + fileName);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(oos != null) {
				oos.close();
			}
		}
	}
	
	public Object deserialize(String fileName) throws IOException {
		Object o = null;
		try {
			File checkFile = new File(fileName);
			if(checkFile.exists()) {
				ois = new ObjectInputStream(new FileInputStream(fileName));
				o = ois.readObject();
				logger.info("Read from: " + fileName);
			}
		} catch(FileNotFoundException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(ois != null) {
				ois.close();
			}
		}
		
		return o;
	}

}
