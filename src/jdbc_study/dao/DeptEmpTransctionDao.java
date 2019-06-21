package jdbc_study.dao;

import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;

public interface DeptEmpTransctionDao {
	int trInsertEmpAndDept(Employee emp, Department dept);
}
