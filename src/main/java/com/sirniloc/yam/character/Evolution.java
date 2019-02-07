package com.sirniloc.yam.character;

import com.sirniloc.yam.character.capa.interfaces.IRace;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class Evolution extends Race{

	
	static Evolution noEvolution = new Evolution();
	
	public static final Evolution[] EVOLUTIONS = {
			new Evolution("Lycan",3,3,3),
			new Evolution("Hallow",9,0,0),
			new Evolution("Vampire",0,9,0),
			new Evolution("Fallen",0,0,9),
			new Evolution("Wraith",3,4,2),
			new Evolution("Spirit",5,1,3),
			new Evolution("Demon",1,5,0),
			new Evolution("Wisp",3,1,5)
			};

	
	public static final int EVOLUTION_COUNT = EVOLUTIONS.length;
	
	public Evolution(String string, int m, int b, int s) {
		name = string;
		setStats(m,b,s);
	}

	public Evolution() {}

	
	public static Evolution getEvoFromInt(int i) {
		if(i>=0)return EVOLUTIONS[MathHelper.clamp(i, 0, EVOLUTIONS.length-1)];
		else return noEvolution;
	}

	@Override
	public String getFullName(EntityLivingBase e) {
		return getName(e);
	}

}
