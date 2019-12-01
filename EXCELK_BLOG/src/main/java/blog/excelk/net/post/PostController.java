package blog.excelk.net.post;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	public boolean MatchPassword(Post post, String newPassword) {
		if(post.matchPassword(newPassword))
			return true;
		
		return false;
	}

	@GetMapping("/write")
	public String postForm() {
		return "/post/write";
	}

	@PostMapping("{id}/{newPassword}/postUpdate")
	public String postWrite(@PathVariable Long id, String newPassword, Post postUpdate) {
		Post post = postRepository.findById(id).get();
		System.out.println(newPassword);
		System.out.println(post.getPassword());
		if (!MatchPassword(post, newPassword)) {
			System.out.println("비밀번호 에러");
			return "redirect:/posts";
		}
		post.update(postUpdate);
		postRepository.save(post);
		System.out.println();

		return "redirect:/posts";
	}

	@GetMapping("{id}/postView")
	public String Update(@PathVariable Long id, Post viewPost, Model model) {
		viewPost = postRepository.findById(id).get();
		if (viewPost == null) {
			return "redirect:/posts";
		}
		
		model.addAttribute("posts", viewPost);
		System.out.println(viewPost);
		return "/post/postView";
	}
	
	@PostMapping("{id}/postModify")
	public String Modify(@PathVariable Long id, String newPassword, Post postModify, Model model) {
		postModify = postRepository.findById(id).get();
		if (postModify == null) {
			return "redirect:/posts";
		}
		if (!MatchPassword(postModify, newPassword)) {
			System.out.println("비밀번호 에러");
			String redirect = "redirect:/posts/" + id + "/postView";
			return redirect;
		}
		postModify.update(postModify);
		postRepository.save(postModify);
		model.addAttribute("posts", postModify);
		System.out.println(postModify);
		return "/post/postModify";
	}

	@PostMapping("")
	public String create(Post post, Model model) {
		if (post.getTopic() == "" || post.getText() == "" || post.getEmail() == "" || post.getPassword() == "") {
			return "redirect:/posts/write/#";
		}
		long time = System.currentTimeMillis(); 
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String YMD = dayTime.format(new Date(time));
		System.out.println(YMD);
		
		post.setYMD(YMD);
		System.out.println(post.toString());
		postRepository.save(post);
		return "redirect:/posts";
	}

	@GetMapping("")
	public String postList(Model model) {
		model.addAttribute("posts", postRepository.findAll());
		System.out.println("POSTLIST LOADING SUCCESS");
		return "/post/postList";
	}

	@GetMapping("/{board}")
	public String postKindsList(@PathVariable String board, Model model) {
		switch (board) {
		case "질문":
			model.addAttribute("posts", postRepository.findQuestion());
			break;
		case "프로젝트":
			model.addAttribute("posts", postRepository.findProject());
			break;
		case "공부":
			model.addAttribute("posts", postRepository.findStudy());
			break;
		case "자유":
			model.addAttribute("posts", postRepository.findAnother());
			break;
		default:
			model.addAttribute("posts", postRepository.findAll());
		}

		System.out.println("KINDS LOADING SUCCESS");
		return "/post/postList";
	}
	
	public boolean matchPassword(Post post, String newPassword) {
		if(post.matchPassword(newPassword))
			return true;
		
		return false;
	}
}
