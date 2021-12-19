package com.stampy.groupOne.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stampy.groupOne.models.FileEntry;
import com.stampy.groupOne.models.Post;
import com.stampy.groupOne.models.Profile;
import com.stampy.groupOne.repositories.PostRepository;

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
