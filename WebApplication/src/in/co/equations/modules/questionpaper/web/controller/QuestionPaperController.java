package in.co.equations.modules.questionpaper.web.controller;

import com.thenextpointer.db.PMF;
import in.co.equations.modules.questionpaper.common.DTO.OptionDTO;
import in.co.equations.modules.questionpaper.common.DTO.QuestionDTO;
import in.co.equations.modules.questionpaper.common.DTO.SectionDTO;
import in.co.equations.modules.questionpaper.dataaccess.model.OptionBean;
import in.co.equations.modules.questionpaper.dataaccess.model.QuestionBean;
import in.co.equations.modules.questionpaper.dataaccess.model.SectionBean;
import in.co.equations.modules.questionpaper.service.QuestionPaperServiceInf;
import in.co.equations.modules.questionpaper.web.bean.Exam;
import in.co.equations.modules.questionpaper.web.bean.Options;
import in.co.equations.modules.questionpaper.web.bean.Question;
import in.co.equations.modules.questionpaper.web.bean.Section;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
public class QuestionPaperController {
	@Autowired
	private QuestionPaperServiceInf serviceInf;

	private static final Logger logger = Logger.getLogger(QuestionPaperController.class.getName());

	@RequestMapping(value = "/createpaper.form", method = RequestMethod.GET)
	public String createPaper(Model model) {

		Question reg = new Question();
		System.out.println("inside create questions==" + reg.toString());
		model.addAttribute("createQuestions", reg);
		return "QuestionPaper";

	}

	@RequestMapping(value = "/createpaper.form", method = RequestMethod.POST)
	public String postQuestions(Model model,
			@ModelAttribute("createQuestions") Question question,
			BindingResult result, HttpServletRequest request, Section section) {
		String message;

		SectionDTO sectionDTO = convertToSectionDTO(question);

		request.setAttribute("sectionName", question.getSectionName());
		request.setAttribute("time", question.getTime());

		boolean savesuccess = serviceInf
				.saveOrUpdateSectionAndQuestions(sectionDTO);
		
		if (savesuccess) {
			message = "user succesfully posted the question";
			request.setAttribute("message", message);
			return "QuestionPaper";
		} else {
			message = "unable to post the question";
			request.setAttribute("message", message);
			return "QuestionPaper";

		}
	}

	@RequestMapping(value = "/availableexams.form", method = RequestMethod.GET)
	public String availableexams(Model model, HttpServletRequest request) {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		javax.jdo.Query q = pm.newQuery(SectionBean.class);

		List<SectionBean> results = new ArrayList<SectionBean>();
		List<SectionBean> sectionDetailsList = new ArrayList<SectionBean>(); 

		try {
			results = (List<SectionBean>) q.execute();
			if (results.isEmpty()) {
				model.addAttribute("availableexams", null);
				model.addAttribute("examName", null);
				model.addAttribute("createdDate", null);
				model.addAttribute("subjectName", null);

			} else {
				model.addAttribute("availableexams", results);
				model.addAttribute("examName", results);
				model.addAttribute("createdDate", results);
				model.addAttribute("subjectName", results);
				int index =0;
				for (SectionBean sectionBean : results) {
					Set<QuestionBean> optionBeans = sectionBean.getOptionBeans();
					Set<QuestionBean> questionBean = sectionBean.getQuestionBean();
					System.out.println("optionBeans.isEmpty() == "+optionBeans.isEmpty());
					System.out.println("questionBean.isEmpty() == "+questionBean.isEmpty());
					if(!optionBeans.isEmpty() || !questionBean.isEmpty()){
						sectionDetailsList.add(sectionBean);
					}
					++index;
				}
			}
		} finally {
			q.closeAll();
			pm.close();
		}
		
		model.addAttribute("results", sectionDetailsList);
		return "availableexams";

	}

	@RequestMapping(value = "/startExam.form", params = { "sectionName" }, method = RequestMethod.GET)
	public String getQuestionPaper(Model model,
			@RequestParam("sectionName") String sectionName,
			HttpServletRequest request, HttpSession session) {

		SectionDTO sectionDTO = serviceInf.getSectionAndQuestions(sectionName);
		List<QuestionDTO> questionDTOList = sectionDTO.getQuestionDTO();
		int number = 1;
		Map<Integer, QuestionDTO> questionSeqDTOMap = new TreeMap<Integer, QuestionDTO>();
		for (QuestionDTO questionDTO : questionDTOList) {
			questionSeqDTOMap.put(number++, questionDTO);
		}

		TreeMap<Integer, Boolean> answerMap = new TreeMap<Integer, Boolean>();
		Map<Integer, String[]> selectedOptions = new TreeMap<Integer, String[]>();
		setQuestionDetails(model, questionSeqDTOMap, 1, answerMap, sectionDTO);

		//session.setMaxInactiveInterval((sectionDTO.getTime()+60));
		session.setAttribute("questionSeqDTOMap", questionSeqDTOMap);
		session.setAttribute("answerMap", answerMap);
		session.setAttribute("sectionDTO", sectionDTO);
		session.setAttribute("selectedOptions", selectedOptions);
		return "exam";

	}

	private void setQuestionDetails(Model model,
			Map<Integer, QuestionDTO> questionSeqDTOMap, Integer sequenceNo,
			Map<Integer, Boolean> answerMap, SectionDTO sectionDTO) {
		QuestionDTO questionDTO = questionSeqDTOMap.get(sequenceNo);
		List<String> options = new ArrayList<String>();
		List<OptionDTO> optionDTOs = questionDTO.getOptionDTO();
		for (OptionDTO optionDTO : optionDTOs) {
			options.add(optionDTO.getOption());
		}

		model.addAttribute("description", questionDTO.getQuestion());
		model.addAttribute("options", options);
		model.addAttribute("sequenceNo", sequenceNo);
	
		Exam exam = new Exam();
		model.addAttribute("time", sectionDTO.getTime());
		model.addAttribute("isMultiChoice",
				questionDTO.getAnswer().contains("|"));
		
		model.addAttribute("isLastQuestion",questionSeqDTOMap.get(sequenceNo + 1) == null
				|| questionSeqDTOMap.size() - 1 == answerMap.size());
		model.addAttribute("exam", exam);
	}

	/*private void setPrevQuestionDetails(Model model,
			Map<Integer, QuestionDTO> questionSeqDTOMap, Integer sequenceNo,
			Map<Integer, Boolean> answerMap, SectionDTO sectionDTO) {

		QuestionDTO questionDTO = questionSeqDTOMap.get(sequenceNo);
		System.out.println("sequesnce number in question dto" + sequenceNo);
		System.out.println("questiondto==" + questionDTO);
		System.out.println("sequencenum==" + questionSeqDTOMap.get(sequenceNo));

		List<String> options = new ArrayList<String>();
		List<OptionDTO> optionDTOs = questionDTO.getOptionDTO();
		System.out.println("options for previous question==" + optionDTOs);
		for (OptionDTO optionDTO : optionDTOs) {
			
			options.add(optionDTO.getOption());
		}

		model.addAttribute("description", questionDTO.getQuestion());
		model.addAttribute("options", options);
		model.addAttribute("sequenceNo", sequenceNo);
		

		model.addAttribute("time", sectionDTO.getTime());
		model.addAttribute("isMultiChoice",
				questionDTO.getAnswer().contains("|"));

		model.addAttribute("isLastQuestion",
				questionSeqDTOMap.get(sequenceNo + 1) == null
						|| questionSeqDTOMap.size() + 1 == answerMap.size());
		model.addAttribute("exam", new Exam());
	}

	boolean isLast = false;*/

	@RequestMapping(params = "next", method = RequestMethod.POST)
	public String nextQuestion(Model model, @ModelAttribute("exam") Exam exam,
			BindingResult result, HttpServletRequest request,
			HttpSession session, Section section) {

		Integer sequenceNo = exam.getSequenceNo();
		Integer time = exam.getTime();

		Map<Integer, QuestionDTO> questionSeqDTOMap = (Map<Integer, QuestionDTO>) session
				.getAttribute("questionSeqDTOMap");
		
		if (!questionSeqDTOMap.containsKey(sequenceNo)) {
			request.getSession().setAttribute("questionSeqDTOMap",
					questionSeqDTOMap);
			
		}

		Map<Integer, Boolean> answerMap = (Map<Integer, Boolean>) session
				.getAttribute("answerMap");
		SectionDTO sectionDTO = (SectionDTO) session.getAttribute("sectionDTO");

		 Map<Integer, String[]> selectedOptions = (Map<Integer, String[]>)
		 session .getAttribute("selectedOptions");
		
		 boolean isLastQuestion = questionSeqDTOMap.get(sequenceNo + 1) == null
					|| questionSeqDTOMap.size() - 1 == answerMap.size();
		 
		 setSelectedOptionsInSession(selectedOptions,exam);
		
		 setAnswerInSession(questionSeqDTOMap, exam, answerMap);

		 Integer nextSequeceNumber=0;
		if (isExamFinished(questionSeqDTOMap, answerMap) || time <= 0) {
			Collection<Boolean> values = answerMap.values();
			System.out.println("answer values==" + values);
			float answerCount = 0;
			for (Boolean isCorrect : values) {
				if (Boolean.TRUE.equals(isCorrect)) {
					answerCount++;
				}
			}
			Integer passorfial = (int) (answerCount / questionSeqDTOMap.size() * 100);
			String resultMessage = "";
			session.invalidate();
			if (passorfial < 40) {
				resultMessage = "you failed";
			} else {
				resultMessage = "you have passed the exam";
			}
			model.addAttribute("result", resultMessage);
			model.addAttribute("score", passorfial + "%");
			return "exam_result";
		} else {
			nextSequeceNumber = nextSequenceNumber(questionSeqDTOMap,
					answerMap, sequenceNo,isLastQuestion);
			
			setQuestionDetails(model, questionSeqDTOMap, nextSequeceNumber,
					answerMap, sectionDTO);
		}
		if(selectedOptions.containsKey(nextSequeceNumber)){
			String[] selectedOptionsArr = selectedOptions.get(nextSequeceNumber);
			exam.setAnswer(selectedOptionsArr);
		 }	
		model.addAttribute("exam", exam);
		model.addAttribute("time", time);
		return "exam";

	}

	private Map<Integer, String[]> setSelectedOptionsInSession(
			Map<Integer, String[]> selectedOptions, Exam exam) {

		if(exam.getAnswer() != null){
			selectedOptions.put(exam.getSequenceNo(), exam.getAnswer());
		}		
		return selectedOptions;
	}

	public Integer nextSequenceNumber(
			Map<Integer, QuestionDTO> questionSeqDTOMap,
			Map<Integer, Boolean> answerMap, Integer sequenceNo, boolean isLastQuestion) {
		Integer nextSequenceNo = null;

		if (questionSeqDTOMap.size() == sequenceNo) {
			nextSequenceNo = 1;
		} else {
			nextSequenceNo = sequenceNo + 1;
		}

		if(isLastQuestion){
			while (answerMap.get(nextSequenceNo) != null) {
				nextSequenceNo++;
			}
		}		

		return nextSequenceNo;
	}

	@RequestMapping(params = "previous", method = RequestMethod.POST)
	public String prevQuestion(Model model, @ModelAttribute("exam") Exam exam,
			BindingResult result, HttpServletRequest request,
			HttpSession session, Section section) {

		

		Integer sequenceNo = exam.getSequenceNo();
		Integer time = exam.getTime();

		

		Map<Integer, QuestionDTO> questionSeqDTOMap = (Map<Integer, QuestionDTO>) session
				.getAttribute("questionSeqDTOMap");
		System.out.println("questionSeqDTOMap" + questionSeqDTOMap);

		Map<Integer, Boolean> answerMap = (Map<Integer, Boolean>) session
				.getAttribute("answerMap");
		
		Map<Integer, String[]> selectedOptions = (Map<Integer, String[]>)
		 session .getAttribute("selectedOptions");
		

		SectionDTO sectionDTO = (SectionDTO) session.getAttribute("sectionDTO");
		System.out.println("time == " + time);

		Integer prevSequenceNumber = prevSequenceNumber(questionSeqDTOMap,
				sequenceNo);

		setQuestionDetails(model, questionSeqDTOMap, prevSequenceNumber,
				answerMap, sectionDTO);

		

		model.addAttribute("time", time - 1);
		
		OptionBean options = new OptionBean();
		
		
		if(selectedOptions.containsKey(prevSequenceNumber)){ 
			String[] selectedOptionsArr = selectedOptions.get(prevSequenceNumber);
			exam.setAnswer(selectedOptionsArr);
		 }		

		model.addAttribute("exam", exam);
		return "exam";
	}

	public Integer prevSequenceNumber(
			Map<Integer, QuestionDTO> questionSeqDTOMap, Integer sequenceNo) {
		Integer prevSequenceNo = null;

		if (sequenceNo == 1) {
			prevSequenceNo = 1;
		} else {
			prevSequenceNo = sequenceNo - 1;
		}

		return prevSequenceNo;
	}

	private boolean isExamFinished(Map<Integer, QuestionDTO> questionSeqDTOMap,
			Map<Integer, Boolean> answerMap) {
		return questionSeqDTOMap.size() == answerMap.size();
	}

	private void setAnswerInSession(
			Map<Integer, QuestionDTO> questionSeqDTOMap, Exam exam,
			Map<Integer, Boolean> answerMap) {
		String[] answers = exam.getAnswer();
		if (answers != null && answers.length > 0) {
			Integer sequenceNo = exam.getSequenceNo();
			QuestionDTO questionDTO = questionSeqDTOMap.get(sequenceNo);

			List<String> studentAnswer = Arrays.asList(answers);
			List<String> correctAnswer = Arrays.asList(questionDTO.getAnswer()
					.split("\\|"));

			answerMap.put(sequenceNo, studentAnswer.containsAll(correctAnswer)
					&& studentAnswer.size() == correctAnswer.size());
		}
	}

	@RequestMapping(params = "submitAll", method = RequestMethod.POST)
	public String endSection(Model model, @ModelAttribute("exam") Exam exam,
			BindingResult result, HttpServletRequest request,
			HttpSession session, Section section) {

		Integer time = exam.getTime();
		Map<Integer, QuestionDTO> questionSeqDTOMap = (Map<Integer, QuestionDTO>) session
				.getAttribute("questionSeqDTOMap");

		Map<Integer, Boolean> answerMap = (Map<Integer, Boolean>) session
				.getAttribute("answerMap");
		

		setAnswerInSession(questionSeqDTOMap, exam, answerMap);

		Collection<Boolean> values = answerMap.values();
		float answerCount = 0;
		for (Boolean isCorrect : values) {
			if (Boolean.TRUE.equals(isCorrect)) {
				answerCount++;
			}
		}
		Integer passorfial = (int) (answerCount / questionSeqDTOMap.size() * 100);
		System.out.println("result score==" + passorfial);
		String resultMsg;
		if (passorfial < 40) {
			resultMsg = "you failed";
		} else {
			resultMsg = "you have passed the exam";
		}
		model.addAttribute("result", resultMsg);
		model.addAttribute("score", passorfial + "%");

		return "exam_result";
	}

	private SectionDTO convertToSectionDTO(Question question) {
		
		QuestionDTO questionDTO = new QuestionDTO();

		SectionDTO sectionDTO = new SectionDTO();
		sectionDTO.setCreatedDate(new Date());
		sectionDTO.setTime(question.getTime() * 60);
		sectionDTO.setName(question.getSectionName());
		sectionDTO.setExamName(question.getExamName());
		sectionDTO.setSubjectName(question.getSubjectName());
		List<OptionDTO> options = new ArrayList<OptionDTO>();
		String answerStr = "";
		if(question.getAnswer() != null && question.getQuestion() != null &&
				question.getOption() != null){
			questionDTO.setQuestion(question.getQuestion());
			String[] answer = question.getAnswer();

			for (int i = 0; i < answer.length; i++) {
				answerStr += answer[i];
				if (i < answer.length - 1)
					answerStr += "|";
			}
			
			String[] option = question.getOption();
			for (String optionValue : option) {
				options.add(createOptionDTO(optionValue));

			}

			questionDTO.setOptionDTO(options);
		}
		
		questionDTO.setAnswer(answerStr);		

		sectionDTO.setQuestionDTO(Arrays.asList(questionDTO));
		return sectionDTO;
	}

	private OptionDTO createOptionDTO(String option) {
		OptionDTO optionDTO = new OptionDTO();
		Options options = new Options();
		optionDTO.setOption(option);

		optionDTO.setOptionsID(options.getOptionID());

		return optionDTO;
	}

}
