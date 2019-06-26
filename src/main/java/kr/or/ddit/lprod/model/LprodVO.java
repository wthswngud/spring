package kr.or.ddit.lprod.model;

public class LprodVO {
	private String lprod_id;
	private String lprod_nm;
	private String lprod_gu;
	
	
	
	public LprodVO() {
		
	}
	
	public LprodVO(String lprod_id, String lprod_nm, String lprod_gu) {
		super();
		this.lprod_id = lprod_id;
		this.lprod_nm = lprod_nm;
		this.lprod_gu = lprod_gu;
	}
	
	@Override
	public String toString() {
		return "LprodVO [lprod_id=" + lprod_id + ", lprod_nm=" + lprod_nm + ", lprod_gu=" + lprod_gu + "]";
	}
	
	public String getLprod_id() {
		return lprod_id;
	}
	public void setLprod_id(String lprod_id) {
		this.lprod_id = lprod_id;
	}
	public String getLprod_nm() {
		return lprod_nm;
	}
	public void setLprod_nm(String lprod_nm) {
		this.lprod_nm = lprod_nm;
	}
	public String getLprod_gu() {
		return lprod_gu;
	}
	public void setLprod_gu(String lprod_gu) {
		this.lprod_gu = lprod_gu;
	}
}
