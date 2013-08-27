package in.co.equations.modules.questionpaper.web.bean;

public class Exam {

	private String[] answer;
	private Integer  sequenceNo;
	private boolean lastQuestion;
	private Integer time;
	private String options;
	
	
	public String[] getAnswer() {
		return answer;
	}
	public void setAnswer(String[] answer) {
		this.answer = answer;
	}
	public Integer getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(Integer sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	public boolean isLastQuestion() {
		return lastQuestion;
	}
	public void setLastQuestion(boolean lastQuestion) {
		this.lastQuestion = lastQuestion;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	
	
}
