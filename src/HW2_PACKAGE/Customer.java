package HW2_PACKAGE;

public class Customer {
	private double threshold;
	private String idNumber;
	
	public Customer(double threshold, String idNumber) {
		this.setThreshold(threshold);
		this.setIdNumber(idNumber);
		
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	public String toString() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
}
