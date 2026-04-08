class Manager extends FullTimeEmployee {
    double travelAllowance;
    double eduAllowance;

    Manager(int employeeId, String name, String PAN, String joiningDate,
            String designation, int deptId, String role,
            double baseSalary, double travelAllowance, double eduAllowance) {

        super(employeeId, name, PAN, joiningDate, designation, deptId, role, baseSalary);
        this.travelAllowance = travelAllowance;
        this.eduAllowance = eduAllowance;
    }

    @Override
    double calcCTC(Payroll p) {
        return baseSalary + p.perfBonus + travelAllowance + eduAllowance
                + p.healthInsurance + p.pfContribution + p.stockOption;
    }
}