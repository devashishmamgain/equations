package in.co.equations.modules.questionpaper.web.bean;

import com.google.appengine.api.datastore.Key;

public class Question {

	private Key key;

	private String question;
	
	private String sectionName;
	  
	private Integer time;

	private String[] option;
	
	private String[] answer;
	
	
	
	private String examName;
	
	private String subjectName;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}


	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String[] getOption() {
		return option;
	}

	public void setOption(String[] option) {
		this.option = option;
	}
	
	public String[] getAnswer() {
		return answer;
	}

	public void setAnswer(String[] answer) {
		this.answer = answer;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	
	
}
