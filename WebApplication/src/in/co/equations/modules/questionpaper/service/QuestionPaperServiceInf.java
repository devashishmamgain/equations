package in.co.equations.modules.questionpaper.service;


import in.co.equations.modules.questionpaper.common.DTO.SectionDTO;

public interface QuestionPaperServiceInf {
	public boolean saveOrUpdateSectionAndQuestions(SectionDTO sectionDTO);

	public SectionDTO getSectionAndQuestions(String sectionName);
	
	
}
