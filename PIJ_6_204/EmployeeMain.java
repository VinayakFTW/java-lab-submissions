import java.util.*;
public class EmployeeMain {
    public static void main(String[] args) {

        Payroll p = new Payroll(5000, 10000, 2000, 3000, 8000, 4000);

        FullTimeEmployee emp1 = new FullTimeEmployee(
                87, "Aditya", "ABCDE1234", "01-01-2023",
                "Engineer", 101, "SWE", 50000
        );

        ContractEmployee emp2 = new ContractEmployee(
                67, "Ravi", "PQRSX567Z", "01-02-2023",
                "Consultant", 102, "Contract", 160, 500
        );
        FullTimeEmployee emp3 = new FullTimeEmployee(
                88, "Anushka", "RSTG1236", "01-08-2023",
                "Recruiter", 105, "HR", 70000
        );

        Manager mgr = new Manager(
                68, "Neha", "LMNOP987K", "01-03-2022",
                "Manager", 103, "SWE", 80000, 10000, 7000
        );

        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(mgr);

        // 🔹 Display Details
        for (Employee e : employees) {
            System.out.println("------------ Employee Details ------------");
            System.out.println("ID: " + e.employeeId);
            System.out.println("Name: " + e.name);
            System.out.println("Designation: " + e.designation);
            System.out.println("Role: " + e.role);
            System.out.println("Department ID: " + e.deptId);

            // Dynamic Method Dispatch
            System.out.println("CTC: " + e.calcCTC(p));

            System.out.println("------------------------------------------\n");
    }
}}