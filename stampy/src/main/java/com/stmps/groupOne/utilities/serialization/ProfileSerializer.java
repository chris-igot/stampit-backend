package com.stmps.groupOne.utilities.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.stmps.groupOne.models.Profile;

public class ProfileSerializer extends StdSerializer<Profile>  {
	private static final long serialVersionUID = 5893258603839349157L;
	
	public ProfileSerializer() {
		this(null);
	}

	public ProfileSerializer(Class<Profile> t) {
		super(t);
	}

	@Override
	public void serialize(Profile profile, JsonGenerator gen, SerializerProvider provider) throws IOException {
		if(profile.getId() != null) {
			gen.writeString(profile.getId());
		} else {
			gen.writeString("");
		}
	}

}
