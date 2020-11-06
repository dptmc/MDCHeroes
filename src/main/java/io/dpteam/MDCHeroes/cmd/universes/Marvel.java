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

public class Marvel implements CommandExecutor {
	public Marvel() {
		super();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("marvel") && sender != null) {
			if (args.length == 0) {
				if (!player.hasPermission("heroes.marvel.commands")) {
					Main.sendMessageMarvel(player, "&4Permission error! Missing: heroes.marvel.commands");
					return true;
				}

				Main.sendMessageMarvel(player, " ");
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l - &f/marvel hero <hero>"));
				return true;
			}

			if (args.length == 2 && args[0].equalsIgnoreCase("hero")) {
				if (!player.hasPermission("heroes.marvel.commands")) {
					Main.sendMessageMarvel(player, "&4Permission error! Missing: heroes.marvel.commands");
					return true;
				}

				if (Main.getPlugin().getConfig().getBoolean("DisableGameMode") && player.getGameMode() == GameMode.CREATIVE) {
					Main.sendMessageMarvel(player, "&4GameMode error! You must be in survival to run this commands!");
					return true;
				}

				if (!args[1].equalsIgnoreCase("thor") && !args[1].equalsIgnoreCase("ironman")) {
					Main.sendMessageMarvel(player, "&4Command error! Hero does not exist!");
					return true;
				}

				if (PlayerData.cooldownFlight.contains(player.getName()) || PlayerData.cooldownDash.contains(player.getName()) || PlayerData.cooldownStrike.contains(player.getName())) {
					Main.sendMessageMarvel(player, "&4Command error! You can not change hero during a cooldown!");
					return true;
				}

				if (args[1].equalsIgnoreCase("thor")) {
					if (!player.hasPermission("heroes.marvel.thor")) {
						Main.sendMessageMarvel(player, "&4Permission error! Missing: heroes.marvel.thor");
						return true;
					}

					if (HeroMethods.isHero(player, Heroes.THOR)) {
						Main.sendMessageMarvel(player, "&4Hero error! Hero already selected!");
						return true;
					}

					if (!PlayerData.inFlight.contains(player.getName()) && !PlayerData.cooldownDash.contains(player.getName()) && !PlayerData.cooldownFlight.contains(player.getName()) && !PlayerData.buildingSuit.contains(player.getName())) {
						PlayerData.poisonSuit.remove(player.getName());
						HeroMethods.setHero(player, Heroes.THOR);
						return true;
					}

					Main.sendMessageMarvel(player, "&4Command error! You can not change hero during a cooldown!");
					return true;
				}

				if (args[1].equalsIgnoreCase("ironman")) {
					if (!player.hasPermission("heroes.marvel.ironman")) {
						Main.sendMessageMarvel(player, "&4Permission error! Missing: heroes.marvel.ironman");
						return true;
					}

					if (HeroMethods.isHero(player, Heroes.IRON_MAN)) {
						Main.sendMessageMarvel(player, "&4Hero error! Hero already selected!");
						return true;
					}

					if (!PlayerData.inFlight.contains(player.getName()) && !PlayerData.cooldownDash.contains(player.getName()) && !PlayerData.cooldownFlight.contains(player.getName())) {
						HeroMethods.setHero(player, Heroes.IRON_MAN);
						return true;
					}

					Main.sendMessageMarvel(player, "&4Command error! You can not change hero during a cooldown!");
					return true;
				}
			}
		}

		return false;
	}
}
