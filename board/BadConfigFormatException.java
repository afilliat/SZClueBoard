package board;

public class BadConfigFormatException extends Exception {

	public BadConfigFormatException() {
		super();
	}

	public BadConfigFormatException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return "Invalid file format.";
	}
	
}
