package edu.eci.pdsw.validator;

import java.util.Optional;

import edu.eci.pdsw.model.Employee;
import edu.eci.pdsw.model.SocialSecurityType;

/**
 * Utility class to validate an employee's salary
 */
public class SalaryValidator implements EmployeeValidator {

	/**
	 * {@inheritDoc}}
	 */
	public Optional<ErrorType> validate(Employee employee) {

		if (employee.getSalary() > 1500 && employee.getSocialSecurityType().equals(SocialSecurityType.SISBEN)) {
			return Optional.of(ErrorType.INVALID_SISBEN_AFFILIATION);
		} else if (employee.getSalary() > 10000 && employee.getSocialSecurityType().equals(SocialSecurityType.EPS)) {
			return Optional.of(ErrorType.INVALID_EPS_AFFILIATION);
		} else {
			return Optional.empty();
		}
	}
}
