package me.SHiLLySiT.StatReset;

import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public class StatReset extends JavaPlugin
{
	private String version;
	public static boolean hasPermissions = false;
	public static Server server;
	private StatResetCommands commands = new StatResetCommands(this);
	private StatResetEntityListener entityListener = new StatResetEntityListener(this);
	
	public void onEnable()
	{
		StatResetLogger.initialize(this, Logger.getLogger("Minecraft"));
		
		version = this.getDescription().getVersion();
		server = this.getServer();
		
		loadConfiguration();
		
		org.bukkit.plugin.PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.ENTITY_DEATH, entityListener, Event.Priority.Normal, this);
		
	    StatResetLogger.info("version " + version + " is enabled!");
	}
	
	public void onDisable()
	{
		StatResetLogger.info("Plugin disabled!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		return commands.executeCommand(sender, cmd, commandLabel, args);
	}
	
	private void loadConfiguration() 
	{
		getConfig().options().copyDefaults(true);
		getConfig().addDefault("deathMessage", "Your mcMMO stats have been reset!");
		saveConfig();
	}
}
