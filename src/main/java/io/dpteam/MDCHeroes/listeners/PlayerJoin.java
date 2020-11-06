package io.dpteam.MDCHeroes.listeners;

import io.dpteam.MDCHeroes.Main;
import io.dpteam.MDCHeroes.manager.HeroMethods;
import io.dpteam.MDCHeroes.manager.Heroes;
import io.dpteam.MDCHeroes.manager.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
	public PlayerJoin() {
		super();
	}

	/** @deprecated */
	@EventHandler
	@Deprecated
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (Main.getPlugin().getConfig().getBoolean("RemoveOnLeave")) {
			PlayerData.playerHeroes.put(player.getUniqueId(), Heroes.HUMAN);
		}

		Main.sendMessageDC(player, "&6Current Hero: " + HeroMethods.getHero(player).getSimpleName());
	}
}
