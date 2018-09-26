package edu.eci.pdsw.validator;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;

import edu.eci.pdsw.model.Employee;
import edu.eci.pdsw.model.SocialSecurityType;

import org.junit.Test;
import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;

/**
 * Test class for {@linkplain SalaryValidator} class
 */
public class SalaryValidatorTest {

	/**
	 * The class under test.
	 */
	private SalaryValidator validator = new SalaryValidator();

	/**
	 * {@inheritDoc}}
	 */
	@Test
	public void validateTest() {

		qt().forAll(employees()).check(employee -> {
			if (employee.getSalary() > 1500 && employee.getSocialSecurityType().equals(SocialSecurityType.SISBEN)) {
				return validator.validate(employee).get().equals(ErrorType.INVALID_SISBEN_AFFILIATION);
			} else if (employee.getSalary() > 10000 && employee.getSocialSecurityType().equals(SocialSecurityType.EPS)) {
				return validator.validate(employee).get().equals(ErrorType.INVALID_EPS_AFFILIATION);
			} else {
				return !validator.validate(employee).isPresent();
			}
		});
		
	}
	
	public Gen<Employee> employees() {
		return ids().zip(salaries(), ss(), (id, salary, socialSecurityType) -> new Employee(id, salary, socialSecurityType));
	}

	private Gen<Integer> ids() {
		return integers().between(1000, 100000);
	}
	
	private Gen<Long> salaries() {
		return longs().between(100, 50000);
	}
	
	private Gen<SocialSecurityType> ss() {
		return Generate.enumValues(SocialSecurityType.class);
	}
		
}
