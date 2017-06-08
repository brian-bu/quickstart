package net.brian.coding.java.core.jdk.newfeatures;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptingDemo {
	public static void main(String[] args) {
		ScriptEngineManager script = new ScriptEngineManager();
		ScriptEngine demo = script.getEngineByName("nashorn");
		try {
			demo.eval("function sum(a,b){return a+b;}");
			System.out.println("ScriptingDemo -- main -- execute function:: " + demo.eval("sum(1, 2);"));
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
}
