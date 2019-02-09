package com.sirniloc.yam.character.capa;

import java.util.Arrays;

import com.sirniloc.yam.BaseYAM;
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
	private int mind,body,spirit,level;
	private double exp;
	public EntityLivingBase theEntity;
	
	private static final float DECAY = 200;
	
	private EntityLivingBase[] recentAttackers;
	private float recentAttackersTime[];
	
	@Override
	public String toString() {		
		return this.getRace().getFullName(theEntity)+
				" Mind:"+this.getMind()+
				" Body:"+this.getBody()+
				" Spirit:"+this.getSpirit();
	}

	public CharacterYAM(EntityLivingBase e) {
		theEntity = e;
		if(raceIndex<0 && !(e instanceof EntityPlayer))raceIndex=Race.getRandomIndex(Race.RACE_COUNT);
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
	}
	@Override
	public void deserializeNBT(NBTTagCompound nbt) {

		NBTHelper.readNBT(null, this, null, nbt);
		
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
		if(this.theEntity instanceof EntityPlayer && raceIndex<0) raceIndex = Race.getRandomIndex(Race.RACE_COUNT);
		
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

	@Override
	public void setExpDouble(double d) {
		this.exp = d;
	}

	@Override
	public void setLevelInt(int i) {
		this.level = MathHelper.clamp(i, 1, 50);
	}

	@Override
	public double getExp() {
		return this.exp;
	}

	@Override
	public int getLevel() {
		this.level = MathHelper.clamp(this.level, 1, 50);
		return this.level;
	}

	@Override
	public double getNextLevelExpCost() {
		return (this.getLevel() + ((1+this.getLevel())/2))*100;
	}

	public static String getNameYAM(EntityLivingBase e) {
		IAbilityScores cap = e.getCapability(BaseYAM.ABS_CAP, null);
		String r = "["+cap.getLevel()+"] "+e.getName()+" ("+cap.getRace().getName(e)+")";
		return r;
	}
	
	@Override
	public void addAttacker(EntityLivingBase n) {
		if(Arrays.stream(recentAttackers).anyMatch(n::equals)) {
			for(int i=0; i< recentAttackers.length;i++) {
				if(this.recentAttackers[i].equals(n)) {
					this.recentAttackersTime[i]=DECAY;
					break;
				}
			}
		}else {
			EntityLivingBase[] clone = recentAttackers.clone();
			float[] cloneTime = this.recentAttackersTime.clone();
			
			recentAttackers = new EntityLivingBase[clone.length];
			recentAttackersTime = new float[cloneTime.length];
			
			for(int i=0; i< clone.length;i++) {
				recentAttackers[i]=clone[i];
				this.recentAttackersTime[i]=cloneTime[i];
			}
			this.recentAttackers[clone.length]=n;
			this.recentAttackersTime[cloneTime.length]=DECAY;
		}
		
		
	}	
	
	@Override
	public void removeAttacker(int j) {
		EntityLivingBase[] clone = recentAttackers.clone();
		float[] cloneTime = this.recentAttackersTime.clone();
		int ii = 0;
		
		for(int i=0; i< clone.length;i++) {
			if(i!=j) {
				ii++;
				recentAttackers[ii]=clone[i];
				this.recentAttackersTime[ii]=cloneTime[i];
			}
		}
	}
	
	@Override
	public void update() {
		try{
			int ogLength = this.recentAttackersTime.length;
			
			for(int i=ogLength; i>0;i--) {
				recentAttackersTime[i]--;
				if(recentAttackersTime[i]<=0) removeAttacker(i);
			}
		}catch(NullPointerException e) {}
	}

	@Override
	public EntityLivingBase[] getRecentAttackers() {
		return recentAttackers;
		
	}

	@Override
	public float[] getRecentAttTime() {
		return this.recentAttackersTime;
	}

}