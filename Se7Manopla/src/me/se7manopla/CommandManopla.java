package me.se7manopla;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManopla implements CommandExecutor {

	private DisplayManopla displayManopla = new DisplayManopla();
	private ManoplaAPI manoplaAPI = new ManoplaAPI();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		Player player = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("manopla")) {
			if (sender instanceof Player) {
				if (player.isOp()) {
					if (args.length == 2 || args.length == 3) {
						String comando = args[0];
						String pedra = args[1];

						if (comando.equals("give")) {
							if (args.length == 2) {
								manoplaAPI.givePedra(player, pedra);
							} else {
								Player target = null;

								try {
									target = Bukkit.getPlayerExact(args[2]);
								} catch (Exception e) {
									player.sendMessage(ChatColor.RED + "Player não encontrado.");
								}

								if (target.isOnline()) {
									manoplaAPI.givePedra(target, pedra);
								} else {
									player.sendMessage(ChatColor.RED + "O player não está online.");
								}
							}
						} else {
							player.sendMessage(ChatColor.YELLOW + "Comandos Manopla");
							player.sendMessage("");
							player.sendMessage(ChatColor.YELLOW + "- /manopla -> Abre o menu de manopla");
							player.sendMessage(
									ChatColor.YELLOW + "- /manopla give (pedra) -> Pega a pedra que você digita");
							player.sendMessage(ChatColor.YELLOW
									+ "- /manopla give (pedra) (player) -> Giva a pedra escolhida a um player");
						}
					} else if (args.length == 0) {
						displayManopla.abrirMenu(player);
					} else {
						player.sendMessage(ChatColor.YELLOW + "Comandos Manopla");
						player.sendMessage("");
						player.sendMessage(ChatColor.YELLOW + "- /manopla -> Abre o menu de manopla");
						player.sendMessage(
								ChatColor.YELLOW + "- /manopla give (pedra) -> Pega a pedra que você digita");
						player.sendMessage(ChatColor.YELLOW
								+ "- /manopla give (pedra) (player) -> Giva a pedra escolhida a um player");
					}
				} else {
					displayManopla.abrirMenu(player);
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Apenas players podem utilizar este comando.");
			}
		}

		return false;
	}

}
