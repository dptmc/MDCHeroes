package io.dpteam.MDCHeroes.manager;

import java.util.Iterator;
import java.util.Map.Entry;
import io.dpteam.MDCHeroes.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HeroMethods {
	public HeroMethods() {
		super();
	}

	public static void setHero(Player player, Heroes hero) {
		PlayerData.playerHeroes.put(player.getUniqueId(), hero);
		Iterator var2 = player.getActivePotionEffects().iterator();

		while(var2.hasNext()) {
			PotionEffect effect = (PotionEffect)var2.next();
			player.removePotionEffect(effect.getType());
		}

		var2 = hero.getPotionEffects().entrySet().iterator();

		while(var2.hasNext()) {
			Entry effects = (Entry)var2.next();
			player.addPotionEffect(new PotionEffect((PotionEffectType)effects.getKey(), Integer.MAX_VALUE, (Integer)effects.getValue()));
		}

		if (getHero(player).getUniverse() == Universe.DC) {
			Main.sendMessageDC(player, "&6" + hero.getSimpleName() + " selected!");
		}

		if (getHero(player).getUniverse() == Universe.MARVEL) {
			Main.sendMessageMarvel(player, "&6" + hero.getSimpleName() + " selected!");
		}

		player.getInventory().setBoots((ItemStack)null);
		player.getInventory().setLeggings((ItemStack)null);
		player.getInventory().setChestplate((ItemStack)null);
		player.getInventory().setHelmet((ItemStack)null);
		PlayerData.hasSuit.remove(player.getName());
	}

	public static Heroes getHero(Player player) {
		return (Heroes)PlayerData.playerHeroes.get(player.getUniqueId());
	}

	public static boolean isHero(Player player, Heroes hero) {
		return getHero(player) == hero;
	}
}
