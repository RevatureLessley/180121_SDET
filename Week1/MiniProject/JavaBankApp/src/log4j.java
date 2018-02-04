import org.apache.log4j.*;

public class log4j {

	final static Logger logger = Logger.getLogger(MainMenu.class);
	
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		logger.debug("This is debug");
		logger.info("This is Info");
		logger.warn("This is Warn");
		logger.error("This is Error");
		logger.fatal("This is Fatal");
		

	}

}
