package spring.di.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import spring.di.entity.Exam;

@Component("console")
public class InlineExamConsole implements ExamConsole {
	
	// 기본 생성자가 있어야  있어야한다.
	// 오버로드 생성자가 있으면 기본생성자 생성이 되질 않아서 바인딩이 일어나지 않는다.
	// settings.xml에 bean으로 exam이 정의되어있지 않으면
	// required = false일때
	// print 메서드에서 exam이 null로 처리가 된다.
	@Autowired(required = true)
//		@Qualifier("exam2")	
	private Exam exam;
	
	public InlineExamConsole() {
		System.out.println("constructor");
	}
	
	// Qualifier 어노테이션이 메서드 상단에 먹히질 않는다.
	// 매개변수 쪽에 위치시켜야한다.
//	@Autowired
//	public InlineExamConsole( @Qualifier("exam2") Exam exam) {
//		System.out.println("overload constructor");
//		this.exam = exam;
//		
//	}
	
	public InlineExamConsole(Exam exam) {
		System.out.println("overload constructor");
		this.exam = exam;
		
	}

	@Override
	public void print() {
		if(exam == null) 
			System.out.printf("total is %d, avg is %f \n", 0, 0.0);
		else 
			System.out.printf("total is %d, avg is %f \n", exam.total(), exam.avg());
	}

//	@Autowired
//	@Qualifier("exam2")	
	// settings.xml에서 id가 exam2인 것을 골라서 넣는다.
	@Override
	public void setExam(Exam exam) {
		System.out.println("setter injection");
		this.exam = exam;
	}
}
