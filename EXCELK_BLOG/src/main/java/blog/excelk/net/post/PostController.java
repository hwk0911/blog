package blog.excelk.net.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostRepository postRepository;

	@GetMapping("/write")
	public String postForm() {
		return "/post/write";
	}

	@PostMapping("{id}/{password}/postUpdate")
	public String postWrite(@PathVariable Long id, @PathVariable String password, Post postUpdate) {
		Post post = postRepository.findById(id).get();
		if(!post.matchPassword(password)) {
			System.out.println("비밀번호 에러");
			return "redircet:/posts";
		}
		post.update(postUpdate);
		postRepository.save(post);
		System.out.println();
		
		return "redirect:/posts";
	}
	
	@GetMapping("{id}/postView")
	public String Update(@PathVariable Long id, Post viewPost, Model model) {
		viewPost = postRepository.findById(id).get();
		if(viewPost == null) {
			return "redirect:/posts";
		}
		viewPost.increaseViewCount();
		viewPost.update(viewPost);
		postRepository.save(viewPost);
		model.addAttribute("posts", viewPost);		
		System.out.println(viewPost);
		return "/post/postView";
	}
	
	@PostMapping("")
	public String create(Post post, Model model) {
		postRepository.save(post);
		System.out.println(post.toString());
		return "redirect:/posts";
	}
	
	@GetMapping("")
	public String postList(Model model) {
		model.addAttribute("posts", postRepository.findAll());
		System.out.println("POSTLIST LOADING SUCCESS");
		return "/post/postList";
	}
	
	
}
