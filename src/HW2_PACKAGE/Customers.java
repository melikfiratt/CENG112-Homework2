package HW2_PACKAGE;

public class Customers<T> implements QueueInterface<T> {
	private T[] customersQueue;
	private int frontIndex;
	private int backIndex;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	
	public Customers() {
		this(DEFAULT_CAPACITY);
	}
	
	public Customers (int initialCapacity) {
		checkCapacity(initialCapacity);
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object [initialCapacity+1];
		customersQueue = tempQueue;
		frontIndex = 0;
		backIndex = initialCapacity;
		initialized = true;
	}

	private boolean checkCapacity(int initialCapacity) {
		if (initialCapacity > MAX_CAPACITY) {
			System.out.println("The initial capacity is bigger than max capacity.");
			return false;
		}
		else {
			return true;
		}
		
	}
	

	public void enqueue(T newEntry) {
		checkInitialization();
		ensureCapacity();
		backIndex = (backIndex + 1) % customersQueue.length;
		customersQueue[backIndex] = newEntry;
		
	}
	
	

	private void ensureCapacity() {
		if (frontIndex == ((backIndex + 2)% customersQueue.length))// if is full
			{
			T[] oldQueue = customersQueue;
			int oldSize = oldQueue.length;
			int newSize = 2 * oldSize;
			checkCapacity(newSize);
			@SuppressWarnings("unchecked")
			T[] tempQueue = (T[]) new Object [newSize];
			customersQueue = tempQueue;
			for (int index = 0; index < oldSize - 1; index++) {
				customersQueue[index] = oldQueue[frontIndex];
				frontIndex = (frontIndex + 1) % oldSize;
			}
			frontIndex = 0;
			backIndex = oldSize -2;	
		}	
	}

	private void checkInitialization() {
		if(!initialized) {
			throw new SecurityException("ArrayBag object is not initialized properly");
			}
		
	}
	
	
	
	public void displayCustomers() {
	    if (isEmpty()) {
	        System.out.println("No Customers in the queue.");
	    } else {
	        System.out.println("Customers in the queue:");
	        for (int i = frontIndex; i != (backIndex + 1) % customersQueue.length; i = (i + 1) % customersQueue.length) {
	            System.out.println(customersQueue[i]);
	        }
	    }
	}
	
	
	
	
	
	public T[] toArray() {
	    checkInitialization();
	    
	    @SuppressWarnings("unchecked")
	    T[] result = (T[]) new Object[backIndex - frontIndex + 1];
	    
	    int resultIndex = 0;
	    for (int queueIndex = frontIndex; queueIndex <= backIndex; queueIndex++) {
	        result[resultIndex] = customersQueue[queueIndex];
	        resultIndex++;
	    }
	    
	    return result;
	}

	
	@Override
	public T dequeue() {
		checkInitialization();
		if(isEmpty()) {
			throw new IllegalStateException();
		}
		else {
			T frontEntry = customersQueue[frontIndex];
			customersQueue[frontIndex] = null;
			frontIndex++;
			return frontEntry;
		
		}	
	}

	@Override
	public T getFront() {
		if(isEmpty()) {
			throw new IllegalStateException();//?EmptyQueueException
		}
		else {
			return customersQueue[frontIndex];
		}
	}

	@Override
	public boolean isEmpty() {
		return frontIndex == ((backIndex + 1) % customersQueue.length);
	}

	@Override
	public void clear() {
		while(!isEmpty()) {
			dequeue();
			
		}
		
	}
}