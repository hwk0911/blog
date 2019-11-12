package blog.excelk.net.post;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long>{
	@Query(value="SELECT * FROM post WHERE BOARD = '질문'", nativeQuery=true)
	public List<Post> findQuestion();
	
	@Query(value="SELECT * FROM post WHERE BOARD = '프로젝트'", nativeQuery=true)
	public List<Post> findProject();
	
	@Query(value="SELECT * FROM post WHERE BOARD = '공부'", nativeQuery=true)
	public List<Post> findStudy();
	
	@Query(value="SELECT * FROM post WHERE BOARD = '자유'", nativeQuery=true)
	public List<Post> findAnother();


}
