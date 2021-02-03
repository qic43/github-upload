
import java.util.*;

public class GasolineAnalysis {
	private static boolean repeat = true;
	public static void main (String [] args){
	while(repeat) {
	Scanner inScan = new Scanner(System.in);
	System.out.println("Welcome! What's your name");
	String name = new String(inScan.nextLine());
	System.out.println("Hello " + name);
	System.out.println("Please enter a floating point value specifying gallons of gasoline");
	double gasoline = inScan.nextDouble();
	System.out.println("What sort of conversions you wish to perform?(enter a number)");
	System.out.println("1. Number of liters");
	System.out.println("2. Number of barrels of oil required to produce the gallons of gasoline specified");
	System.out.println("3. Number of CO2 produced");
	System.out.println("4. Equivalent energy amount of ethanol gallons");
	System.out.println("5. Prie of the gasoline in US dollars");
	int num = inScan.nextInt();
	double liters = gasoline*3.7854;
	double barrel = gasoline/19.5;
	double co2 = 20* gasoline;
	double BTU = gasoline*115000;
	double price = gasoline*4;
	if(num == 1)
		System.out.println(liters+ " liters");
	else if(num == 2)
		System.out.println(barrel+ " barrel");
	else if(num ==3)
		System.out.println(co2+" CO2");
	else if(num ==4)
		System.out.println(BTU + " BTU");
	else
		System.out.println(price + " dollars");
	
	Scanner inScan2 = new Scanner(System.in);
	System.out.println("repeat?(yes/no)");
	String s = new String(inScan2.nextLine());
	if(s.equals("yes")) {
		repeat = true;
	}else {
		repeat = false;
		break;
	}
		
	}
	
		
	}

}
