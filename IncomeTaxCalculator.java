/**
 * This program calculates the net monthly salary by subtracting the income tax from the gross monthly salary.
 * The income tax is calculated based on different tax brackets and rates.
 * If the gross income exceeds 50,000, an additional tax rate of 35% is applied.
 * The user is prompted to enter their gross monthly salary, and the program outputs the net monthly salary.
 */

import java.util.Scanner;

public class IncomeTaxCalculator {

    public static double IncomeTax(double grossIncome) {
        double[][] brackets = {
            {402, 0},
            {512, 5},
            {642, 10},
            {3642, 17.5},
            {20037, 25},
            {50000, 30}
        };
        double totalTax = 0;
        double remainingIncome = grossIncome;

        for (double[] bracket : brackets) {
            double upperLimit = bracket[0];
            double rate = bracket[1];

            if (remainingIncome > upperLimit) {
                double taxableIncome;
                if (bracket == brackets[0]) {
                    taxableIncome = upperLimit;
                } else {
                    double previousBracketUpperLimit = brackets[getBracketIndex(brackets, bracket) - 1][0];
                    taxableIncome = upperLimit - previousBracketUpperLimit;
                }

                double tax = taxableIncome * (rate / 100);
                totalTax += tax;
                remainingIncome -= taxableIncome;
            } else {
                double taxableIncome = remainingIncome;
                double tax = taxableIncome * (rate / 100);
                totalTax += tax;
                break;
            }
        }

        // Handle income exceeding 50,000
        if (grossIncome > 50000) {
            double extraIncome = grossIncome - 50000;
            double extraTax = extraIncome * 35 / 100;
            totalTax += extraTax;
        }

        return grossIncome - totalTax;
    }

    private static int getBracketIndex(double[][] brackets, double[] bracket) {
        for (int i = 0; i < brackets.length; i++) {
            if (brackets[i] == bracket) {
                return i;
            }
        }
        return -1; // Not found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your gross monthly salary: ");
        double grossIncome = scanner.nextDouble();
        double netIncome = IncomeTax(grossIncome);
        System.out.printf("Net monthly salary: %.2f\n", netIncome);
        scanner.close();
    }
}
