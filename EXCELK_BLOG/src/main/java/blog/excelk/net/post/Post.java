package blog.excelk.net.post;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "BOARD", nullable = false)
	private String board;
	
	@Column(name = "TEXT", nullable = false)
	private String text;
	
	@Column(name = "TOPIC", nullable = false)
	private String topic;
	
	@Column(name = "YMD", nullable = true)
	private String YMD;
	
	@Column(name = "VIEWCOUNT")
	private int viewCount = 0;

	
	
	public void increaseViewCount() {
		this.viewCount++;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public void setText(String text) {
		this.text = text;
	}		

	public void setYMD(String YMD) {
		this.YMD = YMD;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", email=" + email + ", password=" + password + ", board=" + board + ", text=" + text
				+ ", topic=" + topic + ", YMD=" + YMD + ", viewCount=" + viewCount + "]";
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getBoard() {
		return board;
	}

	public String getText() {
		return text;
	}
	
	public void update(Post newPost) {
		this.topic = newPost.topic;
		this.email = newPost.email;
		this.text = newPost.text;
		this.board = newPost.board;
	}
	
	public boolean matchPassword(String newPassword) {
		if (newPassword == null) {
			return false;
		}

		if (password.equals(newPassword)){
			System.out.println("PASSWORD TRUE");
			return true;
		}
		
		return false;
	}
		
}
