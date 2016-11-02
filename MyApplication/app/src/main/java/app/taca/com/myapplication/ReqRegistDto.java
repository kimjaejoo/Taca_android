package app.taca.com.myapplication;

// 회원가입 전문 받는 곳 : 요청
public class ReqRegistDto {

	
	String head;
	UserDao body;
	
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public UserDao getBody() {
		return body;
	}
	public void setBody(UserDao body) {
		this.body = body;
	}
	
	

	

}
