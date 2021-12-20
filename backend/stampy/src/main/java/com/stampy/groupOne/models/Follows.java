package com.stampy.groupOne.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "follows")
public class Follows {
	@Id
	@GeneratedValue(generator = "id-generator")
	@GenericGenerator(name = "id-generator",
    strategy = "com.stampy.groupOne.utilities.generators.UrlSafeIdGenerator")
	private String id;
//	private Profile follower;
//	private Profile followee;
}
