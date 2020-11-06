package io.dpteam.MDCHeroes.listeners;

import io.dpteam.MDCHeroes.manager.Heroes;
import io.dpteam.MDCHeroes.manager.PlayerData;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {
	public PlayerDeath() {
		super();
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		if (PlayerData.playerHeroes.get(player.getUniqueId()) == Heroes.IRON_MAN) {
			player.getInventory().remove(Material.IRON_BOOTS);
			player.getInventory().remove(Material.IRON_LEGGINGS);
			player.getInventory().remove(Material.IRON_HELMET);
			player.getInventory().remove(Material.IRON_CHESTPLATE);
			PlayerData.poisonSuit.remove(player.getName());
		}

	}
}
