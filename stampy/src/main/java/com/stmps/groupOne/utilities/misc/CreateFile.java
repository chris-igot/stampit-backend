package com.stmps.groupOne.utilities.misc;

import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors

public class CreateFile {
	public static void text(String content, String filename) {
		try {
			File textFile = new File("/home/ubuntu/"+filename+".txt");
			if(textFile.createNewFile()) {
				FileWriter textWrite = new FileWriter(textFile);
				textWrite.write(content);
				textWrite.close();
			} else {
				FileWriter textWrite = new FileWriter(textFile);
				textWrite.write(content);
				textWrite.close();		
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}