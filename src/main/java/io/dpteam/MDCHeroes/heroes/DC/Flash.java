package io.dpteam.MDCHeroes.heroes.DC;

import io.dpteam.MDCHeroes.Main;
import io.dpteam.MDCHeroes.manager.Heroes;
import io.dpteam.MDCHeroes.manager.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

public class Flash implements Listener {
	public Flash() {
		super();
	}

	/** @deprecated */
	@EventHandler
	@Deprecated
	public void onPlayerSneak(PlayerToggleSneakEvent event) {
		Player player = event.getPlayer();
		if (PlayerData.playerHeroes.get(player.getUniqueId()) == Heroes.FLASH && player.hasPermission("heroes.dc.flash.dash") && player.isSneaking()) {
			if (PlayerData.cooldownDash.contains(player.getName())) {
				Main.sendMessageDC(player, "&4Usage error! Cooldown still active!");
			} else {
				player.launchProjectile(EnderPearl.class);
				Main.sendMessageDC(player, "&6You dashed!");
				PlayerData.cooldownDash.add(player.getName());
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
					PlayerData.cooldownDash.remove(player.getName());
				}, 100L);
			}
		}

	}

	/** @deprecated */
	@EventHandler
	@Deprecated
	public void onPlayerEnderpeal(PlayerTeleportEvent event) {
		Player player = event.getPlayer();
		if (PlayerData.playerHeroes.get(player.getUniqueId()) == Heroes.FLASH && player.hasPermission("heroes.dc.flash.dash") && event.getCause() == TeleportCause.ENDER_PEARL) {
			event.setCancelled(true);
			player.setNoDamageTicks(1);
			player.teleport(event.getTo());
		}

	}
}
