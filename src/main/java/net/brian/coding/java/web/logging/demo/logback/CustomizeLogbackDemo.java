package net.brian.coding.java.web.logging.demo.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * slf4j是日志框架的接口，log4j是日志框架的一种实现
 *
 */
public class CustomizeLogbackDemo {
	private final static Logger logger = LoggerFactory.getLogger("monitor");

	public static void main(String[] args) {
		logger.info("Output user.dir:: " + System.getProperty("user.dir"));
		logger.info("Logback succeed with warning! Level:: info.");
		logger.error("Logback failed! Level:: error.");
		logger.debug("Logback succeed! Level:: debug.");
	}
}
