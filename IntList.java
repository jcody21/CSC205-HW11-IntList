/*
 * class IntList is an array-based implementation of a List data structure. 
 * It is the code base for a csc205 programming assignment.
 *
 * @creator John Cody
 * @created 02019.03.21
 */

public class IntList extends Object {

   public static final int NIL = -1;

   private static final int DFLT_CAPACITY = 8;
   private static final int GROWTH_FACTOR = 2;

   private int[] list;       // the list elements
   private int size;         // number of elements in list
 
   /**
    * Constructs an empty IntList having a DFLT_CAPACITY.
    */
   public IntList() {
      this(DFLT_CAPACITY);
   }


   /**
    * Adds an element to the end of this IntList.
    * The IntList grows if at capacity.
    *
    * @param n        value to add to this IntList
    * @return true    if value added
    * @return false   if value not added
    */
   public boolean add(int n) {
      if (size == list.length) 
         growIntList();
      if (n < 0)
         return false;
      list[size] = n;
      size++;
      return true;
   }

   /**
    * @return number of elements in this IntList
    */
   public int size() {
      return size;
   }

   /**
    * @return capacity of this IntList
    */
   public int capacity() {
      return list.length;
   }

   /**
    * Search value x in this IntList starting from
    * the start of the list.
    *
    * @param x        the value to search for in this IntList
    * @return index   index where x was found in this IntList
    * @return NIL     if x not in this IntList
    */
   public int search(int x) {
      if (size == 0) 
         return NIL;
      return search(x, 0);
   }

   /**
    * Sets the size of this IntList to zero.
    */
   public void clear() {
      size = 0;
   }

   /**
    * An IntList is empty if its size is zero.
    *
    * @return true     if size is 0
    * @return false    if size is !0
    */
   public boolean isEmpty() {
      return size == 0;
   }

   /**
    * Return a string representation of this IntList.
    *
    * @return String  
    */
   public String toString() {
      if (size == 0)
         return "()";
      StringBuffer sb = new StringBuffer("(");
      int limit = size - 1;
      int i = 0;
      for (; i < limit; i++)
         sb.append(list[i] + " ");
      sb.append(list[i] + ")");
      return sb.toString();
   }

   /**
    * TBI (To Be Implemented)
    *
    * Constructs an empty IntList having a client provided capacity.
    *
    * @param capacity in the interval [1, Integer.MAX_VALUE]
    * @throws IllegalArgumentException if capacity is negative
    */
   public IntList(int capacity) throws IllegalArgumentException {
       if(capacity < 0) 
           throw new IllegalArgumentException("\n*** capacity must be > 0");
       list = new int[capacity];
       size = 0;
   }

   /**
    * TBI (To Be Implemented)
    *
    * Instantiates a new list array and copies the elements
    * of the existing list array to the new list array. The
    * list array instance variable becomes a pointer to the
    * new list array.
    */
   public void growIntList() {
       int[] x = new int[list.length * GROWTH_FACTOR];
       for(int i = 0; i < list.length; i++)
           x[i] = list[i];
       list = x;
   }

   /**
    * TBI (To Be Implemented)
    *
    * Returns the first element from this IntList.
    *
    * @return first element, if this IntList is not empty
    * @return NIL, if this IntList is empty
    * 
    */
   public int car() {
       if(size == 0) 
           return NIL;
       return list[0];
   }

   /**
    * TBI (To Be Implemented)
    *
    * Returns a new IntList that is initialized with the
    * elements of this list excluding the first element.
    * The capacity (length) of the new IntList should
    * equal the capacity of this IntList.
    *
    * Example: If this IntList is (5 7 3 4), then cdr() 
    * returns an the IntList (7 3 4).
    *
    * @return new IntList
    */
   public IntList cdr() {
       IntList x = new IntList(list.length - 1);
       for(int i = 1; i < size; i++) {
           x.add(list[i]);
       }
       return x;
   }

   /**
    * TBI (To Be Implemented)
    *
    * Searches this IntList for the given parameter x
    * starting the search at index i.
    * 
    * @param x     the value to search for in this IntList
    * @param i     index from which to begin searching
    * @return index   if found, index where x was found
    * @return NIL     if invalid index or x not found
    */
   public int search(int x, int i) {
       if(i < 0 || i > list.length) 
           return NIL;
       for(; i < list.length; i++){
           if(x == list[i])
               return i;
       }
       return NIL;
   }

   /**
    * TBI (To Be Implemented)
    *
    * Checks if this IntList equals an IntList that is received as
    * a parameter. 
    *
    * Two IntLists are equal if they have the same size and
    * this.list[i] == that.list[i] for i in [0..list.size].
    * 
    * @param IntList    to compare against this IntList
    * @return true    if the two IntLists are equal
    * @return false   if the two IntLists are not equal
    */
   public boolean equals(IntList that) {
       if(size != that.size())
           return false;
       for(int i = 0; i < size; i++) {
           if(list[i] != that.car())
               return false;
           that = that.cdr();
       }
       return true;
   }

   /**
    * TBI (To Be Implemented)
    *
    * Deletes first occurrence of parameter n from this IntList.
    *
    * If an element is deleted and list not empty,
    * rotate remaining elements one element left
    * Example:  
    *    list[0] = 5, list[1] = 2, list[2] = 3, list[3] = 4  (size is 4)
    *    delete(2)   // returns true
    *    list[0] = 5, list[1] = 3, list[2] = 4  (size is 3)
    * 
    * @param n        value to delete
    * @return true    if n deleted
    * @return false   if n not deleted
    */
   public boolean delete(int n) {
       int i = search(n,0);
       if(i == NIL)
           return false;
       for(; i < list.length; i++){
           if(i == list.length-1) 
               list[i] = 0;
           else 
               list[i] = list[i+1];
       }
       size--;
       return true;
   }

   // the main() method is used to test class IntList

   public static void main(String[] argv) {

      int[] data  = { 4, -3, 0, 9, -8, 0, 7, 0 };

      // test growing of an IntList and the size(), capacity(),
      // and isEmpty() instance methods...

      IntList list = new IntList(data.length / 2);
      System.out.println("list (before adds): " + list + 
                         "\nisEmpty() = " + list.isEmpty() +
                         "; size() = " + list.size() +
                         "; capacity() = " + list.capacity());
      for (int i = 0; i < data.length; i++) {
         System.out.print("..." + data[i]);
         if (!list.add(data[i])) 
            System.out.print(" not");
         System.out.println(" added");
      }
      System.out.println("list (after adds): " + list + 
                         "\nisEmpty() = " + list.isEmpty() +
                         "; size() = " + list.size() +
                         "; capacity() = " + list.capacity());
      
      // test the car() and cdr() instance methods...

      IntList z = list;
      IntList y = null;
      int n = -1;
      do {
         if ((n = z.car()) == NIL)
            break;
         System.out.println("car" + z + " = " + z.car());
         y = z.cdr();
         System.out.println("cdr" + z + " = " + y);
         z = y;
      } while (true);

      // test the search() instance method...

      for (int i = 0; i < data.length; i++) {
         int x = data[i];
         System.out.print("search(" + x + ") ");
         int index = list.search(x);
         if (index == NIL)
            System.out.println("not found");
         else {
            System.out.print("found at index " + index);
            for (int j = index + 1; j < list.size; j = index + 1) {
               index = list.search(x, j);
               if (index == NIL)
                  break;
               System.out.print("..." + index);
            }
            System.out.println();
         }
      }

      // test the equals() instance method...

      IntList l2 = new IntList();
      for (int i = 0; i < data.length; i++)
         l2.add(data[i]);
      System.out.println(list + ".equals" + l2 + " = " + list.equals(l2));
      l2.delete(data[0]);   // partial test of delete() 
      System.out.println(list + ".equals" + l2 + " = " + list.equals(l2));

      // test the delete() instance method...

      System.out.println("list (before deletes): " + list);
      for (int i = 0; i < data.length; i++) {
         System.out.print("..." + data[i]);
         if (list.delete(data[i]))
            System.out.println(" deleted " + list);
         else
            System.out.println(" not found " + list);
      }
      System.out.println("list (after deletes): " + list);

      // test the IntList(int capacity) constructor...

      System.out.println("instantiate a negative capacity IntList...");
      try {
         IntList x = new IntList(-42);
      } catch (Exception e) {
         System.out.println(e);
      }
   }
}