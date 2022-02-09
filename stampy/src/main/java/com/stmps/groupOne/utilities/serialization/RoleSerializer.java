package com.stmps.groupOne.utilities.serialization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.stmps.groupOne.models.Role;

public class RoleSerializer extends StdSerializer<Set<Role>>  {
	private static final long serialVersionUID = -1789450774419809475L;

	public RoleSerializer() {
		this(null);
	}

	public RoleSerializer(Class<Set<Role>> t) {
		super(t);
	}

	@Override
	public void serialize(Set<Role> roles, JsonGenerator gen, SerializerProvider provider) throws IOException {
		List<String> stringRoles = new ArrayList<String>();
		
		for (Iterator<Role> iterator = roles.iterator(); iterator.hasNext();) {
			Role role = (Role) iterator.next();
			stringRoles.add(role.getId());
		}
		
		gen.writeObject(stringRoles);
	}

}
