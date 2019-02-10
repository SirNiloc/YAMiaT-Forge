package com.sirniloc.yam.character.race;

import java.util.Random;

import com.sirniloc.yam.character.race.interfaces.IRace;
import com.sirniloc.yam.util.AbilityScoreHelper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;

public class Race implements IRace{
	protected String name,subName;
	double mind,body,spirit;
	
	static Race noRace = new Race();
	
	public static final Race[] RACES = {
			new Race("Human",3,3,3),
			new Race("Voidling",9,0,0),
			new Race("Orc",0,9,0),
			new Race("Starborn",0,0,9),
			new Race("Human","Nordling",3,4,2),
			new Race("Elf","Woodelf",5,1,3),
			new Race("Halfelf",1,5),
			new Race("Elf","Sunelf",3,1,5),
			new Race("Human","Bogling",4,3,2)
			};
	
	public static final int RACE_COUNT = RACES.length;
	
	public Race(String string, int m, int b, int s) {
		name = string;
		subName = "Pure Blooded "+string;
		setStats(m,b,s);
	}
	public Race(String string, String s2, int m, int b, int s) {
		name = string;
		subName = s2;
		setStats(m,b,s);
	}
	public Race(String string, int i, int j) {
		name = string;
		subName="Halfbreed";
		setStats(r(i,j),r(i,j),r(i,j));
	}
	
	public Race() {
		name = "";
		subName = "";
		setStats(0,0,0);
	}
	
	private static int r(int i, int j) {
		Random r = new Random();
		return (int) (i + (j - i) * r.nextDouble());
	}
	
	public static int getRandomIndex(int count) {
		return (int) r(0,count-1);
	}
	
	@Override
	public void setStats(int m, int b, int s) {
		mind=m;
		body=b;
		spirit=s;		
	}
	public static Race getRaceFromInt(int i) {
		if(i>=0)return RACES[MathHelper.clamp(i, 0, RACES.length-1)];
		else return noRace;
	}
	@Override
	public int getMind() {
		return (int) MathHelper.clamp(this.mind, 0, AbilityScoreHelper.MAX_ABS_RACE);
	}
	@Override
	public int getBody() {
		return (int) (MathHelper.clamp(this.body, 0, AbilityScoreHelper.MAX_ABS_RACE));
		
	}
	@Override
	public int getSpirit() {
		return (int) MathHelper.clamp(this.spirit, 0, AbilityScoreHelper.MAX_ABS_RACE);
		
	}
	@Override
	public String getName(EntityLivingBase e) {
		if(e instanceof EntityPlayer)	return this.name;
		return e.getName();
	}
	
	public String getSubRaceName(EntityLivingBase e) {
		if(e instanceof EntityPlayer)	return this.subName;
		return e.getName();
	}
	@Override
	public String getFullName(EntityLivingBase e) {
		if(e instanceof EntityPlayer)	return this.getName(e) +" ("+this.getSubRaceName(e)+")";
		return e.getName();
	}
}
