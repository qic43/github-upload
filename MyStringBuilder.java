
// CS 0445 Spring 2019
// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!  Generally speaking you will implement your MyStringBuilder using
// a singly linked list of nodes.  See more comments below on the specific
// requirements for the class.

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder
{
	// These are the only three instance variables you are allowed to have.
	// See details of CNode class below.  In other words, you MAY NOT add
	// any additional instance variables to this class.  However, you may
	// use any method variables that you need within individual methods.
	// But remember that you may NOT use any variables of any other
	// linked list class or of the predefined StringBuilder or 
	// or StringBuffer class in any place in your code.  You may only use the
	// String class where it is an argument or return type in a method.
	private CNode firstC;	// reference to front of list.  This reference is necessary
							// to keep track of the list
	private CNode lastC; 	// reference to last node of list.  This reference is
							// necessary to improve the efficiency of the append()
							// method
	private int length;  	// number of characters in the list

	// You may also add any additional private methods that you need to
	// help with your implementation of the public methods.

	// Create a new MyStringBuilder initialized with the chars in String s
	public MyStringBuilder(String s)
	{
		firstC = new CNode(s.charAt(0));
		CNode temp = firstC;
		for(int i=1; i<s.length();i++) {
			temp.next = new CNode(s.charAt(i));
			temp = temp.next;
		}
		lastC = temp;
		length = s.length();
	}

	// Create a new MyStringBuilder initialized with the chars in array s
	public MyStringBuilder(char [] s)
	{
		firstC = new CNode(s[0]);
		CNode temp = firstC;
		for(int i =1; i<s.length;i++) {
			temp.next = new CNode(s[i]);
			temp = temp.next;
			
		}
		lastC =  temp;
		length =s.length;
	}

	// Create a new empty MyStringBuilder
	public MyStringBuilder()
	{
		firstC = null;
		lastC = firstC;
		length = 0;
	}

	// Append MyStringBuilder b to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(MyStringBuilder b)
	{
		String str = "";
		CNode temp = b.firstC;
		while(temp != null) {
			str += temp.data;
			temp = temp.next;
		}
		char[]c =str.toCharArray();
		for(char a: c) {
			lastC.next = new CNode(a);
			lastC = lastC.next;
		}
		length += b.length();
		return this;
	}


	// Append String s to the end of the current MyStringBuilder, and return
	// the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(String s)
	{
		char[] c = s.toCharArray();
		
		this.append(c);
		
		
		length += s.length();
		return this;
	}

	// Append char array c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char [] c)
	{
		for(char s:c) {
			this.append(s);
		}
		length += c.length;
		return this;
	}

	// Append char c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char c)
	{
		if(firstC==null && lastC==null) {
			firstC = new CNode(c);
			lastC = firstC;
			length++;
			return this;
			}
		
		lastC.next = new CNode(c);
		lastC = lastC.next;
		length ++;
		return this;
	}

	// Return the character at location "index" in the current MyStringBuilder.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index)
	{
		CNode temp = firstC;
		for(int i=0;i<index;i++) {
			if(temp.next==null)
				break;
			temp=temp.next;
		}
		return temp.data;
	}

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder, 
	// only remove up until the end of the MyStringBuilder. Be careful for 
	// special cases!
	 public MyStringBuilder delete(int start, int end)
	{
		 int index = start;
		 int count = start;
		 while(count<end) {
			 this.deleteCharAt(index);
			 count++;
		 }
		 return this;
	}

	// Delete the character at location "index" from the current
	// MyStringBuilder and return the current MyStringBuilder.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder as is).
	// Be careful for special cases!
	public MyStringBuilder deleteCharAt(int index)
	{
		if(index>length)
			return this;
		
		int count=0;
		CNode Pretemp = firstC;
		CNode temp = Pretemp.next;
		
		while(count<index-1) {
			Pretemp = temp;
			if(temp.next==null)
				break;
			temp = temp.next;
			count++;
		
		}

		    Pretemp.next = temp.next;
		
		if(index ==0) {
			firstC =temp;
		}
		length--;
		return this;
	}

	// Find and return the index within the current MyStringBuilder where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder.  If str does not match any sequence of characters
	// within the current MyStringBuilder, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		String s = this.toString();
		int m  = s.indexOf(str);
		return m;
	}

	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder insert(int offset, String str)
	{
		char[]c = str.toCharArray();
		for(int i =c.length -1; i>=0;i--){
			this.insert(offset, c[i]);
		}
		length+=str.length();
		return this;
		
	}

	// Insert character c into the current MyStringBuilder at index
	// "offset" and return the current MyStringBuilder.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
    public MyStringBuilder insert(int offset, char c)
	{
		if(offset ==length) 
			return this.append(c);
		if(offset>length || offset<0)
			return this;
		if(offset==0) {
			CNode node = new CNode(c);
			node.next =firstC;
			firstC = node;
			length++;
			return this;
		}
		CNode temp = firstC;
		int count = 0;
		while(count<offset-1) {
			if(temp.next==null)
				break;
			temp = temp.next;
			count++;
		}
		CNode node = new CNode(c);
		CNode temp2 = temp.next;
		temp.next =node;
		temp.next.next =temp2;
		length++;
		
		return this;
	}

	// Insert char array c into the current MyStringBuilder starting at index
	// index "offset" and return the current MyStringBuilder.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder insert(int offset, char [] c)
	{
		for(int i =c.length -1; i>=0;i--){
			this.insert(offset, c[i]);
		}
		return this;
	}

	// Return the length of the current MyStringBuilder
	public int length()
	{
		return length;
	}

	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder, then insert String "str" into the current
	// MyStringBuilder starting at index "start", then return the current
	// MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder, only delete until the
	// end of the MyStringBuilder, then insert.  This method should be done
	// as efficiently as possible.  In particular, you may NOT simply call
	// the delete() method followed by the insert() method, since that will
	// require an extra traversal of the linked list.
	public MyStringBuilder replace(int start, int end, String str)
	{
		if(start<0 || end<=start)
			return this;
		if(end>length)
			end = length;				
		this.delete(start, end);
		this.insert(start, str);
		return this;
	}

	// Reverse the characters in the current MyStringBuilder and then
	// return the current MyStringBuilder.
	public MyStringBuilder reverse()
	{
		MyStringBuilder msb = new MyStringBuilder();
		for(int i = length-2;i>=0;i--){
			msb.append(this.charAt(i));
		}
		this.delete(0, length-1);
		this.append(msb);
		return this;
		
	}
	
	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder
	public String substring(int start, int end)
	{
		String str = "";
		CNode temp = firstC;
		for(int i=0;i<start;i++) {
			temp = temp.next;
		}
		for(int i= start;i<end;i++) {
			str+=temp.data;
			temp = temp.next;
		}
		return str;
	}

	// Return the entire contents of the current MyStringBuilder as a String
	public String toString()
	{
		String str = "";
		CNode temp = firstC;
		while(temp != null) {
			str += temp.data;
			temp = temp.next;
		}
		return str;
	}

	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder class MAY access the
	// data and next fields directly.
	private class CNode
	{
		private char data;
		private CNode next;

		public CNode(char c)
		{
			data = c;
			next = null;
		}

		public CNode(char c, CNode n)
		{
			data = c;
			next = n;
		}
	}
}
