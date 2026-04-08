// Abstract Root Class
abstract class Employee {
    int employeeId;
    String name;
    String PAN;
    String joiningDate;
    String designation;
    int deptId;
    String role;

    // Constructor
    Employee(int employeeId, String name, String PAN, String joiningDate,
             String designation, int deptId, String role) {
        this.employeeId = employeeId;
        this.name = name;
        this.PAN = PAN;
        this.joiningDate = joiningDate;
        this.designation = designation;
        this.deptId = deptId;
        this.role = role;
    }

    // Abstract Method
    abstract double calcCTC(Payroll p);
}