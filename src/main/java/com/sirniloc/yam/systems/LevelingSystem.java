package com.sirniloc.yam.systems;

import com.sirniloc.yam.character.capability.CharacterYAM;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class LevelingSystem {

	public static void levelUp(CharacterYAM c) {
		c.setLevelInt(c.getLevel()+1);		
		try {
		if(c.theEntity instanceof EntityPlayerMP) {
			TextComponentString message = new TextComponentString("Level Up!");
			message.setStyle(message.getStyle().setColor(TextFormatting.GOLD));
			c.theEntity.sendMessage(message);
		}
		}catch(NullPointerException e) {
			
		}
		checkStats(c);		
	}
	
	public static void checkStats(CharacterYAM c) {
		c.setBody(c.getLevel()*(c.getRace().getBody()/3));
		c.setMind(c.getLevel()*(c.getRace().getMind()/3));
		c.setSpirit(c.getLevel()*(c.getRace().getSpirit()/3));
		
		try {
			if(c.theEntity instanceof EntityPlayerMP) {
				TextComponentString message = new TextComponentString("Mind:"+c.getMind()+" Body:"+c.getBody()+" Spirit:"+c.getSpirit());
				message.setStyle(message.getStyle().setColor(TextFormatting.GOLD));
				c.theEntity.sendMessage(message);
			}
		}catch(java.lang.NullPointerException e) {}
		
	}
}
