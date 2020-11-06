package io.dpteam.MDCHeroes.listeners;

import io.dpteam.MDCHeroes.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class PlayerFall implements Listener {
	public PlayerFall() {
		super();
	}

	/** @deprecated */
	@EventHandler
	@Deprecated
	public void onPlayerFall(EntityDamageEvent event) {
		if (Main.getPlugin().getConfig().getBoolean("DisableFallDamage") && event.getEntity() instanceof Player) {
			Player player = (Player)event.getEntity();
			if (event.getCause() == DamageCause.FALL) {
				event.setCancelled(true);
			}
		}

	}
}
