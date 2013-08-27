package in.co.equations.modules.questionpaper.dataaccess.model;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NullValue;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(detachable = "true", table = "Questions", identityType = IdentityType.APPLICATION)
public class QuestionBean {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String question;

	@Persistent(defaultFetchGroup = "true", mappedBy = "questionBean", nullValue = NullValue.NONE)
	private Set<OptionBean> optionBeans = new HashSet<OptionBean>();

	

	@Persistent
	private String answer;

	@Persistent
	private SectionBean sectionBean;
	
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

	public Set<OptionBean> getOptionBeans() {
		return optionBeans;
	}

	public void setOptionBeans(Set<OptionBean> optionBeans) {
		this.optionBeans = optionBeans;
	}

	public SectionBean getSectionBean() {
		return sectionBean;
	}

	public void setSectionBean(SectionBean sectionBean) {
		this.sectionBean = sectionBean;
	}

}
