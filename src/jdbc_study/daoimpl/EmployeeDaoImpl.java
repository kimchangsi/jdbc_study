package jdbc_study.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.jdbc.authentication.MysqlClearPasswordPlugin;

import jdbc_study.dao.EmployeeDao;
import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;
import jdbc_study.jdbc.MySQLjdbcUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	static final Logger log = LogManager.getLogger();

	@Override
	public List<Employee> selectEmployeeByAll() throws SQLException {
		String sql = "select e.empno, e.empname, e.title, m.empno meno , m.empname mname , e.salary, e.dno, d.deptname , e.pic " + 
				"from employee e left join employee m on e.manager=m.empno join department d on e.dno=d.deptno order by salary desc;";
		List<Employee> lists = new ArrayList<Employee>();

		try (Connection conn = MySQLjdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			log.trace(pstmt);
			while (rs.next()) {
				lists.add(getEmployeefull(rs));
			}
		}
		return lists;
	}

	private Employee getEmployeefull(ResultSet rs) throws SQLException {
		Employee manager = new Employee(rs.getInt("meno"));
		manager.setEmpName(rs.getString("mname"));
		
		Department dept = new Department(rs.getInt("dno"));
		dept.setDeptName(rs.getString("deptname"));
		return new Employee(rs.getInt("empno"), rs.getString("empname"), rs.getString("title"),
				manager, rs.getInt("salary"), dept,
				rs.getBytes("pic"));
	}
	
	private Employee getEmployee(ResultSet rs) throws SQLException {

		return new Employee(rs.getInt("empno"), rs.getString("empname"), rs.getString("title"),
				new Employee(rs.getInt("manager")), rs.getInt("salary"), new Department(rs.getInt("dno")),
				rs.getBytes("pic"));
	}

	@Override
	public Employee selectEmployeetByNo(Employee employee) throws SQLException  {
		String sql = "select empno, empname, title, manager, salary, dno, pic from employee where empno = ?";
		Employee emp = null;
		try (Connection conn = MySQLjdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
			log.trace(pstmt);
			pstmt.setInt(1, employee.getEmpNo());
			try(ResultSet rs = pstmt.executeQuery();){
				if (rs.next()) {
					emp = getEmployee(rs);
				}
			}
			return emp;
		}

	}


	@Override
	public int insertEmployee(Employee employee) throws SQLException {
		log.trace("insertEmployee()");
		String sql = "insert into employee values(?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = MySQLjdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setInt(1, employee.getEmpNo());
			pstmt.setString(2, employee.getEmpName());
			pstmt.setString(3, employee.getTitle());
			pstmt.setInt(4, employee.getMananger().getEmpNo());
			pstmt.setInt(5, employee.getSalary());
			pstmt.setInt(6, employee.getDno().getDeptNo());
			pstmt.setBytes(7, employee.getPic());
			log.trace(pstmt);
			return pstmt.executeUpdate();
		}

	}

	@Override
	public int deleteEmployee(Employee employee) throws SQLException {
		String sql = "delete from employee where empno = ?";
		try (Connection conn = MySQLjdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, employee.getEmpNo());
			return pstmt.executeUpdate();
		}
	}

	@Override
	public int updateEmployee(Employee employee) throws SQLException {
		log.trace("updateEmployee()");
		String sql = "update employee set empname=?, title=?, manager=?,salary=?,dno=?,pic=? where empno=?";

		try (Connection conn = MySQLjdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, employee.getEmpName());
			pstmt.setString(2, employee.getTitle());
			pstmt.setInt(3, employee.getMananger().getEmpNo());
			pstmt.setInt(4, employee.getSalary());
			pstmt.setInt(5, employee.getDno().getDeptNo());
			pstmt.setBytes(6, employee.getPic());
			pstmt.setInt(7, employee.getEmpNo());
			return pstmt.executeUpdate();
		}
	}

}
