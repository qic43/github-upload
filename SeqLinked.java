// CS 0445 Spring 2019
// Demonstration of Recursive Sequential Search using a linked list.  Compare this
// to the array version in SeqSDemo.java
 
// Note that the primary functionality of this program is incorporated into a
// constructor method.  The main() method simply creates a SeqSLinked object.  This
// allows the "front" variable to be an instance variable so that the loadData() 
// instance method can initialize it properly.  Note that we could NOT initialize the
// list by passing "front" as a parameter to a static method, since Java parameters are
// passed by value -- we would not be able to assign a new address to the front
// argument from within the method.

import java.io.*;

public class SeqSLinked
{
	// Node is a separate, generic type.  See Node.java
	private Node<Integer> front;

	public SeqSLinked() throws IOException
	{
		BufferedReader BR = new BufferedReader(
					new InputStreamReader(System.in));
		System.out.println("Please enter size of the list:");
		int num = Integer.parseInt(BR.readLine());
		loadData(num);
		System.out.println("The data is: ");
		Node<Integer> temp = front;
		while (temp != null)
		{
			System.out.print(temp.getData());
			if (temp.getNextNode() != null)
				System.out.print(", ");
			temp = temp.getNextNode();
		}
		System.out.println();

		System.out.println("Please enter the item to search for:");

		Integer key = new Integer(BR.readLine());
		int loc = linkedSearch(front, key);
		if (loc >= 0)
			System.out.println("Iteratively, " + key + " found at Node " + loc);
		else
			System.out.println(key + " was not found ");

		loc = recLinkedSearch(front, key, 0);
		if (loc >= 0)
			System.out.println("Recursively, " + key + " found at Node " + loc);
		else
			System.out.println(key + " was not found ");

	}

	public static void main (String [] args) throws IOException
	{
		SeqSLinked prog = new SeqSLinked();
	}

	// Note that this is an instance method.  See comment above about initialization.
	// Note also how this initialization is done -- we use a temporary reference to
	// trace down the list as we add new nodes at the end.  This enables use to create
	// the list in the order that the data is generated.  If we inserted new nodes at
	// the front (as with the Bag or Stack) the order of the data in the list would be
	// the reverse of how it was generated.
	public void loadData(int num)
	{
		Node<Integer> temp = null;
		front = null;
		for (int i = 0; i < num; i++)
		{
			Integer item = new Integer((int) (Math.random() * 50000));
			// Special case for empty list -- watch for these cases.
			if (front == null)
			{
				front = new Node<Integer>(item);
				temp = front;
			}
			else
			{
				temp.setNextNode(new Node<Integer>(item));
				temp = temp.getNextNode();
			}
		}
	}

	// Iterative sequential search of the list.  Note that we do not need a new temp
	// variable here since "list" is a parameter and reassigning "list" will not affect
	// the "front" instance variable.
	public static <T extends Comparable<? super T>> int linkedSearch(Node<T> list, T key)
	{
		int loc = 0;
		while (list != null)
		{
			if (list.getData().compareTo(key) == 0)
				return loc;
			else
			{
				list = list.getNextNode();
				loc++;
			}
		}
		if (list == null)
			return -1;
		else
			return loc;
	}

	// Recursive sequential search of the list.  Note the order of the cases -- just as
	// with the array this order matters.  If the list is null then trying to access
	// a method would generate a NullPointerException.  Note how simple and elegant the
	// recursive solution is.  Despite this elegance, we would not actually use it due
	// to the overhead of the recursive calls.
	public static <T extends Comparable<? super T>> int recLinkedSearch(Node<T> list, T key, int loc)
	{
		if (list == null)
			return -1;
		else if (list.getData().compareTo(key) == 0)
			return loc;
		else
			return (recLinkedSearch(list.getNextNode(), key, loc+1));
	}
}