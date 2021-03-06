package jburich2740ex3g;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.DefaultListModel;

public class PayrollObjMapper {
	private String fileName;
	private PrintWriter outputFile;
	private Scanner inputFile;
	private File file;
	
	public PayrollObjMapper(String fileName) {
		super();
		this.fileName = fileName;
	}
	
	public boolean openInputFile() {
		boolean fileOpened = false;
		
		// Open the file.
	    try {
	    	File file = new File(this.fileName);
			fileOpened = file.exists();
					
			if (fileOpened) {
				// Open the file.
			    this.inputFile = new Scanner(file);
			}
	    }
	    catch (IOException e) {}
		
		return fileOpened;
	}
	
	public boolean openOutputFile() {
		boolean fileOpened = false;
		try {
			outputFile = new PrintWriter(this.fileName);
			fileOpened = true;
		}
		catch (IOException e) {}
		
		return fileOpened;
	}
	
	public void closeInputFile(){
		if (this.inputFile != null) this.inputFile.close();
	}
	
	public void closeOutputFile(){
		if (this.outputFile != null) this.outputFile.close();
	}
	
	public Payroll getNextPayroll() {
		Payroll p = null;
		
//		String textline = this.inputFile.nextLine();
//		int id = Integer.parseInt(textline);
//		String name = inputFile.nextLine();
//		textline = this.inputFile.nextLine();
//		double payRate = Double.parseDouble(textline);
//		textline = this.inputFile.nextLine();
//		double hours = Double.parseDouble(textline);
//		p = new Payroll(id, name, payRate, hours);
		
		int id = 0;
		String name = "";
		double payRate = 0.0;
		double hours = 0.0;
		
		try {
			String textline = this.inputFile.nextLine();
			id = Integer.parseInt(textline);
			name = inputFile.nextLine();
			textline = this.inputFile.nextLine();
			payRate = Double.parseDouble(textline);
			textline = this.inputFile.nextLine();
			hours = Double.parseDouble(textline);
			p = new Payroll(id, name, payRate, hours);
		}
		catch (NoSuchElementException e){}
		catch (NumberFormatException e) {}
		
		return p;
	}
	
	public void writePayroll(Payroll payroll) {
		if(outputFile != null && payroll != null){
			outputFile.println(payroll.getId());
			outputFile.println(payroll.getName());
			outputFile.println(payroll.getPayRate());
			outputFile.println(payroll.getHours());
		}
	}
	
	public DefaultListModel getAllPayroll() {
		DefaultListModel payrollCollection = new DefaultListModel();
		
		if(this.openInputFile()) {
			while(this.inputFile.hasNext()) {
				Payroll p = this.getNextPayroll();
					if (p != null)
				payrollCollection.addElement(p);
			}
		}
		
		this.closeInputFile();
		return payrollCollection;
	}
	
	public void writeAllPayroll(DefaultListModel payrollCollection) {
		if(this.openOutputFile()){
			for(int i=0; i<payrollCollection.getSize(); i++) {
				Payroll p = (Payroll) payrollCollection.get(i);
				this.writePayroll(p);
			}
		}
		this.outputFile.close();
	}
}
