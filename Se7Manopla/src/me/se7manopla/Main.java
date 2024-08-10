package me.se7manopla;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Main m;
	
	private CommandSender sender = Bukkit.getConsoleSender();
	private PluginManager pm = Bukkit.getPluginManager();
	
	@Override
	public void onEnable() {
		sender.sendMessage(ChatColor.RED + "Se7Manopla - Ligado");
		
		getCommand("manopla").setExecutor(new CommandManopla());
		
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		sender.sendMessage(ChatColor.RED + "Se7Manopla - Desligado");
		
		HandlerList.unregisterAll();
		
		super.onDisable();
	}
	
	@Override
	public void onLoad() {
		m = this;
		
		super.onLoad();
	}

}
