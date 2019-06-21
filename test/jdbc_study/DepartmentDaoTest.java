package jdbc_study;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.daoimpl.DepartmentDaoImpl;
import jdbc_study.dto.Department;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) // test순서를 고정하겟다
public class DepartmentDaoTest {
	static final Logger log = LogManager.getLogger();
	static DepartmentDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception { // 테스트 전 호출
		log.trace("setUpBeforeClass()");
		dao = new DepartmentDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception { // 테스트 끝난후 호출
		log.trace("tearDownAfterClass()");
		dao = null;
	}

	@Test
	public void test01SelectDepartmentByAll() { 
		List<Department> lists = dao.selectDepartmentByAll();
		for (Department d : lists) {
			log.trace(d);
		}
		Assert.assertNotEquals(0, lists.size());

	}

	@Test
	public void test02SelectDepartmentByNo() throws SQLException { //select where test
		Department dto = new Department(1);
		Department selDept = dao.selectDepartmentByNo(dto);

		log.trace(selDept);
		Assert.assertNotNull(selDept);
	}
	
	@Test
	public void test03InsetDepartment() throws SQLException  { //insert test
		Department newDept = new Department(5,"마케팅",40);
		int res=dao.insertDepartment(newDept);
		Assert.assertNotEquals(-1,res); //res가 -1과 같지않다면 성공
	}
	
	@Test
	public void test0UpdateDepartment() throws SQLException  { //update test
		Department newDept = new Department(5,"마케팅",40);
		int res=dao.insertDepartment(newDept);
		Assert.assertNotEquals(-1,res); //res가 -1과 같지않다면 성공
	}
	
	@Test
	public void test05DeleteDepartment() throws SQLException  { //delete test
		Department newDept = new Department(5);
		int res=dao.deleteDepartment(newDept);
		Assert.assertNotEquals(-1,res); //res가 -1과 같지않다면 성공
	}

}
