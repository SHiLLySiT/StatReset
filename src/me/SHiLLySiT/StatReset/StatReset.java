package me.SHiLLySiT.StatReset;

import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public class StatReset extends JavaPlugin
{
	private String version;
	public static boolean hasPermissions = false;
	public static Server server;
	public Commands commands = new Commands(this);
	public PlayerListener listener = null;
	public Config config = null;
	
	public void onEnable()
	{
		Log.initialize(this, Logger.getLogger("Minecraft"));
		
		version = this.getDescription().getVersion();
		server = this.getServer();
		config = new Config(this);
		listener = new PlayerListener(this);
		
		config.loadConfiguration();
		
	    Log.info("version " + version + " is enabled!");
	}
	
	public void onDisable()
	{
		Log.info("Plugin disabled!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		return commands.executeCommand(sender, cmd, commandLabel, args);
	}
	

}
