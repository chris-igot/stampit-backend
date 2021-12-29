package com.stmps.groupOne.utilities.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.stmps.groupOne.models.FileEntry;

public class ImageSerializer extends StdSerializer<FileEntry> {
	private static final long serialVersionUID = 1L;
	static private String imageRoute = "/img/"; 
	static private String stampRoute = "/stamp/";

	public ImageSerializer() {
		this(null);
	}
	public ImageSerializer(Class<FileEntry> t) {
		super(t);
	}

	@Override
	public void serialize(FileEntry fileEntry, JsonGenerator gen, SerializerProvider provider) throws IOException {
		if(fileEntry.getCategory() != null) {
			switch (fileEntry.getCategory()) {
				case "profile":
				case "post":
					gen.writeString(imageRoute+fileEntry.getFileName());			
					break;
				case "stamp":
					gen.writeString(stampRoute+fileEntry.getFileName());
					break;
				default:
					gen.writeString("");			
					break;
			}
		} else {
			gen.writeString("");
		}
	}

}
