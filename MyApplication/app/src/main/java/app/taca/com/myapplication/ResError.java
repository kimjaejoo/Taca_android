package app.taca.com.myapplication;

public class ResError {
	int code;
	String msg;
	
	// 매개변수있는 기본생성자
	public ResError(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	
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
	
	
}
