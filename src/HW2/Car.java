package HW2;

public class Car {
	private String id;
	private double quality;
	private int leftOccupancy;
	private String rentedBy;
	
	public Car(String id, double quality, int leftOccupancy, String rentedBy) {
		this.id = id;
		this.quality = quality;
		this.leftOccupancy = leftOccupancy;
		this.rentedBy = rentedBy;
	}
    
	@Override
	public String toString() {
		return this.id;
				
	}
	
	public String getId() {
		return id;
	}
	public double getQuality() {
		return quality;
		}
	public int getLeftOccupancy() {
		return leftOccupancy;
		}
	public String getRentedBy() {
		return rentedBy;
	}
	public void setRentedBy(String rentedBy) {
		this.rentedBy = rentedBy;
	}
	public void setLeftOccupancy(int occupancy) {
		this.leftOccupancy = occupancy;
		}
	@Override	
	public boolean equals(Object obj) {
		
		return (this==obj);
	}

}
