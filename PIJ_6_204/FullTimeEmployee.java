class FullTimeEmployee extends Employee {
    double baseSalary;

    FullTimeEmployee(int employeeId, String name, String PAN, String joiningDate,
                     String designation, int deptId, String role, double baseSalary) {
        super(employeeId, name, PAN, joiningDate, designation, deptId, role);
        this.baseSalary = baseSalary;
    }

    @Override
    double calcCTC(Payroll p) {
        double ctc = baseSalary;

        switch (role) {
            case "SWE":
                ctc += p.perfBonus;
                break;

            case "HR":
                ctc += p.hiringCommission;
                break;

            default:
                break;
        }

        ctc += p.healthInsurance + p.pfContribution + p.stockOption;

        return ctc;
    }
}