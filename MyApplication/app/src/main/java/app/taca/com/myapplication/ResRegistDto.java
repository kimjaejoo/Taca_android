package app.taca.com.myapplication;

// 회원가입 결과 처리 : 응답
public class ResRegistDto {

	int code;
	String msg;
	UserDao data;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public UserDao getData() {
		return data;
	}
	public void setData(UserDao data) {
		this.data = data;
	}
	
	
	

	

}
