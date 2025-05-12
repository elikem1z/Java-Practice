/**
 * This program calculates the net monthly salary by subtracting the income tax from the gross monthly salary.
 * The income tax is calculated based on different tax brackets and rates.
 * If the gross income exceeds 50,000, an additional tax rate of 35% is applied.
 * The user is prompted to enter their gross monthly salary, and the program outputs the net monthly salary.
 */

import java.util.Scanner;

public class IncomeTaxCalculator {

    // Calculates the net income after applying tax brackets
    public static double IncomeTax(double grossIncome) {
        // Define tax brackets: {upper limit, tax rate}
        double[][] brackets = {
            {402, 0},      // Up to 402: 0% tax
            {512, 5},      // 403-512: 5% tax
            {642, 10},     // 513-642: 10% tax
            {3642, 17.5},  // 643-3642: 17.5% tax
            {20037, 25},   // 3643-20037: 25% tax
            {50000, 30}    // 20038-50000: 30% tax
        };
        double totalTax = 0; // Accumulates total tax
        double remainingIncome = grossIncome; // Tracks income left to tax

        // Loop through each tax bracket
        for (double[] bracket : brackets) {
            double upperLimit = bracket[0]; // Bracket upper limit
            double rate = bracket[1];       // Tax rate for this bracket

            if (remainingIncome > upperLimit) {
                double taxableIncome;
                if (bracket == brackets[0]) {
                    // For the first bracket, taxable income is up to its upper limit
                    taxableIncome = upperLimit;
                } else {
                    // For other brackets, taxable income is the difference between this and previous upper limit
                    double previousBracketUpperLimit = brackets[getBracketIndex(brackets, bracket) - 1][0];
                    taxableIncome = upperLimit - previousBracketUpperLimit;
                }

                // Calculate tax for this bracket and add to total
                double tax = taxableIncome * (rate / 100);
                totalTax += tax;
                remainingIncome -= taxableIncome; // Reduce remaining income
            } else {
                // If remaining income is within this bracket, tax all of it at this rate
                double taxableIncome = remainingIncome;
                double tax = taxableIncome * (rate / 100);
                totalTax += tax;
                break; // No more income left to tax
            }
        }

        // If income exceeds 50,000, apply extra 35% tax to the excess
        if (grossIncome > 50000) {
            double extraIncome = grossIncome - 50000;
            double extraTax = extraIncome * 35 / 100;
            totalTax += extraTax;
        }

        // Return net income after subtracting total tax
        return grossIncome - totalTax;
    }

    // Helper method to find the index of a bracket in the brackets array
    private static int getBracketIndex(double[][] brackets, double[] bracket) {
        for (int i = 0; i < brackets.length; i++) {
            if (brackets[i] == bracket) {
                return i;
            }
        }
        return -1; // Not found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create scanner for user input
        System.out.print("Enter your gross monthly salary: ");
        double grossIncome = scanner.nextDouble(); // Read gross income
        double netIncome = IncomeTax(grossIncome); // Calculate net income
        System.out.printf("Net monthly salary: %.2f\n", netIncome); // Output result
        scanner.close(); // Close scanner
    }
}
/*
Logic Explanation:

1. The program prompts the user to enter their gross monthly salary.
2. It calculates the net salary by subtracting the income tax from the gross salary.
3. The tax is calculated using several brackets:
    - Up to 402: 0% tax
    - 403 to 512: 5% tax
    - 513 to 642: 10% tax
    - 643 to 3642: 17.5% tax
    - 3643 to 20037: 25% tax
    - 20038 to 50000: 30% tax
    - Any amount above 50000: 35% tax on the excess
4. The program iterates through each bracket, calculating the tax for the portion of income in that bracket.
5. The total tax is subtracted from the gross income to get the net income.
6. The net monthly salary is displayed to the user.
/*
Version Control with Git:

1. Initialize a git repository (if not already done):
    git init

2. Check the status of your files:
    git status

3. Add the file to the staging area:
    git add IncomeTaxCalculator.java

4. Commit the changes with a message:
    git commit -m "Add IncomeTaxCalculator with tax calculation logic"

5. (Optional) View commit history:
    git log
*/
