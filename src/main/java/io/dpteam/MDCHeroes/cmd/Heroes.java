package io.dpteam.MDCHeroes.cmd;

import java.util.Iterator;
import io.dpteam.MDCHeroes.Main;
import io.dpteam.MDCHeroes.manager.HeroMethods;
import io.dpteam.MDCHeroes.manager.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class Heroes implements CommandExecutor {
	public Heroes() {
		super();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("heroes") && sender != null) {
			if (args.length == 0) {
				if (!player.hasPermission("heroes.commands")) {
					Main.sendMessageHeroes(player, "&4Permission error! Missing: heroes.commands");
					return true;
				}

				Main.sendMessageHeroes(player, " ");
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l - &f/heroes human"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l - &f/&bdc"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l - &f/&4marvel"));
				return true;
			}

			if (args.length == 1 && args[0].equalsIgnoreCase("human")) {
				if (!player.hasPermission("heroes.commands.human")) {
					Main.sendMessageHeroes(player, "&4Permission error! Missing: heroes.commands.human");
					return true;
				}

				if (HeroMethods.isHero(player, io.dpteam.MDCHeroes.manager.Heroes.HUMAN)) {
					Main.sendMessageHeroes(player, "&4Hero error! Your already human!");
					return true;
				}

				if (!PlayerData.cooldownDash.contains(player.getName()) && !PlayerData.cooldownFlight.contains(player.getName())) {
					PlayerData.playerHeroes.remove(player.getUniqueId());
					Iterator var6 = player.getActivePotionEffects().iterator();

					while(var6.hasNext()) {
						PotionEffect effect = (PotionEffect)var6.next();
						player.removePotionEffect(effect.getType());
					}

					Main.sendMessageHeroes(player, "&6State returned to human!");
					return true;
				}

				Main.sendMessageHeroes(player, "&4Command error! You can not change state during a cooldown!");
				return true;
			}
		}

		return false;
	}
}
