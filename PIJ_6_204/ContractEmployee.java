class ContractEmployee extends Employee {
    int noOfHours;
    double hourlyRate;

    ContractEmployee(int employeeId, String name, String PAN, String joiningDate,
                     String designation, int deptId, String role,
                     int noOfHours, double hourlyRate) {
        super(employeeId, name, PAN, joiningDate, designation, deptId, role);
        this.noOfHours = noOfHours;
        this.hourlyRate = hourlyRate;
    }

    @Override
    double calcCTC(Payroll p) {
        return noOfHours * hourlyRate;
    }
}