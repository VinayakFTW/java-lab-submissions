class Payroll {
    double healthInsurance;
    double stockOption;
    double insuranceAmount;
    double pfContribution;
    double perfBonus;
    double hiringCommission;

    Payroll(double healthInsurance, double stockOption,
            double insuranceAmount, double pfContribution,
            double perfBonus, double hiringCommission) {

        this.healthInsurance = healthInsurance;
        this.stockOption = stockOption;
        this.insuranceAmount = insuranceAmount;
        this.pfContribution = pfContribution;
        this.perfBonus = perfBonus;
        this.hiringCommission = hiringCommission;
    }
}