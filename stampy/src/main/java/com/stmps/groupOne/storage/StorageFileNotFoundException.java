package com.stmps.groupOne.storage;

public class StorageFileNotFoundException extends StorageException {
	private static final long serialVersionUID = 5204452225951872771L;

	public StorageFileNotFoundException(String message) {
		super(message);
	}

	public StorageFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}