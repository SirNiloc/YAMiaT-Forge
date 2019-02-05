package com.sirniloc.mdk.character;

import java.util.concurrent.Callable;

import com.sirniloc.mdk.MDK;
import com.sirniloc.mdk.ModCapabilities;
import com.sirniloc.mdk.capability.IScores;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CharacterMDK implements IScores, Callable<IScores>, INBTSerializable<NBTTagCompound>{


	String name;
	Race race;
	int mind,body,spirit;
	
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
		this.mind = nbt.getInteger("mind");
		this.body = nbt.getInteger("body");
		this.spirit = nbt.getInteger("spirit");
		
	}
	@Override
	public IScores call() throws Exception {
	    return new CharacterMDK();
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
	

	

	
	
}


