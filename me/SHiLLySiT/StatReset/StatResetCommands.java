package me.SHiLLySiT.StatReset;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatResetCommands {
	@SuppressWarnings("unused")
	private StatReset plugin;
	
	public StatResetCommands(StatReset instance) {
	    this.plugin = instance;
	}
	
	public boolean executeCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("statreset"))  {
			//if no arguments, return date
			if (args.length == 0) {
				if (sender instanceof Player) { 
					Player p = (Player) sender;
					if (p.hasPermission("statreset.user.resetskills")) {
						p.sendMessage(ChatColor.YELLOW + "mcMMO stat reset on death is " + ChatColor.GREEN + "*ON*");
					} else {
						p.sendMessage(ChatColor.YELLOW + "mcMMO stat reset on death is " + ChatColor.RED + "*OFF*");
					}
				}
			} 
			return true;
		} else {
			return false;
		}
	}
}
