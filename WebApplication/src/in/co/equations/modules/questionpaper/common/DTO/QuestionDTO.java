package in.co.equations.modules.questionpaper.common.DTO;

import java.io.Serializable;
import java.util.List;

import com.google.appengine.api.datastore.Key;

public class QuestionDTO implements Serializable{

	private Key key;

	private String question;

	private String answer;

	private List<OptionDTO> optionDTO;
	
	private Integer sequenceNo;

	public Integer getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(Integer sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

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

	public List<OptionDTO> getOptionDTO() {
		return optionDTO;
	}

	public void setOptionDTO(List<OptionDTO> option) {
		this.optionDTO = option;
	}

}
