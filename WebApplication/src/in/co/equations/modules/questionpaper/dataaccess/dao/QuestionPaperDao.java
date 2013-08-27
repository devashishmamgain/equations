package in.co.equations.modules.questionpaper.dataaccess.dao;

import com.thenextpointer.db.PMF;

import in.co.equations.modules.questionpaper.common.DTO.OptionDTO;
import in.co.equations.modules.questionpaper.common.DTO.QuestionDTO;
import in.co.equations.modules.questionpaper.common.DTO.SectionDTO;
import in.co.equations.modules.questionpaper.dataaccess.model.OptionBean;
import in.co.equations.modules.questionpaper.dataaccess.model.QuestionBean;
import in.co.equations.modules.questionpaper.dataaccess.model.SectionBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

@Repository
public class QuestionPaperDao implements QuestionPaperDaoInf {

	private PersistenceManager pm;

	@Override
	public boolean saveOrUpdateSectionAndQuestions(SectionDTO sectionDTO) {

		pm = PMF.get().getPersistenceManager();

		boolean result = true;
		
		
		String sectionName = sectionDTO.getName();
		Integer time = sectionDTO.getTime();
		String examName=sectionDTO.getExamName();
		java.util.Date createdDate= sectionDTO.getCreatedDate();
		String subjectName=sectionDTO.getSubjectName();
		SectionBean sectionBean = getSectionByName(sectionName);
		
		boolean isNewSection = false;
		
		
		if (sectionBean == null) {
			isNewSection = true;
			sectionBean = new SectionBean();
			sectionBean.setSectionName(sectionName);

			sectionBean.setQuestionBean(new HashSet<QuestionBean>());
		}
		System.out.println("name in sectionbean==" + sectionDTO.getName());
		
		sectionBean.setTime(time);
		sectionBean.setCreatedDate(createdDate);
		sectionBean.setExamName(examName);
		sectionBean.setSubjectName(subjectName);
		

		System.out.println("time" + time);
		QuestionDTO questionDTO = sectionDTO.getQuestionDTO().get(0);
		
		if(questionDTO.getQuestion() != null && questionDTO.getOptionDTO() != null && questionDTO.getAnswer() != ""){
			QuestionBean bean = convertQuestionDtoTOQuestionBean(questionDTO);
			sectionBean.getQuestionBean().add(bean);
		}
		pm.makePersistent(sectionBean);
	
		return result;
	}

	public SectionBean getSectionByName(String sectionName) {
		pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(SectionBean.class);

		q.setFilter("sectionName == sectionNameParam");

		q.declareParameters("String sectionNameParam");

		@SuppressWarnings("unchecked")
		List<SectionBean> results = (List<SectionBean>) q.execute(sectionName);
		if (results.size() > 0) {
			return results.get(0);
		}

		return null;
	}

	private QuestionBean convertQuestionDtoTOQuestionBean(
			QuestionDTO questionDTO) {

		QuestionBean questionBean = new QuestionBean();
		questionBean.setQuestion(questionDTO.getQuestion());

		questionBean.setAnswer(questionDTO.getAnswer());
		List<OptionDTO> optionDTOList = questionDTO.getOptionDTO();

		

		Set<OptionBean> optionBeans = new HashSet<OptionBean>();

		for (OptionDTO optionDTO : optionDTOList) {
			OptionBean optionBean = new OptionBean();
			optionBean.setOption(optionDTO.getOption());
			optionBean.setQuestionBean(questionBean);
			optionBeans.add(optionBean);
		}
		questionBean.setOptionBeans(optionBeans);

		return questionBean;
	}
	

	public SectionDTO getSectionAndQuestions(String sectionName) {

		SectionBean sectionBean = getSectionByName(sectionName);

		if (sectionBean == null) {
			throw new RuntimeException("Section with name[" + sectionName
					+ "] does not exists");
		}

		Set<QuestionBean> questionBeans = sectionBean.getQuestionBean();
		List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
		for (QuestionBean questionBean : questionBeans) {

			QuestionDTO questionDTO = new QuestionDTO();
			questionDTO.setQuestion(questionBean.getQuestion());
			questionDTO.setAnswer(questionBean.getAnswer());

			Set<OptionBean> optionBeans = questionBean.getOptionBeans();
			List<OptionDTO> optionDTOs = new ArrayList<OptionDTO>();
			for (OptionBean optionBean : optionBeans) {
				OptionDTO optionDTO = new OptionDTO();
				optionDTO.setOption(optionBean.getOption());
				optionDTOs.add(optionDTO);
			}
			questionDTO.setOptionDTO(optionDTOs);
			questionDTOList.add(questionDTO);

		}

		SectionDTO sectionDTO = new SectionDTO();
		sectionDTO.setName(sectionName);
		sectionDTO.setTime(sectionBean.getTime());
		sectionDTO.setQuestionDTO(questionDTOList);
		return sectionDTO;
	}

	
}
