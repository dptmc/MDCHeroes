package io.dpteam.MDCHeroes.heroes.DC;

import java.util.Iterator;
import io.dpteam.MDCHeroes.Main;
import io.dpteam.MDCHeroes.manager.Heroes;
import io.dpteam.MDCHeroes.manager.PlayerData;
import net.minecraft.server.v1_16_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_16_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_16_R1.PacketPlayOutTitle.EnumTitleAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Superman implements Listener {
	public Superman() {
		super();
	}

	/** @deprecated */
	@EventHandler
	@Deprecated
	public void onPlayerSneak(PlayerToggleSneakEvent event) {
		Player player = event.getPlayer();
		if (PlayerData.playerHeroes.get(player.getUniqueId()) == Heroes.SUPERMAN && player.hasPermission("heroes.dc.superman.flight") && player.isSneaking()) {
			if (PlayerData.cooldownFlight.contains(player.getName())) {
				Main.sendMessageDC(player, "&4Usage error! Cooldown still active!");
			} else {
				if (player.getAllowFlight()) {
					return;
				}

				player.setAllowFlight(true);
				Main.sendMessageDC(player, "&6You used flight!");
				PlayerData.inFlight.add(player.getName());
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
					player.setAllowFlight(false);
					PlayerData.inFlight.remove(player.getName());
					PlayerData.cooldownFlight.add(player.getName());
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
						PlayerData.cooldownFlight.remove(player.getName());
					}, 240L);
				}, 200L);
			}
		}

	}

	/** @deprecated */
	@EventHandler
	@Deprecated
	public void onPlayerFall(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player)event.getEntity();
			if (PlayerData.playerHeroes.get(player.getUniqueId()) == Heroes.SUPERMAN && event.getCause() == DamageCause.FALL) {
				event.setCancelled(true);
			}
		}

	}

	/** @deprecated */
	@EventHandler
	@Deprecated
	public void onPlayerStep(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Material material = Material.valueOf(Main.getPlugin().getConfig().getString("KryptoniteMaterial"));
		int kryptoniteRadius = Main.getPlugin().getConfig().getInt("KryptoniteRadius");
		if (Main.getPlugin().getConfig().getBoolean("Kryptonite") && PlayerData.playerHeroes.get(player.getUniqueId()) == Heroes.SUPERMAN && event.getFrom().getBlockX() == event.getTo().getBlockX() && event.getFrom().getBlockY() == event.getTo().getBlockY() && event.getFrom().getBlockZ() == event.getTo().getBlockZ() && isBlockNearby(player.getLocation(), material, kryptoniteRadius)) {
			Iterator var5 = player.getActivePotionEffects().iterator();

			while(var5.hasNext()) {
				PotionEffect effect = (PotionEffect)var5.next();
				player.removePotionEffect(effect.getType());
			}

			if (player.getAllowFlight()) {
				player.setAllowFlight(false);
			}

			if (!PlayerData.cooldownFlight.contains(player.getName())) {
				PlayerData.cooldownFlight.add(player.getName());
			}

			PlayerData.cooldownKryptonite.add(player.getName());
			player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 0));
			player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 0));
			player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, Integer.MAX_VALUE, 0));
			PacketPlayOutTitle Kryptonite = new PacketPlayOutTitle(EnumTitleAction.ACTIONBAR, ChatSerializer.a("{\"text\":\"Kryptonite is nearby!\"}"));
			((CraftPlayer)player.getPlayer()).getHandle().playerConnection.sendPacket(Kryptonite);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
				PlayerData.cooldownKryptonite.remove(player.getName());
				PlayerData.cooldownFlight.remove(player.getName());
				Iterator var1 = player.getActivePotionEffects().iterator();

				while(var1.hasNext()) {
					PotionEffect effect = (PotionEffect)var1.next();
					player.removePotionEffect(effect.getType());
				}

				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
				player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 2));
				player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
				player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0));
				PacketPlayOutTitle Recovered = new PacketPlayOutTitle(EnumTitleAction.ACTIONBAR, ChatSerializer.a("{\"text\":\"You have recovered!\"}"));
				((CraftPlayer)player.getPlayer()).getHandle().playerConnection.sendPacket(Recovered);
			}, 100L);
		}

	}

	/** @deprecated */
	@Deprecated
	public static boolean isBlockNearby(Location location, Material blockType, int radius) {
		for(int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; ++x) {
			for(int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; ++y) {
				for(int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; ++z) {
					Block locBlock = location.getWorld().getBlockAt(x, y, z);
					if (locBlock.getType() == blockType) {
						return true;
					}
				}
			}
		}

		return false;
	}
}
