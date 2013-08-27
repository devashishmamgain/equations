package in.co.equations.modules.questionpaper.common.DTO;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

public class OptionDTO  implements Serializable{

	private Key key;

	private String option;

	private Integer optionsID;

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

	public Integer getOptionsID() {
		return optionsID;
	}

	public void setOptionsID(Integer optionsID) {
		this.optionsID = optionsID;
	}

}
