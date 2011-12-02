package me.SHiLLySiT.StatReset;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.EntityDeathEvent;
import com.gmail.nossr50.Users;
import com.gmail.nossr50.datatypes.SkillType;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

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
		DamageCause c = p.getLastDamageCause().getCause();
	    	if (p.hasPermission("statreset.user.reset")) {
			SkillType type = null;
			if (c == DamageCause.FALL) {
			    type = SkillType.ACROBATICS;
			} else {
			    // do nothing
			}
			if (type != null) {
			    Users.getProfile(p).skillUp(type, -plugin.getConfig().getInt("levelLoss"));
			    p.sendMessage(ChatColor.YELLOW + plugin.getConfig().getString("deathMessage"));
			}

	    	} else {
	    		//p.sendMessage("This feature is off.");
	    	}
	    } 
	}
}
