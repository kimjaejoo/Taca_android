package app.taca.com.myapplication;

import java.sql.Date;

// tbl_user 테이블의 1개의 row 데이터를 담는 그릇
public class UserDao {
	int memIdx;//	 INT(11) NOT NULL AUTO_INCREMENT COMMENT '인덱스',
	String uid;//	 VARCHAR(50) NOT NULL COMMENT '아이디',
	String name;//	 VARCHAR(50) NOT NULL COMMENT '이름',
	String upw;//	 VARCHAR(50) NOT NULL COMMENT '비번',
	Date regDate;//	 TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '가입일',
	Date lastLogin;//TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '최종로그인시간',
	String uuid;//	 VARCHAR(256) NOT NULL COMMENT '단말기고유값(재설치시회원가입생략을위해저장)',
	String token;//	 VARCHAR(256) NOT NULL COMMENT '토큰값(푸시서비스를 위한 고유값)',
	String phone;//	 VARCHAR(16) NOT NULL COMMENT '전화번호(개인정보..안드몰래)',
	String model;//	 VARCHAR(50) NOT NULL COMMENT '단말기모델(마케팅, 분석용)',
	String os;//	 VARCHAR(2) NOT NULL COMMENT '운영체계: a:안드 i:아이폰',
	int point;//	 INT(11) NOT NULL DEFAULT '1000' COMMENT '포인트',
	int level;//	 INT(11) NOT NULL DEFAULT '1' COMMENT '레벨 만렙:99',
	
	public int getMemIdx() {
		return memIdx;
	}
	public void setMemIdx(int memIdx) {
		this.memIdx = memIdx;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUpw() {
		return upw;
	}
	public void setUpw(String upw) {
		this.upw = upw;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
	
}
