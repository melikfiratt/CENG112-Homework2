package HW2;

public class Customers<T> implements QueueInterface<T> {
	private T[] customersQueue;
	private int frontIndex ;
	private int backIndex;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	private int numberOfEntries;
	
	public Customers() {
		this(DEFAULT_CAPACITY);
	}
	
	public Customers (int initialCapacity) {
		checkCapacity(initialCapacity);
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[initialCapacity+1];
		customersQueue = tempQueue;
		frontIndex = 0;
		backIndex = initialCapacity;
		initialized = true;
		numberOfEntries = 0;
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
		backIndex = (backIndex+1)%customersQueue.length;
		customersQueue[backIndex] = newEntry;
		numberOfEntries ++;
		
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

	@Override
	public T dequeue() {
		checkInitialization();
		if(isEmpty()) {
			throw new IllegalStateException();
		}
		else {
			T frontEntry = customersQueue[frontIndex];
			customersQueue[frontIndex] = null;
			frontIndex = (frontIndex + 1) % customersQueue.length;
			numberOfEntries --;
			return frontEntry;
		
		}	
	}

	@Override
	public T getFront() {
		checkInitialization();
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

	@Override
	public int getNumberOfEntries() {
		
		return numberOfEntries;
	}
}