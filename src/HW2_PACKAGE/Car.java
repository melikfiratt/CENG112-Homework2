package HW2_PACKAGE;

public class Car {
	private String id;
	private double quality;
	private int leftOccupancy;
	
	public Car(String id, double quality, int leftOccupancy) {
		this.id = id;
		this.quality = quality;
		this.leftOccupancy = leftOccupancy;
	}
    
	@Override
	public String toString() {
		return this.id;
				
	}
	//I wrote a getter method for the garbage type and the garbage amount
	public String getId() {
		return id;
	}
	public double getQuality() {
		return quality;
		}
	public int getLeftOccupancy() {
		return leftOccupancy;
		}
	
	public void setLeftOccupancy(int occupancy) {
		this.leftOccupancy = occupancy;
		}
	@Override	
	public boolean equals(Object obj) {
		
		return (this==obj);
	}

}
