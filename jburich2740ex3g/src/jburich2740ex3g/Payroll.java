package jburich2740ex3g;
import java.text.DecimalFormat;

public class Payroll {

	private int id;
    private String name;
    private double payRate;
    private double hours;
    private double hoursWorked;

	public Payroll(int id, String name, double payRate) {
		super();
		this.id = id;
		this.name = name;
		this.payRate = payRate;
		this.hours = 0;	
	}
	
	public Payroll(int id, String name, double payRate, double hours) {
		super();
		this.id = id;
		this.name = name;
		this.payRate = payRate;
		this.hours = hours;	
	}

	public int getId() {
		return id;
	}
	
	public boolean setId(int id) {
		if (id > 100) {
			this.id = id;
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public boolean setName(String name) {
		if (name.isEmpty())
			return false;
		else {
			this.name = name;
			return true;
		}
	}
	
	public double getPayRate() {
		return payRate;
	}
	
	public boolean setPayRate(double payRate) {
		if (payRate >= 7.25 && payRate <= 100.0)
		{
			this.payRate = payRate;
			return true;
		}
		else {
			return false;
		}
	}
	
	public double getHours() {
		return hours;
	}
	
	public void setHours(double hours) {
		this.hours = hours;
	}
	
	public void sethoursWorked(double hours) {
		hoursWorked = hours;
	}
	

	@Override
	public String toString() {
		return id + " " + name + ", Pay rate=" + payRate;
	}

	public double calcGrossPay() {
		double grossPay,
			overtimePay;
		
		if (this.hours > 40) 
		{
			grossPay = 40 * payRate;
			overtimePay = (this.hours - 40) * (this.payRate * 1.5); 
			grossPay += overtimePay;
		}
		else 
		{
			grossPay = this.payRate * this.hours;
		}
		
		return grossPay;
		/*return hours * payRate;*/	
	} 
	
	public boolean addHours (double hours) {
		if (hours >= 0.1 && hours <= 20.0) {
			this.hours += hours;
			return true;
		}
		else {
			return false;
		}
	
	}
}
