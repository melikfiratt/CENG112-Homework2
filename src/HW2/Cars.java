package HW2;

public class Cars<T> implements DequeInterface<T> {
	private T[] queue;
	private int frontIndex;
	private int backIndex;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	private int numberOfEntries;
	public Cars() {
		this(DEFAULT_CAPACITY);
		
	}
	public Cars(int initialCapacity) {
		
		if (initialCapacity<= MAX_CAPACITY) {
			@SuppressWarnings("unchecked")
			T[] tempQueue = (T[])new Object[initialCapacity+1];
			queue = tempQueue;	
			frontIndex = 0;
			backIndex = initialCapacity;
			initialized = true;
		}
		else {
			throw new IllegalStateException("Attempt to create a bag with over max allowed capacity."); 
		}
		
	}
	@Override
	public void addToFront(T newEntry) {
		checkInitialization();
		ensureCapacity();
		 if (frontIndex == -1) {
	            frontIndex = 0;
	            backIndex = 0;
	            
	        }
	 
	        // front is at first position of queue
	     else if (frontIndex == 0)
	         frontIndex = queue.length-1;
	 
	     else // decrement front end by '1'
	       frontIndex = (frontIndex - 1)%queue.length;
	 
	        // insert current element into Deque
	     queue[frontIndex] = newEntry;
	     numberOfEntries++;
		
		
	}

	@Override
	public void addToBack(T newEntry) {
		checkInitialization();
		ensureCapacity();
		backIndex = (backIndex+1)%queue.length;
		queue[backIndex]= newEntry;
		numberOfEntries++;
		
		
	}
	private void checkInitialization() {
		if(!initialized) {
			throw new SecurityException("Array bag object is not initialized properly.");
			
		}
	}
	
	
	public T[] toArray() {
	    checkInitialization();
	    
	    @SuppressWarnings("unchecked")
	    T[] result = (T[]) new Object[backIndex - frontIndex + 1];
	    
	    int resultIndex = 0;
	    for (int queueIndex = frontIndex; queueIndex <= backIndex; queueIndex++) {
	        result[resultIndex] = queue[queueIndex];
	        resultIndex++;
	    }
	    
	    return result;
	}
	

	@Override
	public T removeFront()   {
		checkInitialization();
		if (isEmpty()) {
			throw new IllegalStateException("Empty Queue");
		}
		else {
			T front = queue[frontIndex];
			queue[frontIndex] = null;
			frontIndex = (frontIndex +1)%queue.length;
			numberOfEntries--;
			return front;
		}
	}

	@Override
	public T removeBack() {
		checkInitialization();
		if (isEmpty()) {
			throw new IllegalStateException("Empty Queue");
		}
		else {
			T back = queue[backIndex];
			queue[backIndex] = null;
			backIndex = (backIndex -1)%queue.length;
			numberOfEntries--;
			return back;
		}
	}

	@Override
	public T getFront()  {
		checkInitialization();
		if (isEmpty()) {
			throw new  IllegalStateException("Empty Queue");
		}
		else {
			return queue[frontIndex];
		}
		
		
	}

	@Override
	public T getBack() {
		checkInitialization();
		if (isEmpty()) {
			throw new  IllegalStateException("Empty Queue");
		}
		else {
			return queue[backIndex];
		}
	}

	@Override
	public boolean isEmpty() {
		
		return frontIndex == ((backIndex +1)%queue.length);
	}

	@Override
	public void clear()  {
		while(!isEmpty()) {
			removeFront();
		}
		
	}
	private void ensureCapacity() {
		if (frontIndex == ((backIndex +2)%queue.length)){
			T[] oldQueue = queue;
			int oldSize = oldQueue.length;
			int newSize = 2*oldSize;
			if (newSize<= MAX_CAPACITY) {
				@SuppressWarnings("unchecked")
				T[] tempQueue = (T[])new Object[newSize];
				queue = tempQueue;	
				for (int i = 0; i<oldSize-1; i++) {
					queue[i] = oldQueue[frontIndex];
					frontIndex = (frontIndex+1)%oldSize;				
				}
				frontIndex = 0;
				backIndex = oldSize -2;						
				
			}
			else {
				throw new IllegalStateException("Attempt to create a bag with over max allowed capacity."); 
			}
			
			
		} 
		
	}
	public T getItem(int i) {
		return queue[i];
	}
	@Override
	public int getNumberOfEntries() {
		
		return numberOfEntries;
	}
	
	
	

}
