package hqli.web;

import hqli.persistent.Post;
import hqli.persistent.PostRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
@Path("/")
public class PostController {

	@Inject
	private PostRepository postRepository;

	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PostDto> doName_Insecure(@PathParam("name") String name) {

		List<PostDto> dtos = new ArrayList<>();
		try {
			List<Post> posts = postRepository.getByName_Insecure(name);

			for (Post post : posts) {
				dtos.add(new PostDto(post.getId(), post.getName()));
			}
		} catch (Exception e) {
			e.toString();
			e.getMessage();
		}
		
		return dtos;
	}
	
	@GET
	@Path("/name/sort")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PostDto> doSort_Insecure(@QueryParam("sort") String sort) {

		List<PostDto> dtos = new ArrayList<>();
		try {
			List<Post> posts = postRepository.getByNamesSorted_Insecure(sort);

			for (Post post : posts) {
				dtos.add(new PostDto(post.getId(), post.getName()));
			}
		} catch (Exception e) {
			dtos.add(new PostDto(500, e.getMessage()));
		}
		
		return dtos;
	}

	@GET
	@Path("/secure/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PostDto> doName_Secure(@PathParam("name") String name) {

		List<Post> posts = postRepository.getByName_Secure(name);

		List<PostDto> dtos = new ArrayList<>();

		for (Post post : posts) {
			dtos.add(new PostDto(post.getId(), post.getName()));
		}

		return dtos;
	}
	
	
	@GET
	@Path("/secure2/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PostDto> doName_Secure2(@PathParam("name") String name) throws Exception {

		List<Post> posts = postRepository.getByName_Criteria(name);

		List<PostDto> dtos = new ArrayList<>();

		for (Post post : posts) {
			dtos.add(new PostDto(post.getId(), post.getName()));
		}

		return dtos;
	}

	@POST
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> doAdd(@PathParam("name") String name) {

		postRepository.save(new Post(name));

		return new HashMap<String, String>() {{
			put("message", "ok");
		}};
	}
}
