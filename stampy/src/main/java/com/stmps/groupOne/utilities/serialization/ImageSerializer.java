package com.stmps.groupOne.utilities.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.stmps.groupOne.models.FileEntry;

public class ImageSerializer extends StdSerializer<FileEntry> {
	private static final long serialVersionUID = -6582219661871121216L;

	public ImageSerializer() {
		this(null);
	}
	public ImageSerializer(Class<FileEntry> t) {
		super(t);
	}

	@Override
	public void serialize(FileEntry fileEntry, JsonGenerator gen, SerializerProvider provider) throws IOException {
		if(fileEntry.getCategory() != null) {
			gen.writeString("/"+fileEntry.getCategory()+"/"+fileEntry.getFileName());
		} else {
			gen.writeString("");
		}
	}

}
