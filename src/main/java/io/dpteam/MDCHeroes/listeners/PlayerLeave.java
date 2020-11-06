package io.dpteam.MDCHeroes.listeners;

import java.util.Iterator;
import io.dpteam.MDCHeroes.Main;
import io.dpteam.MDCHeroes.manager.HeroMethods;
import io.dpteam.MDCHeroes.manager.Heroes;
import io.dpteam.MDCHeroes.manager.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class PlayerLeave implements Listener {
	public PlayerLeave() {
		super();
	}

	/** @deprecated */
	@EventHandler
	@Deprecated
	public void onPlayerLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if (HeroMethods.isHero(player, Heroes.IRON_MAN)) {
			player.getInventory().setBoots((ItemStack)null);
			player.getInventory().setLeggings((ItemStack)null);
			player.getInventory().setChestplate((ItemStack)null);
			player.getInventory().setHelmet((ItemStack)null);
			PlayerData.hasSuit.remove(player.getName());
			PlayerData.poisonSuit.remove(player.getName());
			PlayerData.isOnFire.remove(player.getName());
		}

		if (Main.getPlugin().getConfig().getBoolean("RemoveOnLeave")) {
			Iterator var3 = player.getActivePotionEffects().iterator();

			while(var3.hasNext()) {
				PotionEffect effect = (PotionEffect)var3.next();
				player.removePotionEffect(effect.getType());
			}

			PlayerData.playerHeroes.remove(player.getUniqueId());
		}

	}
}
