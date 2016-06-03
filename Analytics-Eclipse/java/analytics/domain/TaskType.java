package analytics.domain;

public enum TaskType {	
	    TYPE1(1,60.0),
	    TYPE2(2,180.0),
	    TYPE3(3,10.0),
	    TYPE4(4,30.0);
	
	private final int type;
	private final Double averageTimeForConclusion; //minutos

	TaskType(int type, Double averageTimeForConclusion){
		this.type=type;
		this.averageTimeForConclusion=averageTimeForConclusion;
	}
	
	public Double getaverageTimeForConclusion(){
		return averageTimeForConclusion;
	}

	public Integer getType(){
		return type;
	}


	
}
