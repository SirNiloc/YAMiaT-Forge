package com.sirniloc.mdk.capability;

import java.lang.ref.WeakReference;

import com.sirniloc.mdk.MDK;
import com.sirniloc.mdk.character.Race;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.util.INBTSerializable;

public class ABS implements IAbilityScores, INBTSerializable<NBTTagCompound> {

	String name;
	Race race;
	int mind,body,spirit;
	public WeakReference<EntityLivingBase> theEntity;


	public ABS(EntityLivingBase e) {
		theEntity = new WeakReference<EntityLivingBase>(e);	
	}
	 @Override
	public int getMind() {
		return MathHelper.clamp(this.mind, 0, MDK.MAXABILITYSCORE);
	}
	@Override
	public int getBody() {
		return MathHelper.clamp(this.body, 0, MDK.MAXABILITYSCORE);
		
	}
	@Override
	public int getSpirit() {
		return MathHelper.clamp(this.spirit, 0, MDK.MAXABILITYSCORE);
		
	}
	//TODO Fix
	@Override
	public NBTTagCompound serializeNBT() {	
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("mind", this.mind);
		nbt.setInteger("body", this.body);
		nbt.setInteger("spirit", this.spirit);
		return nbt;
	}
	@Override
	public void deserializeNBT(NBTTagCompound nbt) {

		System.err.println("deserializeNBT");
		this.mind = nbt.getInteger("mind");
		this.body = nbt.getInteger("body");
		this.spirit = nbt.getInteger("spirit");
		
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
		this.mind = MathHelper.clamp(i, 0, MDK.MAXABILITYSCORE);
		
	}


	@Override
	public void setBody(int i) {
		this.body = MathHelper.clamp(i, 0, MDK.MAXABILITYSCORE);
		
	}


	@Override
	public void setSpirit(int i) {
		this.spirit = MathHelper.clamp(i, 0, MDK.MAXABILITYSCORE);
		
	}
}