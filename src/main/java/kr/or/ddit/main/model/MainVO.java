package kr.or.ddit.main.model;

import java.util.List;

public class MainVO {
	
	private List<String> userId;
	private List<String> name;
	
	public List<AddrVO> getAddr() {
		return addr;
	}

	public void setAddr(List<AddrVO> addr) {
		this.addr = addr;
	}

	private List<AddrVO> addr;
	
	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public List<String> getUserId() {
		return userId;
	}

	public void setUserId(List<String> userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "MainVO [userId=" + userId + ", name=" + name + ", addr=" + addr + "]";
	}
}
