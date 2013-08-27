package in.co.equations.modules.questionpaper.common.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Key;

public class SectionDTO implements Serializable{

	private Key key;

	private String name;
	
	private Integer time;
	
	private List<QuestionDTO> questionDTO;
	
	private Date createdDate;
	
	private String examName;

	private String subjectName;
	
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<QuestionDTO> getQuestionDTO() {
		return questionDTO;
	}

	public void setQuestionDTO(List<QuestionDTO> questionDTO) {
		this.questionDTO = questionDTO;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
