package in.co.equations.modules.questionpaper.dataaccess.dao;

import in.co.equations.modules.questionpaper.common.DTO.SectionDTO;

public interface QuestionPaperDaoInf {
	public boolean saveOrUpdateSectionAndQuestions(SectionDTO sectionDTO);

	public SectionDTO getSectionAndQuestions(String sectionName);

}
