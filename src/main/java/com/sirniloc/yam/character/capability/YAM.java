package com.sirniloc.yam.character.capability;

import java.util.Arrays;

import com.sirniloc.yam.character.CharacterNBT;
import com.sirniloc.yam.character.capability.interfaces.IYam;
import com.sirniloc.yam.character.race.Race;
import com.sirniloc.yam.classes.ClassYAM;
import com.sirniloc.yam.systems.LevelingSystem;
import com.sirniloc.yam.util.AbilityScoreHelper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.INBTSerializable;

public class YAM implements IYam, INBTSerializable<NBTTagCompound> {

	private int raceIndex=-1,classIndex=-1;
	private int level,maxMana;
	
	private double exp,mind,body,spirit,mana;
	
	public EntityLivingBase theEntity;
	private EntityLivingBase[] recentAttackers;
	
	private float recentAttackersTime[];	
	private static final float DECAY = 200;	
	
	
	
	@Override
	public String toString() {		
		return this.getRace().getFullName(theEntity)+
				" Mind:"+this.getMind()+
				" Body:"+this.getBody()+
				" Spirit:"+this.getSpirit();
	}

	public YAM(EntityLivingBase e) {
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
		return (int) this.mind;
	}
	@Override
	public int getBody() {
		return (int) this.body;
		
	}
	@Override
	public int getSpirit() {
		return (int) this.spirit;
		
	}
	@Override
	public NBTTagCompound serializeNBT() {
		return (NBTTagCompound) CharacterNBT.writeNBT(null, this, null);
	}
	@Override
	public void deserializeNBT(NBTTagCompound nbt) {

		CharacterNBT.readNBT(null, this, null, nbt);
		
	}

	// TODO Multiplayer Sync
	@Override
	public void syncSpawn() {}
	@Override
	public void syncChange() {}
	@Override
	public void syncStatCheck() {}


	@Override
	public void setMind(double i) {
		this.mind = MathHelper.clamp(i, 0, AbilityScoreHelper.MAX_ABS_LEVEL);
		
	}


	@Override
	public void setBody(double i) {
		this.body = MathHelper.clamp(i, 0, AbilityScoreHelper.MAX_ABS_LEVEL);
		
	}


	@Override
	public void setSpirit(double i) {
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
	public YAM getYAM() {
		return this;
	}

	@Override
	public void cloneABS(YAM abs) {
		spirit=abs.getTotalSpirit();
		mind=abs.getTotalMind();
		body=abs.getTotalBody();
		raceIndex=abs.getRaceInt();
	}

	@Override
	public void setExpDouble(double d) {
		double curExp = this.exp;
		
		if(this.getNextLevelExpCost()<=(d+curExp)) {
			double leftOverExp = (d+curExp)-this.getNextLevelExpCost();
			
			LevelingSystem.levelUp(this);			
			this.addExp(leftOverExp);

		}else {
			this.exp = d;

			if(this.theEntity instanceof EntityPlayerMP) {
				try {
					TextComponentString message = new TextComponentString("Exp:"+this.getExp() +"/"+this.getNextLevelExpCost());
					message.setStyle(message.getStyle().setColor(TextFormatting.GREEN));
					this.theEntity.sendMessage(message);
				}catch(NullPointerException e) {}
			}
		}
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

	
	
	@Override
	public void addAttacker(EntityLivingBase n) {
		try {
			if(Arrays.stream(recentAttackers).anyMatch(n::equals)) {
				for(int i=0; i< recentAttackers.length;i++) {
					if(this.recentAttackers[i].equals(n)) {
						this.recentAttackersTime[i]=DECAY;
						break;
					}
				}
			}else {
				EntityLivingBase[] oldAttackers = this.recentAttackers.clone();
				float[] oldTimes = this.recentAttackersTime.clone();
				
				recentAttackers = new EntityLivingBase[oldAttackers.length+1];
				recentAttackersTime = new float[oldTimes.length+1];
				
				for(int i=0; i< oldAttackers.length;i++) {
					recentAttackers[i]=oldAttackers[i];
					this.recentAttackersTime[i]=oldTimes[i];
				}
				this.recentAttackers[oldAttackers.length]=n;
				this.recentAttackersTime[oldTimes.length]=DECAY;
			}
		}catch(NullPointerException e) {
			recentAttackers = new EntityLivingBase[]{n};
			recentAttackersTime = new float[] {DECAY};
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
			
			for(int i=ogLength-1; i>=0;i--) {
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


	@Override
	public void addExp(double d) {
		this.setExpDouble(this.getExp()+d);		
	}

	public void useMana(double cost) {
		this.mana-=cost;
		
	}

	@Override
	public void setMana(double f) {
		this.mana=f;
		
	}

	@Override
	public void setManaMax(int integer) {
		this.maxMana=integer;
		
	}

	@Override
	public double getMana() {
		return this.mana;
	}

	@Override
	public int getManaMax() {
		return this.maxMana;
	}

	@Override
	public void setClassInt(int integer) {
		this.classIndex=integer;
	}

	@Override
	public int getClassIndex() {
		return this.classIndex;
	}
	
	public ClassYAM getClassYAM() {
		return ClassYAM.getClassFromIndex(this.getClassIndex());
	}
	
	

}