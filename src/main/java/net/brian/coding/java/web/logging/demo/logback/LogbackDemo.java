package net.brian.coding.java.web.logging.demo.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackDemo {
	private final static Logger logger = LoggerFactory.getLogger(LogbackDemo.class);

	public static void main(String[] args) {
		logger.info("Logback succeed with warning! Level:: info.");
		logger.error("Logback failed! Level:: error.");
		logger.debug("Logback succeed! Level:: debug.");
	}
}