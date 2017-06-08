package net.brian.coding.java.core.jdk.exception;

public class ErrorDemo {
	public static void main(String[] args) {
		ErrorDemo demo = new ErrorDemo();
		demo.throwAnOutOfMemoryError();
	}

	public void throwAnOutOfMemoryError() {
		throw new OutOfMemoryError("ErrorDemo -- throwAnOutOfMemoryError:: Throw an OutOfMemoryError here!");
	}

	public void throwAnError() {
		throw new Error("ErrorDemo -- throwAnError:: Throw an error here!");
	}
}
