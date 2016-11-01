package app.taca.com.myapplication;

public class VersionVO {
	String os, version, url;
	
	// 기본생성자
	public VersionVO() {
		// TODO Auto-generated constructor stub
	}
	
	// 매개변수 있는 생성자
	public VersionVO(String os, String version, String url) {
		super();
		this.os = os;
		this.version = version;
		this.url = url;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
