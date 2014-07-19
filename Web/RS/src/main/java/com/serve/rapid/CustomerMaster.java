package com.serve.rapid;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.serve.rapid.domain.FieldAgent;
import com.serve.rapid.repository.FieldAgentRepository;

public class CustomerMaster {
	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				BeanConfiguration.class);
		FieldAgentRepository repo = context.getBean(FieldAgentRepository.class);
		FieldAgent fa = new FieldAgent();
		fa.setName("Test");
		fa.setContactNumber("9999999");
		fa.setType(Constants.UT_FIELDAGENT);
		repo.save(fa);
		
		/*CustomerRepository repository = context
				.getBean(CustomerRepository.class);
		repository.save(new Customer("Rajesh", "Bangalore"));
		repository.save(new Customer("Krishna", "Kannur"));
		System.out.println("CUSTOMER DETAILS");
		Iterable<Customer> customers = repository.findAll();
		for (Object customer : customers) {
			System.out.println(customer);
		}
		((AbstractApplicationContext) context).close();*/
		
		/*ChapterRepository repository = context
				.getBean(ChapterRepository.class);*/
		
		/*QuestionRepository qrepository = context
				.getBean(QuestionRepository.class);
		
		DiagnsoticClassDataCreator dCreator = new DiagnsoticClassDataCreator();
		
		dCreator.getDiagnosticData();
		List<String> classes = qrepository.findAvailableClasses();
		System.out.println(classes);*/
		String question = "What is another word for anticipate?";
		int length = question.length();
		int x = 25;
		int y = 35;
		String quesText = question.substring(0, x)+"!"+question.substring(x, length);
		String nextques = quesText.substring(0, y+1)+"~"+quesText.substring(y+1, quesText.length());
		String finalStr = nextques.replaceAll("!", "[b]");
		finalStr = finalStr.replaceAll("~", "[/b]");
		
/*	List<DataChapter> chapList = repository.findChaptersForClass();	
 * 
 * for (DataChapter dataChapter : chapList) {
			System.out.println(dataChapter.getChapterName());
			for (DataTopic dataTopic : dataChapter.getTopics()) {
				System.out.println(dataTopic.getTopicName());
				for (DataPassage dataPassage : dataTopic.getPassages()) {
					System.out.println(dataPassage.getPassageText());
					System.out.println("-------------------------"+"  "+dataPassage.getPassageNumber());
					for (DataQuestion dataQuestion : dataPassage.getQuestions()) {
						System.out.println(dataQuestion.getQuestionText());
						for (DataAnswer dataAnswer : dataQuestion.getAnswers()) {
							System.out.println(dataAnswer.getAnswer()+"  "+dataAnswer.getAnswerType());
						}
					}
				}
			}
		}*/
	}
}