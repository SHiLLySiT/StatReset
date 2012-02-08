package me.SHiLLySiT.StatReset;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands {
	private StatReset plugin;
	
	public Commands(StatReset plugin)
	{
	    this.plugin = plugin;
	}
	
	public boolean executeCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if (cmd.getName().equalsIgnoreCase("statreset"))  {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("check")) {
					if (sender instanceof Player) { 
						Player p = (Player) sender;
						if (p.hasPermission("statreset.user.resetskills")) {
							p.sendMessage(ChatColor.YELLOW + "mcMMO stat reset on death is " + ChatColor.GREEN + "*ON*");
						} else {
							p.sendMessage(ChatColor.YELLOW + "mcMMO stat reset on death is " + ChatColor.RED + "*OFF*");
						}
					}
				} else if (args[0].equalsIgnoreCase("deathmessage")) {
					if (sender instanceof Player) { 
						Player p = (Player) sender;
						if (p.hasPermission("statreset.admin.deathmessage")) {
							p.sendMessage(ChatColor.YELLOW + "Death message: " + plugin.config.deathMessage);
						} 
					}
				}
			}
		return true;
		} else {
			return false;
		}
	}
}
