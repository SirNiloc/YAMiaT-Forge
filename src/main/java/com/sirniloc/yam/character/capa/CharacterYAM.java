package com.sirniloc.yam.character.capa;

import com.sirniloc.yam.character.NBTHelper;
import com.sirniloc.yam.character.Race;
import com.sirniloc.yam.character.capa.interfaces.IAbilityScores;
import com.sirniloc.yam.util.AbilityScoreHelper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.util.INBTSerializable;

public class CharacterYAM implements IAbilityScores, INBTSerializable<NBTTagCompound> {

	private int raceIndex=-1;
	private int mind,body,spirit;
	public EntityLivingBase theEntity;
	
	@Override
	public String toString() {		
		return this.getRace().getFullName(theEntity)+
				" Mind:"+this.getMind()+
				" Body:"+this.getBody()+
				" Spirit:"+this.getSpirit();
	}

	public CharacterYAM(EntityLivingBase e) {
		theEntity = e;
		if(raceIndex<0 && !(e instanceof EntityPlayer))raceIndex=Race.getRandomRaceIndex(Race.RACE_COUNT);
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
	@Override
	public NBTTagCompound serializeNBT() {
		return (NBTTagCompound) NBTHelper.writeNBT(null, this, null);
		/*
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("mind", this.mind);
		nbt.setInteger("body", this.body);
		nbt.setInteger("spirit", this.spirit);
		nbt.setInteger("race", this.race);
		return nbt;*/
	}
	@Override
	public void deserializeNBT(NBTTagCompound nbt) {

		NBTHelper.readNBT(null, this, null, nbt);
		/*
		this.mind = nbt.getInteger("mind");
		this.body = nbt.getInteger("body");
		this.spirit = nbt.getInteger("spirit");
		this.race = nbt.getInteger("race");
		*/
		
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
		this.mind = MathHelper.clamp(i, 0, AbilityScoreHelper.MAX_ABS_LEVEL);
		
	}


	@Override
	public void setBody(int i) {
		this.body = MathHelper.clamp(i, 0, AbilityScoreHelper.MAX_ABS_LEVEL);
		
	}


	@Override
	public void setSpirit(int i) {
		this.spirit = MathHelper.clamp(i, 0, AbilityScoreHelper.MAX_ABS_LEVEL);
		
	}
	
	@Override
	public Race getRace() {
		if(this.theEntity instanceof EntityPlayer && raceIndex<0) raceIndex = Race.getRandomRaceIndex(Race.RACE_COUNT);
		
		return Race.getRaceFromInt(this.raceIndex);
	}


	@Override
	public int getRaceInt() {
		return this.raceIndex;
	}


	@Override
	public void setRaceInt(int i) {
		this.raceIndex = i;		
	}

	@Override
	public CharacterYAM getABS() {
		return this;
	}

	@Override
	public void cloneABS(CharacterYAM abs) {
		spirit=abs.getTotalSpirit();
		mind=abs.getTotalMind();
		body=abs.getTotalBody();
		raceIndex=abs.getRaceInt();
	}

}