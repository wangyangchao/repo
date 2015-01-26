package cn.com.flaginfo.content;

public enum FileType {

	LOGO("0"),
	HEAD("1"),
	FILE("2");
	
	
	private String type;
	
	private FileType(String type){
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
