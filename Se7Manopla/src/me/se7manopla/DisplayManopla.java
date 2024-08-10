package me.se7manopla;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class DisplayManopla {
	
	private ManoplaAPI manoplaAPI = new ManoplaAPI();
	
	public void abrirMenu(Player player) {
		Inventory inv = Bukkit.createInventory(null, 4 * 9, ChatColor.GRAY + "Menu de Manopla");
		
		inv.setItem(11, manoplaAPI.getPedraPlayer(player, null));
		inv.setItem(12, manoplaAPI.getPedraPlayer(player, null));
		inv.setItem(13, manoplaAPI.getPedraPlayer(player, null));
		inv.setItem(14, manoplaAPI.getPedraPlayer(player, null));
		inv.setItem(15, manoplaAPI.getPedraPlayer(player, null));
		inv.setItem(22, manoplaAPI.getPedraPlayer(player, null));
		
		player.openInventory(inv);
	}

}
