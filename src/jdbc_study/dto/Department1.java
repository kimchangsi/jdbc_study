package jdbc_study.dto;

public class Department1 {
	private int deptNo;
	private String deptName;
	private int floor;

	public Department1() {
		// TODO Auto-generated constructor stub
	}

	public Department1(int deptNo) {
		this.deptNo = deptNo;
	}

	public Department1(int deptNo, String deptName, int floor) {
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.floor = floor;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return String.format("Department [deptNo=%s, deptName=%s, floor=%s]", deptNo, deptName, floor);
	}
	
	public Object[] toArray() {
		return new Object[]{deptNo, deptName, floor};
	}

}
