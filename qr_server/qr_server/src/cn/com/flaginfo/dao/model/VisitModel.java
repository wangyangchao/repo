package cn.com.flaginfo.dao.model;

public class VisitModel {

	private int id ;
	
	private int qcId;
	
	private String visitTime;
	
	private String visitIp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQcId() {
		return qcId;
	}

	public void setQcId(int qcId) {
		this.qcId = qcId;
	}

	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	public String getVisitIp() {
		return visitIp;
	}

	public void setVisitIp(String visitIp) {
		this.visitIp = visitIp;
	}
	
	
}
