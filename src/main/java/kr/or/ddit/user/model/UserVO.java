package kr.or.ddit.user.model;

import java.util.Date;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;

@XmlRootElement(name="userVO")
public class UserVO {
	private String name;
	
	@Size(min=4)
	private String userId;
	private String alias;
	private String pass;
	private String addr1;
	private String addr2;
	private String path;
	private String filename;
	private String zipcd;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;
	
	public UserVO() {
		
	}

	public UserVO(String userId, String name, String alias, String pass,
			String addr1, String addr2, String zipcd, Date birth) {
		this(name, userId, alias);
		this.pass = pass;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.zipcd = zipcd;
		this.birth = birth;
	}
	
	public UserVO(String name, String userId, String alias) {
		super();
		this.name = name;
		this.userId = userId;
		this.alias = alias;
	}

	@Override
	public String toString() {
		return "\nUserVO [\nname=" + name + "\nuserId=" + userId + "\nalias="
				+ alias + "\npass=" + pass + "\naddr1=" + addr1 + "\naddr2="
				+ addr2 + "\npath=" + path + "\nfilename=" + filename
				+ "\nzipcd=" + zipcd + "\nbirth=" + birth + "\n]";
	}
	
	public UserVO(String userId, String name, String alias, String pass,
			String addr1, String addr2, String path, String filename,
			String zipcd, Date birth) {
		super();
		this.name = name;
		this.userId = userId;
		this.alias = alias;
		this.pass = pass;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.path = path;
		this.filename = filename;
		this.zipcd = zipcd;
		this.birth = birth;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getZipcd() {
		return zipcd;
	}
	public void setZipcd(String zipcd) {
		this.zipcd = zipcd;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
}
