package me.se7manopla;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ManoplaAPI {

	private FileAPI fileAPI;
	private FileConfiguration config;
	private String folder = "Manopla";
	private Main plugin;

	public ManoplaAPI() {
		this.plugin = Main.m;
		this.fileAPI = new FileAPI(this.plugin, "players.yml", folder);
		this.config = this.fileAPI.getConfig();
	}

	private void saveAndReload() {
		fileAPI.saveConfig();
		fileAPI.reloadConfig();
	}

	public void removePedra(Player player, String pedra) {
		config.set(player + "." + pedra, 0);

		saveAndReload();
	}

	public void setPedra(Player player, String pedra) {
		config.set(player + "." + pedra, 1);

		saveAndReload();
	}

	public int getPermPedra(Player player, String pedra) {
		int perm = config.getInt(player + "." + pedra);

		return perm;
	}

	public ItemStack getPedra(String pedra) {
		ItemStack item = null;
		ItemMeta meta = null;

		switch (pedra) {
		case "poder":
			item = new ItemStack(Material.WOOL);
			meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.DARK_PURPLE + "Pedra do Poder");
			meta.setLore(Arrays.asList(""));

			break;
		case "tempo":
			item = new ItemStack(Material.EMERALD);
			meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "Pedra do Tempo");
			meta.setLore(Arrays.asList(""));

			break;
		case "alma":
			item = new ItemStack(Material.GOLD_INGOT);
			meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "Pedra da Alma");
			meta.setLore(Arrays.asList(""));

			break;
		case "mente":
			item = new ItemStack(Material.GOLD_INGOT);
			meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.YELLOW + "Pedra da Mente");
			meta.setLore(Arrays.asList(""));

			break;
		case "realidade":
			item = new ItemStack(Material.REDSTONE);
			meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.RED + "Pedra da Realidade");
			meta.setLore(Arrays.asList(""));

			break;
		case "espaco":
			item = new ItemStack(Material.LAPIS_ORE);
			meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.BLUE + "Pedra do Espaço");
			meta.setLore(Arrays.asList(""));

			break;
		default:
			return null;
		}

		item.setItemMeta(meta);

		return item;
	}
	
	public ItemStack getPainelVidro(String pedra) {
		return null;
	}

	public void givePedra(Player player, String pedra) {
		ItemStack pedraItem = getPedra(pedra);

		player.getInventory().addItem(pedraItem);
	}

	public ItemStack getPedraPlayer(Player player, String pedra) {
		ItemStack item = null;
		int perm = getPermPedra(player, pedra);
		
		if (perm == 0) {
			item = getPedra(pedra);
		} else {
			item = getPainelVidro(pedra);
		}

		return item;
	}

}
