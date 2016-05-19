import java.util.Arrays;
import java.util.Scanner;

public class Main {
	//
	static int p1 = 0,p2 = 0, p3 = 0;
	static String plugVals = "";	

	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.println("What are the rotors set at? (value 0 to 26) the fastest gear is on the right \n e.g 1 2 3");
		p3 = scan.nextInt();
		p2 = scan.nextInt();
		p1 = scan.nextInt();
		System.out.println("How are the plugs arranged, if at all (submit a non alphabetic character if not) \nIt must be in this format: AB-CD-EF-GH-IJ");
		plugVals = scan.next().replace("-", " ");
		System.out.println(plugVals);

		System.out.println("Remeber these values to decrypt your message");
		
	System.out.println("Full Message to encrypt/decrypt: ");
	String encrypted = "";
			Scanner scan2 =new Scanner(System.in);
				String initial = scan2.nextLine();//do the things
				
				initial = initial.toUpperCase();
				for(int i = 0; i < initial.length(); i++)
				{
					if(initial.charAt(i) == 32)
					{
						encrypted+=" ";
					}
					else encrypted+=Cipher(initial.charAt(i));
					//System.out.println(i);
				}
				System.out.println("The message is " + encrypted + " ");
			scan2.close();
		
	}
	
	public static char Cipher(char s)
	{
	s = checkPlug(s);
    Character[][] Rotor1 = {{'A','V'},{'B','N'},{'C','X'},{'D','Y'},{'E','A'},{'F','W'},{'G','I'},{'H','D'},{'I','G'},{'J','S'},{'K','H'},{'L','M'},{'M','Z'},{'N','R'},{'O','P'},{'P','F'},{'Q','B'},{'R','J'},{'S','L'},{'T','C'},{'U','E'},{'V','Q'},{'W','K'},{'X','O'},{'Y','T'},{'Z','U'},};
	Character[][] Rotor2 = {{'A','H'},{'B','F'},{'C','K'},{'D','G'},{'E','M'},{'F','P'},{'G','X'},{'H','R'},{'I','N'},{'J','I'},{'K','Y'},{'L','B'},{'M','O'},{'N','S'},{'O','J'},{'P','U'},{'Q','A'},{'R','D'},{'S','T'},{'T','C'},{'U','L'},{'V','Z'},{'W','Q'},{'X','V'},{'Y','E'},{'Z','W'},};
	Character[][] Rotor3 = {{'A','Z'},{'B','S'},{'C','F'},{'D','X'},{'E','W'},{'F','E'},{'G','I'},{'H','N'},{'I','J'},{'J','C'},{'K','G'},{'L','T'},{'M','L'},{'N','P'},{'O','R'},{'P','Q'},{'Q','B'},{'R','Y'},{'S','A'},{'T','M'},{'U','K'},{'V','U'},{'W','D'},{'X','O'},{'Y','H'},{'Z','V'},};
	Character[][] Reflector = {{'A','Q'},{'B','R'},{'C','V'},{'D','W'},{'E','O'},{'F','T'},{'G','U'},{'H','N'},{'I','S'},{'J','X'},{'K','P'},{'L','Y'},{'M','Z'},{'N','H'},{'O','E'},{'P','K'},{'Q','A'},{'R','B'},{'S','I'},{'T','F'},{'U','G'},{'V','C'},{'W','D'},{'X','J'},{'Y','L'},{'Z','M'},};
	
	if(p2 == 26)
	{
		p3++;
		p2 = 0;
	}
	if(p1 > 26)
	{
		p1 = 0;
		p2++;
	}

	for(int i = 0; i < p1; i++)
	{
		Rotor1 = Rotate(Rotor1);
		//System.out.println(Arrays.deepToString(Rotor1));
	}
	for(int i = 0; i < p2; i++)
	{
		Rotor1 = Rotate(Rotor2);
	}
	for(int i = 0; i < p3; i++)
	{
		Rotor1 = Rotate(Rotor3);
	}
	s = goThrough(s,Rotor1);
	s = goThrough(s,Rotor2);
	s = goThrough(s,Rotor3);
	s = goThrough(s,Reflector);
	s = goThroughInv(s,Rotor3);
	s = goThroughInv(s,Rotor2);
	s = goThroughInv(s,Rotor1);
	s = checkPlug(s);
	
	
	p1++;
	//System.out.println(Arrays.deepToString(Rotor1));
		return s;
	}
	
	static char goThrough(char c, Character[][] array)
	{
		for(int i = 0; i < 26; i++)
		{
			if(array[i][0].equals(c))
			{
				c=array[i][1];
				return c;
			}
		}
		return c;
	}
	
	static char goThroughInv(char c, Character[][] array)
	{
		for(int i = 0; i < 26; i++)
		{
			if(array[i][1].equals(c))
			{
				c=array[i][0];
				return c;
			}
		}
		return c;
	}
	
	static char checkPlug(char a)
	{
		if(plugVals.indexOf(a) >= 0)
		{
			int temp = plugVals.indexOf(a);
			if(temp + 1 < plugVals.length() &&plugVals.charAt(temp + 1) != 32)
			{
				return plugVals.charAt(temp + 1);
			}
			else if(temp - 1 < plugVals.length() &&plugVals.charAt(temp - 1) != 32)
			{
				return plugVals.charAt(temp - 1);
			}
		}
		return a;
	}
	
	static Character[][] Rotate(Character[][] Rotor)
	{
		for(int i = 0; i < 26; i++)
		{
			if(Rotor[i][0] == 90 )
			{
				Rotor[i][1]++;
				Rotor[i][0] = 65;
			}
			else if(Rotor[i][1] == 90)
			{
				Rotor[i][0]++;
				Rotor[i][1] =65;
			}
			else
			{
			Rotor[i][0]++;
			Rotor[i][1]++;
			}
		}
		return Rotor;
	}
}