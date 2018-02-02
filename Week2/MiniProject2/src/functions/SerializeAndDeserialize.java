package functions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import beans.User;

public class SerializeAndDeserialize {
	
	private static FileInputStream fis;
	private static ObjectInputStream ois;
	private static FileOutputStream fos;
	private static ObjectOutputStream oos;
		
	final static Logger logger = Logger.getLogger(SerializeAndDeserialize.class);
	
	//Loads from file into memory
	public static List<User> deserializeUsers(String outputfile) {
		logger.debug("Attempting to deserialize users");
		List<User> users = new ArrayList<>();
		try {
			fis = new FileInputStream(outputfile);
			ois = new ObjectInputStream(fis);
			
			users = (List<User>) ois.readObject();

			logger.debug("Deserialized successfully");
			
		} catch (FileNotFoundException e) {
			logger.debug("FileNotFoundException");
			logger.debug(e.getStackTrace());
			e.printStackTrace();
			System.out.println("File not found");
		} catch (IOException e) {
			logger.debug("IOException");
			logger.debug(e.getStackTrace());
			e.printStackTrace();
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			logger.debug("ClassNotFoundException");
			logger.debug(e.getStackTrace());
			e.printStackTrace();
		}
		finally {
			try {
				if (ois != null) {
					ois.close();				
				}
				if (fis != null) {
					fis.close();				
				}				
			}
			catch(IOException e) {
				logger.debug("IOException");
				logger.debug(e.getStackTrace());
				e.printStackTrace();
			}
	
		}
		return users;
	}
	
	//Writes from memory into file
	public static void serializeUsers(List<User> users, String inputfile) {
		logger.debug("Attempting to serialize users");
		try {
		FileOutputStream fos = new FileOutputStream(inputfile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(users);				

		logger.debug("Successfully serialized users");
		} catch (FileNotFoundException e) {
			logger.debug("FileNotFoundException");
			logger.debug(e.getStackTrace());
			System.out.println("File not found");
			e.printStackTrace();
		} catch (IOException e) {
			logger.debug("IOException");
			logger.debug(e.getStackTrace());
			System.out.println("Error initializing stream");
			e.printStackTrace();
		}
		finally {
			try {
				if (oos != null) {
					oos.close();
				}
				if (fos != null) {
					fos.close();
				}				
			}
			catch (IOException e) {
				logger.debug("IOException");
				logger.debug(e.getStackTrace());
				e.printStackTrace();
			}

		}
	}
	
}
