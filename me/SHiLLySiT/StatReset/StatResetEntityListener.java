package me.SHiLLySiT.StatReset;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.EntityDeathEvent;
import com.gmail.nossr50.Users;
import com.gmail.nossr50.datatypes.SkillType;

public class StatResetEntityListener extends EntityListener
{
	private final StatReset plugin;

    public StatResetEntityListener(StatReset instance)
    {
        plugin = instance;
    }
	
	public void onEntityDeath(EntityDeathEvent event)
	{
	    Entity ent = event.getEntity();
	    if (ent instanceof Player) {
	    	Player p = (Player) ent;
	    	if (p.hasPermission("statreset.user.reset")) {
	    		p.sendMessage(ChatColor.YELLOW + plugin.getConfig().getString("deathMessage"));
	    		Users.getProfile(p).modifyskill(SkillType.ALL, 0);
	    	} else {
	    		//p.sendMessage("This feature is off.");
	    	}
	    } 
	}
}
