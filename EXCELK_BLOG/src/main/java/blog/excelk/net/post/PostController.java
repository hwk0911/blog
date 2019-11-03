package blog.excelk.net.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

	@PostMapping("/postUpdate")
	public String postWrite() {
		return "index";
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
		System.out.println("인식확인인식확인인식확인인식확인인식확인인식확인인식확인인식확인인식확인인식확인");
		return "/post/postList";
	}
}
