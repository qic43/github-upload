// CS 0445 Spring 2019
// Read this class and its comments very carefully to make sure you implement
// the class properly.  The data and public methods in this class are identical
// to those MyStringBuilder, with the exception of the two additional methods
// shown at the end.  You cannot change the data or add any instance
// variables.  However, you may (and will need to) add some private methods.
// No iteration is allowed in this implementation. 

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder2
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

	// Create a new MyStringBuilder2 initialized with the chars in String s
	public MyStringBuilder2(String s)
	{
		if(s!=null && s.length()>0)
			makeBuilder(s,0);
		else
		{
			length = 0;
            firstC = null;
            lastC = null;
		}
	}
	private void makeBuilder(String s, int pos)
	{
      // Recursive case we have not finished going through the String
      if (pos < s.length()-1)
      {
            // Note how this is done we make the recursive call FIRST, then
            // add the node before it.  In this way the LAST node we add is
            // the front node, and it enables us to avoid having to make a
            // special test for the front node.  However, many of your
            // methods will proceed in the normal front to back way.
            makeBuilder(s, pos+1);
            firstC = new CNode(s.charAt(pos), firstC);
            length++;
      }
      else if (pos == s.length()-1) // Special case for last char in String
      {                             // This is needed since lastC must be
                                    // set to point to this node
            firstC = new CNode(s.charAt(pos));
            lastC = firstC;
            length = 1;
      }
      else  // This case should never be reached, due to the way the
            // constructor is set up.  However, I included it as a
      {     // safeguard (in case some other method calls this one)
            length = 0;
            firstC = null;
            lastC = null;
      }
	}

	// Create a new MyStringBuilder2 initialized with the chars in array s
	public MyStringBuilder2(char [] s)
	{
		if(s.length>0)
			makeBuilder(s,0);
		else
		{
			firstC=null;
			lastC=null;
			length=0;
		}
	}
	private void makeBuilder(char[] s, int pos)
	{
      // Recursive case we have not finished going through the char[]
      if (pos < s.length-1)
      {
            // Note how this is done we make the recursive call FIRST, then
            // add the node before it.  In this way the LAST node we add is
            // the front node, and it enables us to avoid having to make a
            // special test for the front node.  However, many of your
            // methods will proceed in the normal front to back way.
            makeBuilder(s, pos+1);
            firstC = new CNode(s[pos], firstC);
            length++;
      }
      else if (pos == s.length-1) // Special case for last char in char[]
      {                             // This is needed since lastC must be
                                    // set to point to this node
            firstC = new CNode(s[pos]);
            lastC = firstC;
            length = 1;
      }
      else  // This case should never be reached, due to the way the
            // constructor is set up.  However, I included it as a
      {     // safeguard (in case some other method calls this one)
            length = 0;
            firstC = null;
            lastC = null;
      }
	}

	// Create a new empty MyStringBuilder2
	public MyStringBuilder2()
	{
		firstC=null;
		lastC=null;
		length=0;
	}

	// Append MyStringBuilder2 b to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(MyStringBuilder2 b)
	{
		if(firstC!=null)
		add(b,0);
		else
		{
			CNode c = new CNode(b.charAt(0));
			CNode c2 = new CNode(b.charAt(1));
			firstC = c;
			length++;
			firstC.next = c2;
			length++;
			lastC=c2;
			add(b,2);
		}
		return this;
	}

	private void add(MyStringBuilder2 b, int pos)
	{
		
		if(pos<b.length()-1)
		{
			CNode c = new CNode(b.charAt(pos));
			lastC.next=c;
			lastC=lastC.next;
			length++;
			add(b, pos+1);
		}
		else if(pos==b.length()-1)
		{
			CNode c = new CNode(b.charAt(pos));
			lastC.next=c;
			lastC=lastC.next;
			length++;
		}
		else
		{
			lastC=null;
		}
	}


	// Append String s to the end of the current MyStringBuilder2, and return
	// the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(String s)
	{
		if(firstC!=null)
		add(s,0);
		else
		{
			CNode c = new CNode(s.charAt(0));
			CNode c2 = new CNode(s.charAt(1));
			firstC = c;
			length++;
			firstC.next = c2;
			length++;
			lastC=c2;
			add(s,2);
		}
		return this;
	}
		private void add(String s, int pos)
	{
		
		if(pos<s.length()-1)
		{
			CNode c = new CNode(s.charAt(pos));
			lastC.next=c;
			lastC=lastC.next;
			length++;
			add(s, pos+1);
		}
		else if(pos==s.length()-1)
		{
			CNode c = new CNode(s.charAt(pos));
			lastC.next=c;
			lastC=lastC.next;
			length++;
		}
		else
		{
			lastC=null;
		}
	}
	
	// Append char array c to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(char [] c)
	{
		if(firstC!=null)
		add(c,0);
		else
		{
			CNode c1 = new CNode(c[0]);
			CNode c2 = new CNode(c[1]);
			firstC = c1;
			length++;
			firstC.next = c2;
			length++;
			lastC=c2;
			add(c,2);
		}
		return this;
	}

	private void add(char[] r, int pos)
	{
		
		if(pos<r.length-1)
		{
			CNode c = new CNode(r[pos]);
			lastC.next=c;
			lastC=lastC.next;
			length++;
			add(r, pos+1);
		}
		else if(pos==r.length-1)
		{
			CNode c = new CNode(r[pos]);
			lastC.next=c;
			lastC=lastC.next;
			length++;
		}
		else
		{
			lastC=null;
		}
	}

	// Append char c to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(char c)
	{
		CNode node=new CNode(c);
		if(firstC!=null)
		{
			lastC.next=node;
			lastC=node;
			length++;
		}
		else
		{
			firstC=node;
			lastC=firstC;
			length++;
		}
		return this;
	}

	// Return the character at location "index" in the current MyStringBuilder2.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index)
	{
		if(index>=length)
			throw new IndexOutOfBoundsException("");
		else
		{
			if(index == 0)
				return firstC.data;
			else
			{
			CNode node=new CNode('a');
			node=firstC;
			node=goTo(node, 0, index);
			char c = node.data;
			return c;
			}
		}
	}

	private CNode goTo(CNode c, int flag, int index)
	{
		if(flag<index)
		{
			c=c.next;
			return goTo(c,flag+1,index);
		}
		else
		{
			return c;	
		}
	}

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder2, and return the current MyStringBuilder2.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder2 as is).  If "end" is past the end of the MyStringBuilder2, 
	// only remove up until the end of the MyStringBuilder2. Be careful for 
	// special cases!
	public MyStringBuilder2 delete(int start, int end)
	{
		if(start<0 || start>=length || end<=start)
		{
			return this;
		}
		if(start==0)
			{
				if(end<length)
				{
				firstC=goTo(firstC,0, end);
				length=(length-(end-start));
				}
				else
				{
					firstC= null;
					lastC= null;
					length=0;
				}
			}
		else
			{
			CNode startnode=new CNode('a');
			CNode lastnode=new CNode('a');
			startnode=firstC;
			startnode= goTo(startnode,0, start-1);
			if(end>length)
			{
				end=length;
			}
				lastnode=startnode;
				lastnode = goTo(lastnode,start-1, end);
				startnode.next=lastnode;
				length=length-(end-start);
			}
			return this;
		
	}

	// Delete the character at location "index" from the current
	// MyStringBuilder2 and return the current MyStringBuilder2.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder2 as is).
	// Be careful for special cases!
	public MyStringBuilder2 deleteCharAt(int index)
	{
		if(index<0||index>=length)
		{
		}
		else
		{
			if(index==0)
			{
				firstC=firstC.next;
				length--;
			}
			else 
			{
				CNode node = new CNode('a');
				node = firstC;
				CNode dnode= new CNode('a');
				dnode=firstC;
				node =goTo(node,0, index-1);
				dnode =goTo(dnode,0,index+1);
				node.next=dnode;
				if(index==length-1)
					lastC=node;
				length--;
			}
		}
		return this;
	}

	// Find and return the index within the current MyStringBuilder2 where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder2.  If str does not match any sequence of characters
	// within the current MyStringBuilder2, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		int index=-1;
		boolean match=false;
		CNode node = firstC;
		int i=0;
		index = find(node,str,i);	
		return index;
	}

	private int find(CNode c, String str, int pos)
	{
		if(c==null)
			return -1;
		else
		{
		if(c.data==str.charAt(0))
		{
			boolean match = true;
			CNode temp = c;
			match = check(temp, str, 0);
			if(match)
			return pos;
			else
			{
			c=c.next;
			return find(c,str,pos+1);
			}
		}
		else
		{
			c=c.next;
			return find(c,str,pos+1);
		}
		}
	}

	private boolean check(CNode c, String str, int l)
	{
		if(l<str.length())
		{
		if(c==null)
		{
			return false;
		}
		if(c.data!=str.charAt(l))
		{
			return false;
		}
			c=c.next;
			return check(c,str,l+1);
		}
		else
		return true;
	}

	// Insert String str into the current MyStringBuilder2 starting at index
	// "offset" and return the current MyStringBuilder2.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder2 insert(int offset, String str)
	{
		if(offset>length || offset<0)
			return this;
		else 
		{
			if(offset == length)
				this.append(str);
			else
			{
				CNode node = firstC;
				if(offset==0)
				{
					firstC=new CNode(str.charAt(0));
					CNode temp =firstC;
					temp = add2(temp, str, 1);
					temp.next=node;
				}
				else
				{
				node = goTo(node,0, offset-1);
				CNode temp = node.next;
				node = add2(node,str,0);
				node.next=temp;
				}
				length+=str.length();
			}
			return this;
		}
	}

	private CNode add2(CNode c, String s, int pos)
	{

		if(pos<s.length())
		{
			c.next=new CNode(s.charAt(pos));
			c=c.next;
			return add2(c,s,pos+1);
		}
		else
		{
			return c;
		}
	}

	// Insert character c into the current MyStringBuilder2 at index
	// "offset" and return the current MyStringBuilder2.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder2 insert(int offset, char c)
	{
		if(offset>length || offset<0)
			return this;
		else
		{
			if(offset==length)
				this.append(c);
			else
			{
				CNode node = firstC;
				if(offset==0)
				{
					firstC=new CNode(c);
					firstC.next=node;
				}
				else
				{
				node = goTo(node,0, offset-1);
				CNode temp = node.next;
				CNode add = new CNode(c);
				node.next=add;
				node=node.next;
				node.next=temp;
				}
				length++;
			}

			return this;
		}
	}

	// Insert char array c into the current MyStringBuilder2 starting at index
	// index "offset" and return the current MyStringBuilder2.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder2 insert(int offset, char [] c)
	{
		if(offset>length || offset<0)
			return this;
		else 
		{
			if(offset == length)
				this.append(c);
			else
			{
				CNode node = firstC;
				if(offset==0)
				{
					firstC=new CNode(c[0]);
					CNode temp =firstC;
					temp = add2(temp,c,1);
					temp.next=node;
				}
				else
				{
				node = goTo(node, 0,offset-1);
				CNode temp = node.next;
				node = add2(node,c,0);
				node.next=temp;
				}
				length+=c.length;
			}
			return this;
		}
	}
	private CNode add2(CNode c, char[] ch, int pos)
	{

		if(pos<ch.length)
		{
			c.next=new CNode(ch[pos]);
			c=c.next;
			return add2(c,ch,pos+1);
		}
		else
		{
			return c;
		}
	}

	// Return the length of the current MyStringBuilder2
	public int length()
	{
		return length;
	}

	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder2, then insert String "str" into the current
	// MyStringBuilder2 starting at index "start", then return the current
	// MyStringBuilder2.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder2, only delete until the
	// end of the MyStringBuilder2, then insert.  This method should be done
	// as efficiently as possible.  In particular, you may NOT simply call
	// the delete() method followed by the insert() method, since that will
	// require an extra traversal of the linked list.
	public MyStringBuilder2 replace(int start, int end, String str)
	{
		if(start<0 || start>=length || end <=start)
			return this;
		else
		{
			CNode node = firstC;
			if(end>length)
			{
				end=length;
			}
			if(start==0)
			{
				node = goTo(node,0, end);
				firstC=new CNode(str.charAt(0));
				CNode temp =firstC;
				temp = add2(temp, str, 1);
				temp.next=node;
			}
			else
			{
				node = goTo(node,0, start-1);
				CNode temp = node.next;
				temp = goTo(temp,start, end);
				node = add2(node, str, 0);
				node.next=temp;
			}
			length=length-(end-start)+str.length();
		}
		return this;
	}

	// Reverse the characters in the current MyStringBuilder2 and then
	// return the current MyStringBuilder2.
	public MyStringBuilder2 reverse()
	{
		MyStringBuilder2 sb=new MyStringBuilder2();
		re(sb,length-1);
		this.delete(0,length);
		this.append(sb);
		return this;
	}

	private void re(MyStringBuilder2 sb, int flag)
	{
		if(flag>=0)
		{
			sb.append(this.charAt(flag));
			re(sb, flag-1);
		}
	}
	
	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder2
	public String substring(int start, int end)
	{
		String temp ="";
		temp += addString(temp, start, end);
		return temp;
	}

	private String addString(String temp, int start, int end)
	{
		CNode c = firstC;
		c=goTo(c,0,start);
		if(c!=null)
		{
		if(start<end-1)
		{
			temp+=this.charAt(start);
			return addString(temp, start+1, end);
		}
		else
		{
			return temp+this.charAt(start);
		}
		}
		else
			return temp;
	}

	// Return the entire contents of the current MyStringBuilder2 as a String
	public String toString()
	{
		String temp ="";
		if(length==0)
		{}
		else
		temp += addString(temp,0,length);
		return temp;
	}
	// Find and return the index within the current MyStringBuilder2 where
	// String str LAST matches a sequence of characters within the current
	// MyStringBuilder2.  If str does not match any sequence of characters
	// within the current MyStringBuilder2, return -1.  Think carefully about
	// what you need to do for this method before implementing it.  For some
	// help with this see the Assignment 3 specifications.
	public int lastIndexOf(String str)
	{
		int index=-1;
		CNode node = firstC;
		int i=0;
		index = find2(node,str,i);	
		return index;
	}

	private int find2(CNode c, String str, int pos)
	{
		if(c==null)
			return -1;
		else
		{
		if(c.data==str.charAt(0))
		{
			boolean match = true;
			CNode temp = c;
			match = check(temp, str, 0);
			if(match)
			{
			return Math.max(pos,find2(c.next,str,pos+1));
			}
			else
			{
			c=c.next;
			return find2(c,str,pos+1);
			}
		}
		else
		{
			c=c.next;
			return find2(c,str,pos+1);
		}
		}
	}
	
	// Find and return an array of MyStringBuilder2, where each MyStringBuilder2
	// in the return array corresponds to a part of the match of the array of
	// patterns in the argument.  If the overall match does not succeed, return
	// null.  For much more detail on the requirements of this method, see the
	// Assignment 3 specifications.
	public MyStringBuilder2 [] regMatch(String [] pats)
	{
		MyStringBuilder2 [] mb=new MyStringBuilder2[pats.length];
		mb=init(mb,0,pats.length);
		boolean matched=match(mb,pats,0,0);
		if(matched)
			return mb;
		else
			return null;
	}

	private MyStringBuilder2[] init(MyStringBuilder2[] mb,int a, int b)
	{
		if(a<b)
		{
			mb[a]=new MyStringBuilder2();
			return init(mb,a+1,b);
		}
		else
			return mb;
	}

	private boolean match(MyStringBuilder2[] mb, String[] pats, int pos, int flag)
	{
		if(pos==pats.length)
			return true;
		else if(flag==length)
			return false;
		else if(pos==0)
		{
			boolean a1=check(this.charAt(flag), pats[pos],0);
			boolean a2=true;
			if(a1)
			{
				mb[pos].append(this.charAt(flag));
				a2=match(mb,pats,pos,flag+1);
				if(!a2)
				{
					if(pats.length==1)
						return match(mb,pats,pos+1,flag);
					if(mb[pos].length()==1)
					{
						mb[pos].deleteCharAt(0);
						return match(mb, pats, pos, flag+1);
					}
					else
					{
						boolean a3 = match(mb, pats, pos+1, flag);
						mb[pos].deleteCharAt(mb[pos].length()-1);
						return a3;
					}
				}
				else
					return a2;
			}
			else
			{
				if(mb[pos].length()==0)
				{
					if(flag<length)
						return match(mb, pats, pos, flag+1);
					else
						return false; 
				}
				else
					return match(mb, pats, pos+1,flag+1);

			}
		}
		else
		{
			boolean a1 = check(this.charAt(flag),pats[pos],0);
			boolean a2 = true;
			if(a1)
			{
				mb[pos].append(this.charAt(flag));
				a2 = match(mb, pats, pos, flag+1);
				if(!a2)
				{
					mb[pos].deleteCharAt(mb[pos].length()-1);
					return match(mb, pats, pos+1,flag);
				}
			}
			else
			{
				if(mb[pos].length()==0)
					return false;
				else
					return match(mb, pats, pos+1, flag);
			}
			return a2;
		}
	}
	
	private boolean check(char c, String str, int l)
	{
		if(l<str.length())
		{
		if(c==str.charAt(l))
		{
			return true;
		}
		else
			return check(c,str,l+1);
		}
		else
		return false;
	}
	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder2 class MAY access the
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
