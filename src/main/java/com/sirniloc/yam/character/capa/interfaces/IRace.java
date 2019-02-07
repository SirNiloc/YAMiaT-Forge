package com.sirniloc.yam.character.capa.interfaces;

import net.minecraft.entity.EntityLivingBase;

public interface IRace {

	String getFullName(EntityLivingBase e);

	String getName(EntityLivingBase e);

	int getSpirit();

	int getBody();

	int getMind();


	void setStats(int m, int b, int s);


}
