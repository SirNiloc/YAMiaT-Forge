package com.sirniloc.mdk.character;

import java.util.Random;

public class Race {
	String name,subName;
	double mind,body,spirit;
	 
	public static Race[] races = {new Race("Voidling",9,0,0),
			new Race("Orc",0,9,0),
			new Race("Starborn",0,0,9),
			new Race("Human","Nordling",3,4,2),
			new Race("Elf","Woodelf",5,1,3),
			new Race("Halfelf",1,5),
			new Race("Elf","Sunelf",3,1,5),
			new Race("Human","Bogling",4,3,2),
			new Race("Human",3,3,3)};

	
	public Race(String string, int m, int b, int s) {
		name = string;
		subName = string;
		setStats(m,b,s);
	}
	public Race(String string, String s2, int m, int b, int s) {
		name = string;
		subName = s2;
		setStats(m,b,s);
	}
	public Race(String string, int i, int j) {
		name = string;
		subName=string;
		setStats(r(i,j),r(i,j),r(i,j));
	}

	private double r(int i, int j) {
		Random r = new Random();
		return (i + (j - i) * r.nextDouble());
	}
	
	private void setStats(double m, double b, double s) {
		mind=m;
		body=b;
		spirit=s;
		
	}
	
	
		
	
}
