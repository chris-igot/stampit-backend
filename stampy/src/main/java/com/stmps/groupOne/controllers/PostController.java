package com.stmps.groupOne.controllers;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.stmps.groupOne.models.Post;
import com.stmps.groupOne.models.Profile;
import com.stmps.groupOne.services.PostService;
import com.stmps.groupOne.services.ProfileService;
import com.stmps.groupOne.services.StampService;

@Controller
public class PostController {
	@Autowired
	PostService postServ;
	@Autowired
	ProfileService profileServ;
	@Autowired
	StampService stampServ;
	
	@GetMapping("/api/posts")
	public ResponseEntity<List<Post>> getAPIPostUser(@RequestParam("id") String theirProfileId, HttpSession session) {
		String ownProfileId = (String) session.getAttribute("profile_id");
		Profile theirProfile = profileServ.getById(theirProfileId);
		ResponseEntity<List<Post>> response;

		if(theirProfile != null) {
			if(!theirProfile.getIsPrivate()) {
				response = ResponseEntity.ok().body(postServ.getProfilePosts(theirProfileId));
			} else {
				switch (profileServ.checkFollowStatus(ownProfileId, theirProfileId)) {
				case 0:
				case 1:
					response = ResponseEntity.status(403).build();
					break;
				case 2:
					response = ResponseEntity.ok().body(postServ.getProfilePosts(theirProfileId));
					break;
				default:
					response = ResponseEntity.ok().body(Collections.emptyList());
					break;
				}
			}
		} else {
			response = ResponseEntity.notFound().build();
		}
		
		return response;
	}
	
	@GetMapping("/api/posts/self")
	public ResponseEntity<List<Post>> getAPIPostSelf(HttpSession session) {
		Profile profile = profileServ.getById((String) session.getAttribute("profile_id"));

		return ResponseEntity.ok().body(profile.getPosts());
	}
	
	@PostMapping("/api/posts/new")
	public ResponseEntity<Void> postAPIPost(
			@RequestParam("file") MultipartFile uploadedFile, 
			@RequestParam("description") String description, 
			HttpSession session
		) {
		Profile profile = profileServ.getById((String) session.getAttribute("profile_id"));
		postServ.addImagePost(uploadedFile, description, profile);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/api/posts/public")
	public ResponseEntity<List<Post>> getAPIPublic() {
		return ResponseEntity.ok().body(postServ.getAllPublic());
	}
	
	@GetMapping("/api/posts/followed")
	public ResponseEntity<List<Post>> getAPIFollowing(HttpSession session){
		String profileId = (String)session.getAttribute("profile_id");

		return ResponseEntity.ok().body( postServ.getAllFollowed(profileId));
	}

	@GetMapping("/api/posts/{postid}")
	public ResponseEntity<Post> getAPIPost(@PathVariable("postid") String postId, HttpSession session) {
		String ownProfileId = (String) session.getAttribute("profile_id");
		Post post = postServ.getById(postId);
		ResponseEntity<Post> response;

		if(post != null) {
			if(post.getProfile().getId().equals(ownProfileId) || !post.getProfile().getIsPrivate()) {
				response = ResponseEntity.ok().body(post);
			} else {
				String theirProfileId = post.getProfile().getId();
				switch (profileServ.checkFollowStatus(ownProfileId, theirProfileId)) {
				case 0:
				case 1:
					response = ResponseEntity.status(403).build();
					break;
				case 2:
					response = ResponseEntity.ok().body(post);
					break;
				default:
					response = ResponseEntity.notFound().build();
					break;
				}
			}
		} else {
			response = ResponseEntity.notFound().build();
		}

		return response;
	}
	
	@GetMapping("/api/posts/{postid}/remove")
	public ResponseEntity<Void> deleteAPIPost(@PathVariable("postid") String postId, HttpSession session) {
		String ownProfileId = (String) session.getAttribute("profile_id");
		Post post = postServ.getById(postId);
		ResponseEntity<Void> response;

		if(post == null) {
			response = new ResponseEntity<Void>( HttpStatus.NOT_FOUND );
		} else {
			if(post.getProfile().getId().equals(ownProfileId)) {
				if(post.getStamps().size() > 0) {
					stampServ.removePostStamps(post.getStamps());
				}
				postServ.removeById(post);
				response = new ResponseEntity<Void>( HttpStatus.OK );
			} else {
				response = new ResponseEntity<Void>( HttpStatus.FORBIDDEN );
			}
		}

		return response;
	}
}
