package in.co.equations.modules.questionpaper.web.bean;

import com.google.appengine.api.datastore.Key;

public class Options {

	private Key key;

	private String option;

	private Integer optionID;

	private String answer;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public Integer getOptionID() {
		return optionID;
	}

	public void setOptionID(Integer optionID) {
		this.optionID = optionID;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
