package com.sirniloc.mdk.capability;

import com.sirniloc.mdk.character.Race;
import com.sirniloc.mdk.util.ABSCalc;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.util.INBTSerializable;

public class ABS implements IAbilityScores, INBTSerializable<NBTTagCompound> {

	private int race=-1;
	private int mind,body,spirit;
	public EntityLivingBase theEntity;
	
	@Override
	public String toString() {		
		return this.getRace().getRaceFullName(theEntity)+
				" Mind:"+this.getMind()+
				" Body:"+this.getBody()+
				" Spirit:"+this.getSpirit();
	}

	public ABS(EntityLivingBase e) {
		theEntity = e;
		if(race<0 && !(e instanceof EntityPlayer))race=Race.getRandomRaceIndex();
	}
	
		@Override
	public int getTotalMind() {
		return this.getRace().getMind()+this.getMind();
	}
	@Override
	public int getTotalBody() {
		return this.getRace().getBody()+this.getBody();
		
	}
	@Override
	public int getTotalSpirit() {
		return this.getRace().getSpirit()+this.getSpirit();
		
	}
	//
	@Override
	public int getMind() {
		return this.mind;
	}
	@Override
	public int getBody() {
		return this.body;
		
	}
	@Override
	public int getSpirit() {
		return this.spirit;
		
	}
	//
	//TODO Fix
	@Override
	public NBTTagCompound serializeNBT() {	
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("mind", this.mind);
		nbt.setInteger("body", this.body);
		nbt.setInteger("spirit", this.spirit);
		nbt.setInteger("race", this.race);
		return nbt;
	}
	@Override
	public void deserializeNBT(NBTTagCompound nbt) {

		System.err.println("deserializeNBT");
		this.mind = nbt.getInteger("mind");
		this.body = nbt.getInteger("body");
		this.spirit = nbt.getInteger("spirit");
		this.race = nbt.getInteger("race");
		
	}

	
	@Override
	public void syncSpawn() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void syncChange() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void syncStatCheck() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setMind(int i) {
		this.mind = MathHelper.clamp(i, 0, ABSCalc.MAX_ABS_LEVEL);
		
	}


	@Override
	public void setBody(int i) {
		this.body = MathHelper.clamp(i, 0, ABSCalc.MAX_ABS_LEVEL);
		
	}


	@Override
	public void setSpirit(int i) {
		this.spirit = MathHelper.clamp(i, 0, ABSCalc.MAX_ABS_LEVEL);
		
	}


	


	


	@Override
	public Race getRace() {
		if(this.theEntity instanceof EntityPlayer && race<0) race = Race.getRandomRaceIndex();
		
		return Race.getRaceFromInt(this.race);
	}


	@Override
	public int getRaceInt() {
		return this.race;
	}


	@Override
	public void setRaceInt(int i) {
		race = i;		
	}

	@Override
	public ABS getABS() {
		return this;
	}

	@Override
	public void cloneABS(ABS abs) {
		spirit=abs.getTotalSpirit();
		mind=abs.getTotalMind();
		body=abs.getTotalBody();
		race=abs.getRaceInt();
	}
}