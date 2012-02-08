package me.SHiLLySiT.StatReset;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import com.gmail.nossr50.Users;
import com.gmail.nossr50.datatypes.PlayerProfile;
import com.gmail.nossr50.datatypes.SkillType;

public class PlayerListener implements Listener
{
	private StatReset plugin;
	
    public PlayerListener(StatReset plugin)
    {
    	this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
	
    @EventHandler 
	public void onEntityDeath(EntityDeathEvent event)
	{
    	Entity ent = event.getEntity();
    	if (ent instanceof Player) {
	    	Player p = (Player) ent;
	    	if (p.hasPermission("statreset.user.reset")) {
	    		applyPenalty(p);
	    	} else {
	    		//p.sendMessage("This feature is off.");
	    	}
    	}
	}
    
	private void applyPenalty(Player p)
    {
    	PlayerProfile user = Users.getProfile(p);
    	double penalty = plugin.getConfig().getDouble("penalty");
    	String type = plugin.getConfig().getString("type");
    	
    	for (int i = 0; i < plugin.config.affectedSkills.size(); i++) {
    		
    		SkillType skill = plugin.config.affectedSkills.get(i);
    		
    		if (type.toLowerCase().equals("level")) { // if death affects level
    			int current = user.getSkillLevel(skill);
    			
	    		if (penalty == 0) { // if 0, reset skill(s)
	    			user.modifyskill(skill, 0);
	    		} else { // else, subtract penalty from current level
	    			if (user.getSkillLevel(skill) - penalty > 0) {
		    			user.modifyskill(skill, current - (int) penalty);
	    			} else {
	    				user.modifyskill(skill, 0);
	    			}
	    		}
    		} else if (type.toLowerCase().equals("experience")) { // if death affects experience
    			int currentXP = user.getSkillXpLevel(skill);
    			
    			if (penalty <= 100 && penalty > 1) { 
	    			user.removeXP(skill, ((int) Math.ceil(currentXP * (penalty/100))));
    			}
    		}
    	}
    	
    	p.sendMessage(ChatColor.YELLOW + plugin.config.deathMessage);
    }
}
