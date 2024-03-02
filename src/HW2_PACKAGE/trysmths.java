package HW2_PACKAGE;

import java.util.Scanner;
import java.util.Random;


public class trysmths {

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
        int y=1;
        while (!cars.isEmpty()) {//we are surfing our cars array 
 		   Car myCar = cars.removeFront();// we are tooking the cars from our array
 		   System.out.println("myCar: "+ myCar);
 		   

 		   boolean checkOccupancy = (myCar.getLeftOccupancy()==0);
 		   
 		   while (checkOccupancy) {// while myCar is available
 			   
 			   boolean checkCustomers = customers.isEmpty();
 			   int x = 0;
 			   while(x != 5){//we are surfing our customers array
 				   x += 1;
 				   System.out.println(x);
 				   customers.displayCustomers();
 				   
 				   Customer myCustomer = customers.dequeue();
 				   customers.enqueue(myCustomer);
 				   
 				   
 				   
         		   
         		  
 			   }	

 			   
 			   
 		   }
 		   //end for customer ( all customers looked this car)
 		   //for (int i = 0; i <= customers.toArray().length - 1; i++) {
 			   //customers.enqueue(customers.dequeue());//
 		   }//end the while(cars is empty)
		  
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
       
    		  
    
    private static double getRandomDouble() {
        double min = 1.00;
        double max = 3.00;

        Random random = new Random();
        double randomValue = min + (max - min) * random.nextDouble();
        randomValue = Math.round(randomValue * 100.0) / 100.0;
        return randomValue;

   }

}
