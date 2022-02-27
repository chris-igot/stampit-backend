package com.stmps.groupOne.services;

import java.util.List;
import java.util.Optional;

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

	public Post addImagePost(MultipartFile uploadedFile, String description, Profile profile) {
		Post post = new Post();
		FileEntry image = fileServ.addImage(uploadedFile, "image");
		post.setDescription(description);
		post.setId(image.getId());
		post.setImage(image);
		post.setProfile(profile);
		return postRepo.save(post);
	}

	public List<Post> getAllPublic() {
		return postRepo.findAllPublic();
	}

	public List<Post> getAllFollowed(String profileId) {
		return postRepo.findAllFollowed(profileId);
	}

	public List<Post> getProfilePosts(String profileId) {
		return postRepo.findByProfileIdEqualsOrderByCreatedAtDesc(profileId);
	}

	public Post getById(String id) {
		Optional<Post> optPost = postRepo.findById(id);
		if(optPost.isPresent()) {
			return optPost.get();
		} else {
			return new Post();
		}
	}
}
