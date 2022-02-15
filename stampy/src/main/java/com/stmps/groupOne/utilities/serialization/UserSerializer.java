package com.stmps.groupOne.utilities.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.stmps.groupOne.models.User;

public class UserSerializer  extends StdSerializer<User>  {
	private static final long serialVersionUID = 5629273613824227512L;

	public UserSerializer() {
		this(null);
	}

	public UserSerializer(Class<User> t) {
		super(t);
	}

	@Override
	public void serialize(User dbUser, JsonGenerator gen, SerializerProvider provider) throws IOException {
		if(dbUser.getId() != null) {
			User user = new User();
			user.setId(dbUser.getId());
			user.setUsername(dbUser.getUsername());
			user.setEmail(dbUser.getEmail());
			user.setRoles(dbUser.getRoles());
			gen.writeObject(user);
		} else {
			gen.writeObject(new User());
		}
	}

}