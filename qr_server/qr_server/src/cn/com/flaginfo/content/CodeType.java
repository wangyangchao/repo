package cn.com.flaginfo.content;

public enum CodeType {

	TEXT(1),
	URL(2),
	MSG(3),
	CARD(4),
	IMG(5),
	FILE(6);
	
	
	private int type;
	
	private CodeType(int type){
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public static CodeType getType(int i){
		CodeType[] values = CodeType.values();
		for(CodeType type : values){
			if (type.getType() == i){
				return type;
			}
		}
		return null;
	}
	
	
	
}
