import java.util.Scanner;


public class GasExpenses 
{
	public static void main(String[] args)
	{
		Scanner scn = new Scanner(System.in);
		double miles_p_gallon = 0;
		double miles_p_week = 0;
		double gas_price = 0;
		double miles_p_year = 0;
		double gallon_p_week = 0;
		double gallon_p_year = 0;
		double gas_expense_p_week = 0;
		double gas_expense_p_year = 0;
		double gas_price_after_goup = 0;
		
		System.out.println("GAS EXPENSES");
		System.out.println();
		System.out.print("How many miles do you drive per week?");
		miles_p_week = scn.nextDouble();
		System.out.print("How many miles per gallon does your auto get?");
		miles_p_gallon = scn.nextDouble();
		System.out.print("What is the current cost of gas?");
		gas_price = scn.nextDouble();
		miles_p_year = getMilesPerYear(miles_p_week);
		System.out.println();
		System.out.println("At " + miles_p_week + " miles per week, you will travel " + miles_p_year + " miles per year.");
		System.out.println("");
		gallon_p_week = getGallonPerWeek(miles_p_gallon, miles_p_week);
		System.out.println("Gallons per week: " + gallon_p_week + " gallons.");
		gallon_p_year = getGallonPerYear(gallon_p_week);
		System.out.println("Gallons per year: " + gallon_p_year + " gallons.");
		System.out.println();
		System.out.println("With gas at $" + gas_price + " per gallon, you will spend:");
		gas_expense_p_week = getGasExpensePerWeek(gallon_p_week, gas_price);
		System.out.println("Gas expense per week: $" + gas_expense_p_week);
		gas_expense_p_year = getGasExpensePerYear(gas_expense_p_week);
		System.out.println("Gas expense per year: $" + gas_expense_p_year);
		gas_price_after_goup = gas_price + 1;
		System.out.println();
		System.out.println("If gas goes up by one dollar per gallon to $" + gas_price_after_goup + " per gallon, you will"
				+ "spend:");
		gas_expense_p_week = getGasExpensePerWeek(gallon_p_week, gas_price_after_goup);
		System.out.println("Gas expense per week: $" + gas_expense_p_week);
		gas_expense_p_year = getGasExpensePerYear(gas_expense_p_week);
		System.out.println("Gas expense per year: $" + gas_expense_p_year);
	}
	
	public static double getGasExpensePerYear(double gas_expense_p_week)
	{
		return gas_expense_p_week / 7 * 365;
	}
	public static double getGasExpensePerWeek(double gallon_p_week, double gas_price)
	{
		return gallon_p_week * gas_price;
	}
	public static double getGallonPerWeek(double miles_p_gallon, double miles_p_week)
	{
		return 1 / miles_p_gallon * miles_p_week;
	}
	public static double getGallonPerYear(double gallon_p_week)
	{
		return gallon_p_week / 7 * 365;
	}
	public static double getMilesPerYear(double miles_p_week)
	{
		return miles_p_week / 7 * 365;
	}
}
