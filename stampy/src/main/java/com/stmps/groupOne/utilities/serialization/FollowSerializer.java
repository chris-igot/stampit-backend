package com.stmps.groupOne.utilities.serialization;

import java.io.IOException;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.stmps.groupOne.models.Profile;

public class FollowSerializer extends StdSerializer<Set<Profile>>  {
	private static final long serialVersionUID = -828934917720279321L;

	public FollowSerializer() {
		this(null);
	}

	public FollowSerializer(Class<Set<Profile>> t) {
		super(t);
	}

	@Override
	public void serialize(Set<Profile> profiles, JsonGenerator gen, SerializerProvider provider) throws IOException {
		if(profiles != null) {
			gen.writeNumber(profiles.size());
		} else {
			gen.writeNumber(0);
		}
	}

}
