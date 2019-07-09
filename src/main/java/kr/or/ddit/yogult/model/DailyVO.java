package kr.or.ddit.yogult.model;

public class DailyVO {
	private int cid;	// 고객번호
	private int pid;	//제품 번호
	private String dt;	//애음일자(20190708)
	private int cnt;	//애음수량
	
	public DailyVO() {
	}
	
	public DailyVO(int cid, int pid, String dt, int cnt) {
		super();
		this.cid = cid;
		this.pid = pid;
		this.dt = dt;
		this.cnt = cnt;
	}
	
	@Override
	public String toString() {
		return "DailyVO [cid=" + cid + ", pid=" + pid + ", dt=" + dt + ", cnt=" + cnt + "]";
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
}
