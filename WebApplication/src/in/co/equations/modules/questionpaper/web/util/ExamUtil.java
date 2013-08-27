package in.co.equations.modules.questionpaper.web.util;

import in.co.equations.modules.questionpaper.dataaccess.model.QuestionBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ExamUtil {

	static Map<String, Map<Integer, String>> correctAnswersMap = new HashMap<String, Map<Integer, String>>();
	static Map<String, Map<Integer, String>> selectedAnswersMap = new HashMap<String, Map<Integer, String>>();

	public static void storeCorrectAns(String sectionName,
			List<QuestionBean> questionBeanList) {
		Map<Integer, String> ansMap = new TreeMap<Integer, String>();
		int questionNo = 1;
		for (QuestionBean questionBean : questionBeanList) {
			String answer = questionBean.getAnswer();
			String repaceAnswer = null;
			if (answer.contains("|")) {
				repaceAnswer = answer.replace('|', ',');
				answer = repaceAnswer;
			}
			ansMap.put(questionNo++, answer);
		}
		if (!correctAnswersMap.containsKey(sectionName)) {
			correctAnswersMap.put(sectionName, ansMap);
		}
	}

	public static void storeSelectedAns(String sectionName, int questionNo,
			String correctAnswer) {
		Map<Integer, String> ansMap = new TreeMap<Integer, String>();
		if (selectedAnswersMap.containsKey(sectionName)) {
			ansMap = selectedAnswersMap.get(sectionName);
		}
		ansMap.put(questionNo, correctAnswer);
		selectedAnswersMap.put(sectionName, ansMap);
	}

	public static int examValidation(String sectionName) {
		Map<Integer, String> selectedAnswers = selectedAnswersMap
				.get(sectionName);
		Map<Integer, String> correctAnswers = correctAnswersMap
				.get(sectionName);
		Set<Integer> keySet = selectedAnswers.keySet();
		int score = 0;

		for (Integer questionNo : keySet) {
			System.out.println("questionNo == " + questionNo);
			String selectedOption = selectedAnswers.get(questionNo);
			String correctOption = correctAnswers.get(questionNo);

			int compareTo = selectedOption.compareTo(correctOption);

			if (compareTo == 0) {
				++score;
			}
		}
		return score;

	}
}
