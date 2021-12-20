package com.stmps.groupOne.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stmps.groupOne.models.FileEntry;
import com.stmps.groupOne.models.Post;
import com.stmps.groupOne.models.Profile;
import com.stmps.groupOne.repositories.PostRepository;

@Service
public class PostService {
	@Autowired
	PostRepository postRepo;
	@Autowired
	JointFileService fileServ;
	public Post addImagePost(MultipartFile uploadedFile, Profile profile) {
		Post post = new Post();
		FileEntry image = fileServ.addImage(uploadedFile, "post");
		post.setId(image.getId());
		post.setImage(image);
		post.setProfile(profile);
		return postRepo.save(post);
	}
	public List<Post> getAll() {
		return postRepo.findAllByOrderByCreatedAtDesc();
	}
	public Post getById(String id) {
		return postRepo.findById(id).get();
	}
}
