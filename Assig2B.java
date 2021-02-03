
import java.util.*;

public class Assig2B {

	public static void main(String[] args) {
		Scanner inScan = new Scanner(System.in);
		System.out.println("Enter the Value of N");
		int n = inScan.nextInt();
		String s  = "";
		StringBuilder sb = new StringBuilder();
		MyStringBuilder msb = new MyStringBuilder();
		
		//StringBuilder
		long starttime = System.nanoTime();
		for(int i =0;i<n;i++)
			sb.append('A');
		long stoptime = System.nanoTime();
		long time = stoptime - starttime;
		long average = time/n;
		System.out.println("Testing Append:"+"\n"+"        Predefined StringBuilder:");
		System.out.println("                Total time: "+time+" ns for "+n+" appends");
		System.out.println("                Time per append: "+average+ " ns");
		starttime = System.nanoTime();
		for(int i =0;i<sb.length();i++)
			sb.delete(0, 1);
		stoptime = System.nanoTime();
		time = stoptime - starttime;
		average = time/n;
		System.out.println("Testing remove(0):"+"\n"+"        Predefined StringBuilder:");
		System.out.println("                Total time: "+time+" ns for "+n+" remove(0)");
		System.out.println("                Time per remove(0): "+average+ " ns");
		starttime = System.nanoTime();
		if(sb.length()==0 ||sb.length()==1)
			sb.append('A');
		for(int i =0;i<n;i++)
			sb.insert(sb.length()/2, 'A');
		stoptime = System.nanoTime();
		time = stoptime - starttime;
		average = time/n;
		System.out.println("Testing insert:"+"\n"+"        Predefined StringBuilder:");
		System.out.println("                Total time: "+time+" ns for "+n+" inserts");
		System.out.println("                Time per inserts: "+average+ " ns");		

				
		//MyStringBuilder
		starttime = System.nanoTime();
		for(int i =0; i<n;i++)
			msb.append('A');
		stoptime = System.nanoTime();
		time = stoptime - starttime;
		average = time/n; 
		System.out.println("Testing Append:"+"\n"+"        MyStringBuilder:");
		System.out.println("                Total time: "+time+" ns for "+n+" appends");
		System.out.println("                Time per append: "+average+ " ns");
		starttime = System.nanoTime();
		for(int i=0;i<msb.length();i++)
			msb.deleteCharAt(0);
		stoptime = System.nanoTime();
		time = stoptime - starttime;
		average = time/n;
		System.out.println("Testing remove(0):"+"\n"+"        MyStringBuilder:");
		System.out.println("                Total time: "+time+" ns for "+n+" remove(0)");
		System.out.println("                Time per remove(0): "+average+ " ns");
		starttime = System.nanoTime();
		if(msb.length()==0||msb.length()==1)
			msb.append('A');
		for(int i =0; i<n;i++)
			msb.insert(msb.length()/2, 'A');
		stoptime = System.nanoTime();
		time = stoptime - starttime;
		average = time/n;
		System.out.println("Testing insert:"+"\n"+"        MyStringBuilder:");
		System.out.println("                Total time: "+time+" ns for "+n+" inserts");
		System.out.println("                Time per inserts: "+average+ " ns");
				
		//String
		starttime = System.nanoTime();
		for(int i = 0;i<n;i++)
			s+='A';
		stoptime = System.nanoTime();
		time = stoptime - starttime;
		average = time/n;
		System.out.println("Testing Append:"+"\n"+"        String:");
		System.out.println("                Total time: "+time+" ns for "+n+" appends");
		System.out.println("                Time per append: "+average+ " ns");
		starttime = System.nanoTime();
		for(int i = 0;i<n;i++) 
			s.substring(0);
		stoptime = System.nanoTime();
		time = stoptime - starttime;
		average = time/n;
		System.out.println("Testing Remove(0):"+"\n"+"        String:");
		System.out.println("                Total time: "+time+" ns for "+n+" remove(0)");
		System.out.println("                Time per remove(0): "+average+ " ns");
		starttime = System.nanoTime();
		if(s.length()==0 || s.length()==1)
			s+='A';
		for(int i =0; i<n;i++) {
			int index = s.length()/2;
			String str="";
			for(int m = 0;m<index;m++) {
				str+=s.charAt(m);
			}
			str+='A';
			for(int m = index+1;m<s.length();m++) {
				str+=s.charAt(m);			
			}
			s = str;
		}
		stoptime = System.nanoTime();
		time = stoptime - starttime;
		average = time/n;
		System.out.println("Testing insert:"+"\n"+"        String:");
		System.out.println("                Total time: "+time+" ns for "+n+" inserts");
		System.out.println("                Time per inserts: "+average+ " ns"); 	
		
		
		
		//MyStringBuilder
		starttime = System.nanoTime();
		for(int i =0; i<n;i++)
			msb.append('A');
		stoptime = System.nanoTime();
		time = stoptime - starttime;
		average = time/n; 
		System.out.println("Testing Append:"+"\n"+"        MyStringBuilder:");
		System.out.println("                Total time: "+time+" ns for "+n+" appends");
		System.out.println("                Time per append: "+average+ " ns");
		starttime = System.nanoTime();
		for(int i=0;i<msb.length();i++)
			msb.delete(0, 1);
		stoptime = System.nanoTime();
		time = stoptime - starttime;
		average = time/n;
		System.out.println("Testing remove(0):"+"\n"+"        MyStringBuilder:");
		System.out.println("                Total time: "+time+" ns for "+n+" remove(0)");
		System.out.println("                Time per remove(0): "+average+ " ns");
		starttime = System.nanoTime();
		if(msb.length()==0||msb.length()==1)
			msb.append('A');
		for(int i =0; i<n;i++)
			msb.insert(msb.length()/2, 'A');
		stoptime = System.nanoTime();
		time = stoptime - starttime;
		average = time/n;
		System.out.println("Testing insert:"+"\n"+"        MyStringBuilder:");
		System.out.println("                Total time: "+time+" ns for "+n+" inserts");
		System.out.println("                Time per inserts: "+average+ " ns");
		}
		
	}


