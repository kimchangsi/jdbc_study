package jdbc_study.dto;

public class Employee {
	private int empNo;
	private String empName;
	private String title;
	private Employee mananger;
	private int salary;
	private Department dno;
	private byte[] pic;

	public Employee(int empNo) {
		this.empNo = empNo;
	}

	public Employee(int empNo, String empName, String title, Employee mananger, int salary, Department dno) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.mananger = mananger;
		this.salary = salary;
		this.dno = dno;
	}
	
	public Employee(int empNo, String empName, String title, Employee mananger, int salary, Department dno,byte[] pic) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.mananger = mananger;
		this.salary = salary;
		this.dno = dno;
		this.pic = pic;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Employee getMananger() {
		return mananger;
	}

	public void setMananger(Employee mananger) {
		this.mananger = mananger;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Department getDno() {
		return dno;
	}

	public void setDno(Department dno) {
		this.dno = dno;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	@Override
	public String toString() {
		return String.format("%s (%d)", empName, mananger.getEmpNo());
	}

	public Object[] toArray() {
		return new Object[] { empNo, empName, title, mananger.getEmpNo(), salary, dno.getDeptNo() };
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + empNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Employee))
			return false;
		Employee other = (Employee) obj;
		if (empNo != other.empNo)
			return false;
		return true;
	}
	
	
}
