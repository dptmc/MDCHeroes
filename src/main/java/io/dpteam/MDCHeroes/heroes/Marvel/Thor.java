package io.dpteam.MDCHeroes.heroes.Marvel;

import java.util.Set;
import io.dpteam.MDCHeroes.Main;
import io.dpteam.MDCHeroes.manager.Heroes;
import io.dpteam.MDCHeroes.manager.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class Thor implements Listener {
	public Thor() {
		super();
	}

	/** @deprecated */
	@EventHandler
	@Deprecated
	public void onPlayerSneak(PlayerToggleSneakEvent event) {
		Player player = event.getPlayer();
		if (PlayerData.playerHeroes.get(player.getUniqueId()) == Heroes.THOR && player.hasPermission("heroes.marvel.thor.strike") && player.isSneaking()) {
			if (PlayerData.cooldownStrike.contains(player.getName())) {
				Main.sendMessageMarvel(player, "&4Usage error! Cooldown still active!");
			} else {
				Main.sendMessageMarvel(player, "&6Strike is charging!");
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
					Location target = player.getTargetBlock((Set)null, 16).getLocation().clone().add(0.0D, 1.0D, 0.0D);
					player.getWorld().strikeLightning(target);
					Main.sendMessageMarvel(player, "&6Lightning stikes the ground!");
					PlayerData.cooldownStrike.add(player.getName());
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
						PlayerData.cooldownStrike.remove(player.getName());
					}, 200L);
				}, 40L);
			}
		}

	}
}
