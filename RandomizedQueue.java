/*
*  @author Kevin Wayne
*
*  @param <Item> the generic type of an item in this queue
*/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

   private int capacity = 10;
   private int element_count = 0;
   private Item[] queue;

   /**
    * Initializes an empty queue.
    */
@SuppressWarnings("unchecked")
public RandomizedQueue() {
	queue = (Item[]) new Object[capacity];
   }

   /**
    * Returns true if this queue is empty.
    *
    * @return {@code true} if this queue is empty; {@code false} otherwise
    */
   public boolean isEmpty() {
       return element_count==0;
   }

   /**
    * Returns the number of items in this queue.
    *
    * @return the number of items in this queue
    */
   public int size() {
       return element_count;
   }

   /**
    * Returns the item least recently added to this queue.
    *
    * @return the item least recently added to this queue
    * @throws NoSuchElementException if this queue is empty
    */
   public Item sample() {
       if (isEmpty()) throw new NoSuchElementException("Queue empty");
       return  queue[StdRandom.uniform(0,element_count)];
   }

   /**
    * Adds the item to this queue.
    *
    * @param  item the item to add
    */
   public void enqueue(Item item) {
	   if (item == null) throw new java.lang.NullPointerException();
	   resize();
       queue[element_count++] = item;
   }
/**
    * Removes and returns the item on this queue that was least recently added.
    *
    * @return the item on this queue that was least recently added
    * @throws NoSuchElementException if this queue is empty
    */
   /**
 * @return
 */
public Item dequeue() {
       if (isEmpty()) throw new NoSuchElementException("Queue empty");
       int rnd_index = StdRandom.uniform(element_count);
       Item rtn = queue[rnd_index];
       queue[rnd_index] = null;
       exchange(rnd_index, element_count-1);
       element_count--;
       resize();
       return rtn;
   }
   
   private void resize() {
	if (element_count<capacity) return;
	int new_size;
    if (element_count > 0 && element_count <= queue.length/4) 
    	new_size = queue.length/2; 
    else new_size = queue.length*2;
	@SuppressWarnings("unchecked")
	Item[] copy = (Item[]) new Object[new_size];
	for(int i=0;i<element_count;i++) 
		copy[i] = queue[i];
	queue = copy;
	
    }
   
   private void exchange( int i, int j){
	    Item swap=queue[i];
	    queue[i]=queue[j];
	    queue[j]=swap;
	}

   /**
    * Returns a string representation of this queue.
    *
    * @return the sequence of items in FIFO order, separated by spaces
    */
   public String toString() {
       StringBuilder s = new StringBuilder();
       for (Item item : this) {
           s.append(item);
           s.append(' ');
       }
       return s.toString();
   } 

   /**
    * Returns an iterator that iterates over the items in this queue in FIFO order.
    *
    * @return an iterator that iterates over the items in this queue in FIFO order
    */
   public Iterator<Item> iterator()  {
       return new RandomizeIterator<Item>();  
   }

   // an iterator, doesn't implement remove() since it's optional
   private class RandomizeIterator<Item> implements Iterator<Item> {
	   int[] indices ;
	   int i = 0;
	   
       public RandomizeIterator() {
    	  //create a random indices array
    	   int i = 0;
    	   indices = new int[element_count];
    	   for(int j=0;j<element_count;j++) 
    			   indices[j] = j;
    	   StdRandom.shuffle(indices);
       }

       public boolean hasNext()  { return i<indices.length;}
       public void remove() { throw new UnsupportedOperationException();  }

       @SuppressWarnings("unchecked")
	   public Item next() {
           if (!hasNext()) throw new NoSuchElementException();
           Item item =  (Item) queue[indices[i++]];
           return item;
       }
   }


   /**
    * Unit tests the {@code Queue} data type.
    *
    * @param args the command-line arguments
    */
   public static void main(String[] args) {
       RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue();
       StdOut.println("Queue is empty: " + randomizedQueue.isEmpty());
       for (int i = 0; i < 20; i++)  {
           randomizedQueue.enqueue(i);
           if (i % 3 == 0) {
               randomizedQueue.dequeue();
           }
       }
//       for (int i = 0; i < 10; i++) StdOut.println(StdRandom.uniform(10));
       StdOut.println("Size: " + randomizedQueue.size());
       for (int i : randomizedQueue)
    	   StdOut.println(i);
       StdOut.println();
       StdOut.print("Random pick ups: ");
       for (int j = 0; j < 5; j++) {
           StdOut.print(randomizedQueue.sample() + " ");
       }
   }
}

