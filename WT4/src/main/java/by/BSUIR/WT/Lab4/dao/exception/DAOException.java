package by.BSUIR.WT.Lab4.dao.exception;

public class DAOException extends Exception {

	public DAOException() {}
	
	public DAOException(String message) {
		super(message);
	}
	
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}
}
