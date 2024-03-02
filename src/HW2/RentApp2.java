package HW2;

import java.util.Scanner;
import java.util.Random;


public class RentApp2 {
	//Firstly, we take two n and k values for car count and customer count

	private static Scanner myObj;
	private static Scanner myObj2;
	public static void main(String[] args) {
		myObj = new Scanner(System.in);
		myObj2 = new Scanner(System.in);
		System.out.print("Enter available car count, n: ");  
		int n= myObj.nextInt();
		System.out.print("Enter customer count, k: ");  
		int k= myObj2.nextInt();
		//Secondly, we created deque, queue and list for cars, customers, and occupied cars by order
		DequeInterface<Car> cars = new Cars<Car>(n);
		QueueInterface<Customer> customers = new Customers<Customer>(k);
		ListInterface<Car> occupiedCars = new OccupiedCars<Car>();
			
		//generating items , enqueue them into the queue and Deque.
		
		for (int i=0; i<n;i++) {
			
			String s=Integer.toString(i);
			String id = ("car"+ s); 
			double quality = getRandomDouble();
			int leftOccupancy =  0;		
			String rentedBy = null;
			Car newCar = new Car(id, quality, leftOccupancy, rentedBy);
			cars.addToBack(newCar);
			
		}
		for(int c = 0; c<k; c++) {
            String m = Integer.toString(c);
            String id = ("Customer" + m);
            double threshold = getRandomDouble();
            String idNumber = id;
            Customer newCustomer = new Customer(threshold, idNumber);
            customers.enqueue(newCustomer);
            
        }
		
//For simulation We created 3 while loops for each Customer, Car, and Day.	
		
		int day = 1;
		//the simulation should end when the customers queue is empty, this is the day loop.
		
		while(!customers.isEmpty()) {
			
			System.out.print("\n"+"*******Day"+day+"*******");
			int entryNumberCar = cars.getNumberOfEntries();
			int v = 1;
			
			//this the the car loop for each day, we checked if cars queue is empty or the customers is empty
			//also we checked if all cars will be shown just once for the customer with the V value that is incremented by one each loop
		
			while(!(customers.isEmpty())&&!(v>entryNumberCar)) {
				
				
				Car car= cars.removeFront();
				System.out.print("\n");
				System.out.print("Current"+ " "+car+" "+car.getQuality()+" " +"is offering to"+"\n");
				boolean accepted = false;
				int entryNumber = customers.getNumberOfEntries();
				int m = 1;

				//this is the customer loop for each car also We checked if the customer loop is empty or the customer accepts the car 
			
				while((!accepted)&&!(m>entryNumber)){
					
					Customer customer = customers.dequeue();
				
					//if accepted 
					
					if (customer.getThreshold()<=car.getQuality()) {
						car.setLeftOccupancy(getRandomInt());
						car.setRentedBy(customer.toString());
						occupiedCars.add(car);
						accepted = true;
						System.out.print("\t"+ "Current"+ " "+ customer +" "+customer.getThreshold()+ "   accepted"+  "\n");
					
					}
					
					//if rejected
					
					else {
						System.out.print("\t"+"Current"+ " "+ customer +" "+customer.getThreshold()+ "   not accepted"+"\n");
						m++;
						double newThreshold = customer.getThreshold()*0.9;
						newThreshold = Math.round(newThreshold * 100.0) / 100.0;
						customer.setThreshold(newThreshold);
						customers.enqueue(customer);	
					}					
				}
				
				//when the customer loop ends we wanted to reset the queue as the beginning so we created a loop enqueue and dequeue at the same time
				
				for (int j = 0; j<entryNumber-m; j++ ) {
					Customer myCust = customers.dequeue();
					customers.enqueue(myCust);		
				}
				
				//if one car rejected by all customer then enqueue that car for the next day loop
				
				if((!accepted)) {
					
					cars.addToBack(car);
					System.out.print("\t"+ "*not accepted by any customer*" +"\n");
				}
				v++;
				
			}
			//displaying if there are still customers
			System.out.print("\n"+"All cars have seen" + "\n");
			if (!customers.isEmpty()) {
				System.out.print("But there are still customers waiting." +"\n");
			}
			
			day++;
			//displaying the rented cars
			System.out.print("\n"+"Rented cars:");
			for (int l = 1; l<=occupiedCars.getLength(); l++) {
				Car occCar = occupiedCars.getEntry(l);
				System.out.print("\n"+"\t"+occCar+" by "+occCar.getRentedBy()+" occupancy="+occCar.getLeftOccupancy());
				
			}
			//displaying the available cars
			System.out.print("\n"+"Available cars:");
			for (int i = 0; i<n+1; i++) {
				Car x = cars.getItem(i);
				if (!(x==null)) {
					System.out.print("\n"+cars.getItem(i));
					
				}
					
			}
			
			//end of each day decrement the left occupancy values for each occupied cars and if its 0 add them into the beginning of the car queue
			
			for (int s = occupiedCars.getLength(); s>=1; s--) {
				int newOcc = (occupiedCars.getEntry(s)).getLeftOccupancy()-1;
				occupiedCars.getEntry(s).setLeftOccupancy(newOcc);
				if ((newOcc == 0)) {
					cars.addToFront(occupiedCars.getEntry(s));
					occupiedCars.remove(s);
				}	
			}
			System.out.print("\n");
			System.out.print("*********End Of Day*********"+ "\n");
	}
	System.out.print("All customers rented a car.");	
				
	}
	//method for generating random doubles 	between 1 and 3
	private static double getRandomDouble() {
		 double min = 1.00;
	     double max = 3.00;

	     Random random = new Random();
	     double randomValue = min + (max - min) * random.nextDouble();
	     randomValue = Math.round(randomValue * 100.0) / 100.0;
	     return randomValue;
	        
	}
	//method for generating integers from 1 to 5
	private static int getRandomInt() {
		Random random = new Random();
        int randomInt = random.nextInt(5) + 1;
	    return randomInt;
	        
	}
	
}


