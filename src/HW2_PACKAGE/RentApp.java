package HW2_PACKAGE;

import java.util.Scanner;
import java.util.Random;


public class RentApp {

    public static void main(String[] args) {
        DequeInterface<Car> cars = new Cars<Car>();
        DequeInterface<Car> newCars = new Cars<Car>();
        QueueInterface<Customer> customers = new Customers<Customer>();     
        QueueInterface<Customer> newCustomers = new Customers<Customer>();  
        ListInterface<Car> occupiedCars = new OccupiedCars<Car>();
        
        Scanner myObj = new Scanner(System.in);
        Scanner myObj2 = new Scanner(System.in);
        System.out.print("Enter available car count, n: ");
        int n= myObj.nextInt();
        System.out.print("Enter customer count, k: ");
        int k= myObj2.nextInt();

        for (int i=0; i<n;i++) {

            String s=Integer.toString(i);
            String id = ("car"+ s); 
            double quality = getRandomDouble();
            int leftOccupancy =  0;
            Car newCar = new Car(id, quality, leftOccupancy);
            cars.addToBack(newCar);
        }
        
        for(int i = 0; i<n; i++) {
        	String m = Integer.toString(i);
        	String id = ("Customer" + m);
        	double threshold = getRandomDouble();
        	String idNumber = id;
        	Customer newCustomer = new Customer(threshold, idNumber);
        	customers.enqueue(newCustomer);
        }

    	/*her arabayý tek tek al : 1. arabayý aldým
        	tüm müþterileri gezdim 1 2 3 
        		1. if müþteri kabul ederse
          				müþteriyi sil arabayý occupiedcarsa at:
          					
          		2. else(hiçbir müþteri kabul etmezse)
          				araba1 addtoback
          			endcar1/ car1 tüm müþteriler bitti bir sonraki cara geç
          	tüm arabalar bir tur attý bir sonraki güne geç ??		
          
          	*/		
          	
       while (!customers.isEmpty()) { 
    	   boolean finished = false;
    	   int totalDay = 0;
    	   while(!finished) {
    		   
    		   
    		   while (!cars.isEmpty()) {//we are surfing our cars array
    			   
        		   Car myCar = cars.removeFront();// we are tooking the cars from our array
        		   // if all cars are unavailable then go nextday.. bunu yapmadým sonra kontrol et gerek var mý diye..
        		   boolean checkOccupancy = (myCar.getLeftOccupancy()==0);
        		   while (checkOccupancy) {// while myCar is available
        			   boolean checkCustomers = customers.isEmpty();      			   
        			   while(!checkCustomers){//we are surfing our customers array	
        				   Customer myCustomer = customers.dequeue();
	            		   if(myCustomer.getThreshold() <= myCar.getQuality()) {// if customer wants to take this car
	            			   occupiedCars.add(myCar);  //our car now is unavailable
	            			   //customers.dequeue(); GEREK YOK ZATEN Dequeue ettik yukarda
	            			   checkCustomers = true; // because of we should skip this car
	            			   checkOccupancy = false;
	            			while (!customers.isEmpty()) {
	            				newCustomers.enqueue(customers.dequeue());//because of we will change the car we are preparing+
	            				//+ our customers for priority of old customers.
	            			}
	            			   
	            			customers = newCustomers;
	            			
	            			while(!newCustomers.isEmpty()) {//we will clear the backup list
            					newCustomers.dequeue();
            					  }
            					  
            				      
	            		   }
	            		   else {// if customer doesn't want
	            			   myCustomer.setThreshold(myCustomer.getThreshold() * 0.9); // customer growing desperation
	            			   newCustomers.enqueue(myCustomer);//we are adding to backup list
	            			   if(customers.isEmpty()) {
	            				   newCars.addToBack(myCar);
	            				   
	            			   }   
	            		   }
	            		   if ((customers.isEmpty())&&(newCustomers.isEmpty())){//if there are no customers
	            			   finished = true;// finish the program.. burayý deðiþtir
	            			   checkCustomers = true;
	            			   checkOccupancy = false;
	            			   
	            		   }
        			   }	
  
        			   if (customers.isEmpty()) { //if customers empty and our backup customers not empty // if de yapabiliriz
        				   customers = newCustomers;// take the backup customer and we will skip to the next car
        				   checkOccupancy = false;// now we are changing our current car	
        				   while (!newCustomers.isEmpty()) {//we will clear the backup list
     						  newCustomers.dequeue();
     					  
     					  }
        			   }
        			   
        		   }
        		   //end for customer ( all customers looked this car)
        		   //for (int i = 0; i <= customers.toArray().length - 1; i++) {
        			   //customers.enqueue(customers.dequeue());//
        		   }//end the while(cars is empty)
    		   if (cars.isEmpty()){ //if all customer looks all cars or there are no available cars
    			   //dayOver = false; // a day is over
    			   totalDay += 1; //a day is over
    			   cars.addToBack(newCars.removeFront());
    			   /* burada occupied carslarýn hepsini alýp gün sayýsýný bir azaltcaz */
    			   
    		   }
        		   
        	   }
    	   }
    	   
    	  
       }
    
    private static double getRandomDouble() {
         double min = 1.00;
         double max = 3.00;

         Random random = new Random();
         double randomValue = min + (max - min) * random.nextDouble();
         randomValue = Math.round(randomValue * 100.0) / 100.0;
         return randomValue;

    }





}