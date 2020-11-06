package io.dpteam.MDCHeroes.cmd.universes;

import io.dpteam.MDCHeroes.Main;
import io.dpteam.MDCHeroes.manager.HeroMethods;
import io.dpteam.MDCHeroes.manager.Heroes;
import io.dpteam.MDCHeroes.manager.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DC implements CommandExecutor {
	public DC() {
		super();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("dc") && sender != null) {
			if (args.length == 0) {
				if (!player.hasPermission("heroes.dc.commands")) {
					Main.sendMessageDC(player, "&4Permission error! Missing: heroes.dc.commands");
					return true;
				}

				Main.sendMessageDC(player, " ");
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l - &f/dc hero <hero>"));
				return true;
			}

			if (args.length == 2 && args[0].equalsIgnoreCase("hero")) {
				if (!player.hasPermission("heroes.dc.commands")) {
					Main.sendMessageDC(player, "&4Permission error! Missing: heroes.dc.commands");
					return true;
				}

				if (Main.getPlugin().getConfig().getBoolean("DisableGameMode") && player.getGameMode() == GameMode.CREATIVE) {
					Main.sendMessageDC(player, "&4GameMode error! You must be in survival to run this commands!");
					return true;
				}

				if (!args[1].equalsIgnoreCase("flash") && !args[1].equalsIgnoreCase("superman")) {
					Main.sendMessageDC(player, "&4Command error! Hero does not exist!");
					return true;
				}

				if (PlayerData.cooldownFlight.contains(player.getName()) || PlayerData.cooldownDash.contains(player.getName())) {
					Main.sendMessageDC(player, "&4Command error! You can not change hero during a cooldown!");
					return true;
				}

				if (args[1].equalsIgnoreCase("flash")) {
					if (!player.hasPermission("heroes.dc.flash")) {
						Main.sendMessageDC(player, "&4Permission error! Missing: heroes.dc.flash");
						return true;
					}

					if (HeroMethods.isHero(player, Heroes.FLASH)) {
						Main.sendMessageDC(player, "&4Hero error! Hero already selected!");
						return true;
					}

					if (!PlayerData.inFlight.contains(player.getName()) && !PlayerData.cooldownDash.contains(player.getName()) && !PlayerData.cooldownFlight.contains(player.getName()) && !PlayerData.buildingSuit.contains(player.getName())) {
						PlayerData.poisonSuit.remove(player.getName());
						HeroMethods.setHero(player, Heroes.FLASH);
						return true;
					}

					Main.sendMessageDC(player, "&4Command error! You can not change hero during a cooldown!");
					return true;
				}

				if (args[1].equalsIgnoreCase("superman")) {
					if (!player.hasPermission("heroes.dc.superman")) {
						Main.sendMessageDC(player, "&4Permission error! Missing: heroes.dc.superman");
						return true;
					}

					if (HeroMethods.isHero(player, Heroes.SUPERMAN)) {
						Main.sendMessageDC(player, "&4Hero error! Hero already selected!");
						return true;
					}

					PlayerData.poisonSuit.remove(player.getName());
					HeroMethods.setHero(player, Heroes.SUPERMAN);
					return true;
				}
			}
		}

		return false;
	}
}
