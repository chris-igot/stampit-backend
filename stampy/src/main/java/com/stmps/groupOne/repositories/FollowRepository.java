package com.stmps.groupOne.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.stmps.groupOne.models.Follow;
import com.stmps.groupOne.models.FollowCompositeKey;

public interface FollowRepository extends CrudRepository<Follow, FollowCompositeKey> {
	List<Follow> findAll();
}
