package HW2;
import java.util.Arrays;


public class OccupiedCars<T> implements ListInterface<T> {
	private T[] occupiedCars;
	private int numberOfEntries;
	private boolean initialized = false;
 	private static final int DEFAULT_CAPACITY = 25;
 	private static final int MAX_CAPACITY = 10000;
 	public OccupiedCars() {
 		this(DEFAULT_CAPACITY);
 	}
 	 	

	public OccupiedCars(int initialCapacity) {
		if (initialCapacity<= MAX_CAPACITY) {
            @SuppressWarnings("unchecked")
            T[] tempList = (T[])new Object[initialCapacity + 1];
            occupiedCars = tempList;
            numberOfEntries = 0;
            initialized = true;
        }
        else {
            throw new IllegalStateException("Attempt to create a bag with over max allowed capacity."); 
        }
	
	}
	
	private void checkInitialization() {
        if(!initialized) {
            throw new SecurityException("Array bag object is not initialized properly.");

        }
	}


	@Override
	public void add(T newEntry) {
		checkInitialization();
		occupiedCars[numberOfEntries +1] = newEntry;
		numberOfEntries++;
		ensureCapacity();	
	}
	

	@Override
	public void add(int newPosition, T newEntry) {
		checkInitialization();
		if((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
			if(newPosition <= numberOfEntries)
				makeRoom(newPosition);
			occupiedCars[newPosition] = newEntry;
			numberOfEntries++;
			ensureCapacity();
			
		}
		else {
			throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
		}
		
	}

	@Override
	public T remove(int givenPosition) {
		checkInitialization();
		if((givenPosition >= 1) &&(givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			T result = occupiedCars[givenPosition];
			if (givenPosition < numberOfEntries)
				removeGap(givenPosition);
			 numberOfEntries--;
			 return result; 
			
		}
		else {
			throw new IndexOutOfBoundsException("Illegal posiiton given to remove operation.");
		}
	}
	private void removeGap(int givenPosition) {
		
			assert(givenPosition >= 1) && (givenPosition < numberOfEntries);
			int removedIndex = givenPosition;
			int lastIndex = numberOfEntries;
			for (int index = removedIndex; index < lastIndex; index++)
				occupiedCars[index] = occupiedCars[index + 1];
		}

	@Override
	public void clear() {
			for (int index = 1; index <= numberOfEntries - 1; index++) {
				occupiedCars[index] = null;
			}
	}


	@Override
	public T replace(int givenPosition, T newEntry) {
		checkInitialization();
		if((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			T originalEntry = occupiedCars[givenPosition];
			occupiedCars[givenPosition] = newEntry;
			return originalEntry;
		}
		else {
			throw new IndexOutOfBoundsException("Illegal Position given to replace operation.");
		}
	}
		
	public T getEntry(int givenPosition) {
        checkInitialization();
        if((givenPosition>=1)&&(givenPosition<=numberOfEntries)){
            assert !isEmpty();
            return occupiedCars[givenPosition];
        }
        else {
            throw new IndexOutOfBoundsException("Illegal position given to Get Entry operation");

        }
        }
	public T[] toArray() {
            checkInitialization();
            @SuppressWarnings("unchecked")
            T[] result = (T[])new Object[numberOfEntries];
            for (int i = 0; i<numberOfEntries; i++) {
                result[i] = occupiedCars[i+1];
                }
            return result;


    }
	public boolean contains(T anEntry) {
        checkInitialization();
        boolean found = false;
        int index = 1;
        while (!found&&(index<=numberOfEntries)) {
            if(anEntry.equals(occupiedCars[index])) {
                found = true;
            index++;

            }

        }
        return found;

    }
@Override
    public int getLength() {

        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {

        return numberOfEntries == 0;
    }

	
	private void makeRoom(int newPosition) {
        assert (newPosition >= 1)&&(newPosition<=numberOfEntries+1);
        int newIndex = newPosition;
        int lastIndex = numberOfEntries;

        for (int index = lastIndex; index>=newIndex; index--) {
            occupiedCars[index+1] = occupiedCars[index];
        }    
    }
	private void ensureCapacity() {
        int capacity = occupiedCars.length -1;
        if (numberOfEntries>=capacity) {
            int newCapacity = 2*capacity;
            if (newCapacity<= MAX_CAPACITY) {
                occupiedCars = Arrays.copyOf(occupiedCars, newCapacity+1);
            }
        }
    }
	
}

	