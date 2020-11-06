package io.dpteam.MDCHeroes.listeners;

import io.dpteam.MDCHeroes.Main;
import io.dpteam.MDCHeroes.manager.Heroes;
import io.dpteam.MDCHeroes.manager.PlayerData;
import io.dpteam.MDCHeroes.manager.Universe;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class PlayerGamemodeChange implements Listener {
	public PlayerGamemodeChange() {
		super();
	}

	/** @deprecated */
	@EventHandler
	@Deprecated
	public void onPlayerChangeGamemode(PlayerGameModeChangeEvent event) {
		Player player = event.getPlayer();
		if (Main.getPlugin().getConfig().getBoolean("DisableGameMode") && (((Heroes)PlayerData.playerHeroes.get(player.getUniqueId())).getUniverse() == Universe.DC || ((Heroes)PlayerData.playerHeroes.get(player.getUniqueId())).getUniverse() == Universe.MARVEL) && event.getNewGameMode() == GameMode.CREATIVE) {
			event.setCancelled(true);
			Main.sendMessageHeroes(player, "&4GameMode error! You can not change GameMode as a hero!");
		}

	}
}
