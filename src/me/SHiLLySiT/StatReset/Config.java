package me.SHiLLySiT.StatReset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gmail.nossr50.datatypes.SkillType;

public class Config {
	
	private StatReset plugin;
	public ArrayList<SkillType> affectedSkills = new ArrayList<SkillType>();
	public String deathMessage;
	
	public Config(StatReset plugin) {
	    this.plugin = plugin;
	}
	
	public void loadConfiguration() 
	{
		plugin.getConfig().options().copyDefaults(true);
		
		plugin.getConfig().addDefault("type", "level");
		
		plugin.getConfig().addDefault("penalty", 0);
		
		String[] skills = {"acrobatics"};
		plugin.getConfig().addDefault("affectedSkills", Arrays.asList(skills));
		loadSkills();
		
		plugin.getConfig().addDefault("deathMessage", "Your mcMMO stats have been reset!");
		formatDeathMessage();
		
		plugin.saveConfig();
	}
	
	private void loadSkills()
	{
		List<Object> list = plugin.getConfig().getList("affectedSkills");
    	for (int i = 0; i < list.size(); i++) {
    		String element = list.get(i).toString().toLowerCase();
    		if (element.equals("acrobatics")) {
    			affectedSkills.add(SkillType.ACROBATICS);
    		} else if (element.equals("alchemy")) {
    			affectedSkills.add(SkillType.ALCHEMY);
    		} else if (element.equals("archery")) {
    			affectedSkills.add(SkillType.ARCHERY);
    		} else if (element.equals("axes")) {
    			affectedSkills.add(SkillType.ARCHERY);
    		} else if (element.equals("enchanting")) {
    			affectedSkills.add(SkillType.ENCHANTING);
    		} else if (element.equals("excavation")) {
    			affectedSkills.add(SkillType.EXCAVATION);
    		} else if (element.equals("fishing")) {
    			affectedSkills.add(SkillType.FISHING);
    		} else if (element.equals("herbalism")) {
    			affectedSkills.add(SkillType.HERBALISM);
    		} else if (element.equals("mining")) {
    			affectedSkills.add(SkillType.MINING);
    		} else if (element.equals("repair")) {
    			affectedSkills.add(SkillType.REPAIR);
    		} else if (element.equals("swords")) {
    			affectedSkills.add(SkillType.SWORDS);
    		} else if (element.equals("taming")) {
    			affectedSkills.add(SkillType.TAMING);
    		} else if (element.equals("unarmed")) {
    			affectedSkills.add(SkillType.UNARMED);
    		} else if (element.equals("woodcutting")) {
    			affectedSkills.add(SkillType.WOODCUTTING);
    		}
    	}
	}
	
	private void formatDeathMessage()
	{
		deathMessage = plugin.getConfig().getString("deathMessage");
		
		if (deathMessage.contains("&skills")) {
			String skills = "";
			
			for (int i = 0; i < affectedSkills.size(); i++) {
				if (i == affectedSkills.size()-1 && affectedSkills.size() > 1) {
					skills += " and";
				}
				
				skills += " " + affectedSkills.get(i).toString().toLowerCase();
				
				if (i < affectedSkills.size()-1 && affectedSkills.size() > 2) {
					skills += ",";
				}
			}
			
			deathMessage = deathMessage.replaceFirst("&skills", skills);
		}
		
		deathMessage = deathMessage.replaceFirst("&penalty", plugin.getConfig().getString("penalty"));
	}
}
