package in.co.equations.modules.questionpaper.service;


import in.co.equations.modules.questionpaper.common.DTO.SectionDTO;
import in.co.equations.modules.questionpaper.dataaccess.dao.QuestionPaperDaoInf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionPaperService implements QuestionPaperServiceInf {

	@Autowired
	private QuestionPaperDaoInf daoInf;

	@Transactional
	public boolean saveOrUpdateSectionAndQuestions(SectionDTO sectionDTO) {

		return daoInf.saveOrUpdateSectionAndQuestions(sectionDTO);
	}

	@Transactional(readOnly = true)
	public SectionDTO getSectionAndQuestions(String sectionName) {
		return daoInf.getSectionAndQuestions(sectionName);
	}



}
