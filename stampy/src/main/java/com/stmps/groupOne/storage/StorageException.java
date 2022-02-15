package com.stmps.groupOne.storage;

public class StorageException extends RuntimeException {
	private static final long serialVersionUID = -2493401683574485916L;

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
