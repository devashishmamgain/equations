package in.co.equations.modules.questionpaper.dataaccess.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NullValue;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(detachable = "true", table = "Sections", identityType = IdentityType.APPLICATION)
public class SectionBean {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent(defaultFetchGroup = "true", mappedBy = "sectionBean", nullValue = NullValue.NONE)
	private Set<QuestionBean> questionBean = new HashSet<QuestionBean>();

	@Persistent
	private String sectionName;


	@Persistent
	private Integer time;

	@Persistent
	@Temporal(TemporalType.TIME)
	private Date createdDate;

	@Persistent
	private String examName;

	@Persistent
	private String subjectName;

	public Set<QuestionBean> getQuestionBean() {
		return questionBean;
	}

	public void setQuestionBean(Set<QuestionBean> questionBean) {
		this.questionBean = questionBean;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Set<QuestionBean> getOptionBeans() {
		return questionBean;
	}

	public void setOptionBeans(Set<QuestionBean> questionBean) {
		this.questionBean = questionBean;
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
