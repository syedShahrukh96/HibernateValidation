package Com.shahrukh.hibernate.FirstProject;

import java.time.LocalDate;
import java.util.Set;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;



public class GetMain {

	  public static void main(String[] args) {
	    	
	    	
		
	LocalDate futureDate = LocalDate.of(2030, 1, 1);
	LocalDate pastDate = LocalDate.of(2020, 12, 23);
	
	isValid(new Employee(null, -3, null, futureDate, 123.123));
	isValid(new Employee("a", 100, "", pastDate, 123));
	isValid(new Employee("a$bcd&", 40, "abc", pastDate, -123.123 ));
	isValid(new Employee("Raj", 40, "raj@gmail.com", pastDate, 123454.32));
	
	  }
	
	static void isValid(Employee employee) {
		
		
		 ValidatorFactory vf = Validation.byDefaultProvider()
		            .configure()
		            .messageInterpolator(new ParameterMessageInterpolator()) // Use basic message interpolator
		            .buildValidatorFactory();
		Validator valid = vf.getValidator();
		
		
		
		Set<ConstraintViolation<Employee>> violation = valid.validate(employee);
		if(violation.isEmpty())
			System.out.println("Validation success \n");
		else {
			for(ConstraintViolation<Employee> v: violation) {
				System.out.println(v.getPropertyPath() + " : " + v.getMessage());
			}
			System.out.println("Validation Failed \n");
		}
	
	}
}
