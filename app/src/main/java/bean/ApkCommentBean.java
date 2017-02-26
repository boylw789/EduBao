package bean;

import java.io.Serializable;

/**
 *
 */
public class ApkCommentBean implements Serializable {

	private String userName;
	private String time;
	private String userScore;
	private String goodComment;
	private String userComment;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUserScore() {
		return userScore;
	}

	public void setUserScore(String userScore) {
		this.userScore = userScore;
	}

	public String getGoodComment() {
		return goodComment;
	}

	public void setGoodComment(String goodComment) {
		this.goodComment = goodComment;
	}

	public String getUserComment() {
		return userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

}
