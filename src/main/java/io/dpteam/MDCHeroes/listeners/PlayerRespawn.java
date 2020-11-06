package io.dpteam.MDCHeroes.listeners;

import io.dpteam.MDCHeroes.Main;
import io.dpteam.MDCHeroes.manager.Heroes;
import io.dpteam.MDCHeroes.manager.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerRespawn implements Listener {
	public PlayerRespawn() {
		super();
	}

	/** @deprecated */
	@EventHandler
	@Deprecated
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		if (PlayerData.playerHeroes.get(player.getUniqueId()) == Heroes.IRON_MAN) {
			PlayerData.hasSuit.remove(player.getName());
			PlayerData.poisonSuit.remove(player.getName());
			PlayerData.isOnFire.remove(player.getName());
			player.setAllowFlight(false);
		}

		if (PlayerData.playerHeroes.get(player.getUniqueId()) == Heroes.SUPERMAN) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
				player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 2));
				player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
				player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0));
			}, 10L);
		}

		if (PlayerData.playerHeroes.get(player.getUniqueId()) == Heroes.FLASH) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
				player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0));
			}, 10L);
		}

	}
}
