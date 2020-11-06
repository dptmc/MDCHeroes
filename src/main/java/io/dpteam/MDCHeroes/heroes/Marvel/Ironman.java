package io.dpteam.MDCHeroes.heroes.Marvel;

import io.dpteam.MDCHeroes.Main;
import io.dpteam.MDCHeroes.manager.HeroMethods;
import io.dpteam.MDCHeroes.manager.Heroes;
import io.dpteam.MDCHeroes.manager.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Ironman implements Listener {
	public Ironman() {
		super();
	}

	/** @deprecated */
	@EventHandler
	@Deprecated
	public void onPlayerFall(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player)event.getEntity();
			if (PlayerData.playerHeroes.get(player.getUniqueId()) == Heroes.IRON_MAN && event.getCause() == DamageCause.FALL) {
				event.setCancelled(true);
			}
		}

	}

	/** @deprecated */
	@EventHandler(
	priority = EventPriority.NORMAL
	)
	@Deprecated
	public void onClick(InventoryClickEvent event) {
		Player player = (Player)event.getWhoClicked();
		if (event.getSlotType() == SlotType.ARMOR && HeroMethods.isHero(player, Heroes.IRON_MAN)) {
			event.setCancelled(true);
		}

	}

	/** @deprecated */
	@EventHandler
	@Deprecated
	public void onPlayerSneak(PlayerToggleSneakEvent event) {
		Player player = event.getPlayer();
		Location loc = player.getLocation();
		if (PlayerData.playerHeroes.get(player.getUniqueId()) == Heroes.IRON_MAN && player.hasPermission("heroes.marvel.ironman.suit") && player.isSneaking()) {
			if (PlayerData.hasSuit.contains(player.getName())) {
				return;
			}

			PlayerData.hasSuit.add(player.getName());
			PlayerData.buildingSuit.add(player.getName());
			Main.sendMessageMarvel(player, "&6A suit is on its way!");
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
				player.playSound(loc, Sound.BLOCK_ANVIL_PLACE, 1.0F, 1.0F);
				ItemStack IronManBoots = new ItemStack(Material.IRON_BOOTS);
				ItemMeta IronManBootsMeta = IronManBoots.getItemMeta();
				IronManBootsMeta.setDisplayName(ChatColor.GRAY + "Mark 42 Boots");
				IronManBootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
				IronManBootsMeta.addEnchant(Enchantment.PROTECTION_FIRE, 1, true);
				IronManBoots.setItemMeta(IronManBootsMeta);
				player.getInventory().setBoots(IronManBoots);
				player.setAllowFlight(true);
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &4Suit Percentage: 25%"));
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
					player.playSound(loc, Sound.BLOCK_ANVIL_PLACE, 1.0F, 1.0F);
					player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 2));
					player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 2));
					ItemStack IronManChest = new ItemStack(Material.IRON_CHESTPLATE);
					ItemMeta IronManChestMeta = IronManChest.getItemMeta();
					IronManChestMeta.setDisplayName(ChatColor.GRAY + "Mark 42 Chest");
					IronManChestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
					IronManChestMeta.addEnchant(Enchantment.PROTECTION_FIRE, 2, true);
					IronManChestMeta.addEnchant(Enchantment.THORNS, 3, true);
					IronManChest.setItemMeta(IronManChestMeta);
					player.getInventory().setChestplate(IronManChest);
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &4Suit Percentage: 50%"));
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
						player.playSound(loc, Sound.BLOCK_ANVIL_PLACE, 1.0F, 1.0F);
						ItemStack IronManLeggings = new ItemStack(Material.IRON_LEGGINGS);
						ItemMeta IronManLeggingsMeta = IronManLeggings.getItemMeta();
						IronManLeggingsMeta.setDisplayName(ChatColor.GRAY + "Mark 42 Leggings");
						IronManLeggingsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
						IronManLeggingsMeta.addEnchant(Enchantment.PROTECTION_FIRE, 2, true);
						IronManLeggings.setItemMeta(IronManLeggingsMeta);
						player.getInventory().setLeggings(IronManLeggings);
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &4Suit Percentage: 75%"));
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
							player.playSound(loc, Sound.BLOCK_ANVIL_PLACE, 1.0F, 1.0F);
							ItemStack IronManHelmet = new ItemStack(Material.IRON_HELMET);
							ItemMeta IronManHelmetMeta = IronManHelmet.getItemMeta();
							IronManHelmetMeta.setDisplayName(ChatColor.GRAY + "Mark 42 Helmet");
							IronManHelmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
							IronManHelmetMeta.addEnchant(Enchantment.PROTECTION_FIRE, 2, true);
							IronManHelmetMeta.addEnchant(Enchantment.WATER_WORKER, 2, true);
							IronManHelmetMeta.addEnchant(Enchantment.OXYGEN, 2, true);
							IronManHelmet.setItemMeta(IronManHelmetMeta);
							player.getInventory().setHelmet(IronManHelmet);
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &4Suit Percentage: 100%"));
							player.sendMessage("");
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &6Flight systems operational!"));
							player.sendMessage("");
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &6Body scan results:"));
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &6Health: " + player.getHealth()));
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &6Hunger: " + player.getFoodLevel()));
							player.sendMessage("");
							PlayerData.buildingSuit.remove(player.getName());
						}, 30L);
					}, 20L);
				}, 40L);
			}, 80L);
		}

	}

	/** @deprecated */
	@EventHandler
	@Deprecated
	public void onPlayerTakeDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player)event.getEntity();
			if (HeroMethods.isHero(player, Heroes.IRON_MAN) && PlayerData.hasSuit.contains(player.getName())) {
				if (event.getCause() == DamageCause.FIRE || event.getCause() == DamageCause.FIRE_TICK) {
					if (PlayerData.isOnFire.contains(player.getName())) {
						return;
					}

					PlayerData.isOnFire.add(player.getName());
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &6Warning! Fire detected!"));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &6Fire suppression started!"));
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
						player.setFireTicks(0);
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &6Fire suppression successful!"));
						PlayerData.isOnFire.remove(player.getName());
					}, 40L);
				}

				if (event.getCause() == DamageCause.DROWNING) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &6Warning! Oxygen low!"));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &6Surfacing is advised!"));
				}

				if (event.getCause() == DamageCause.POISON) {
					if (PlayerData.poisonSuit.contains(player.getName())) {
						return;
					}

					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &6Warning! Unknown chemical detected!"));
					player.sendMessage("");
					PlayerData.poisonSuit.add(player.getName());
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &6Starting scan"));
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &6Scan 25%"));
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &6Scan 75%"));
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &6Scan 100%"));
								player.sendMessage("");
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &6Chemical identified!"));
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &6Antidote injected!"));
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &6Vital signs normalizing!"));
								player.removePotionEffect(PotionEffectType.POISON);
								PlayerData.poisonSuit.remove(player.getName());
							}, 30L);
						}, 30L);
					}, 30L);
				}

				if (player.getHealth() < 10.0D) {
					if (PlayerData.isLowHealth.contains(player.getName())) {
						return;
					}

					PlayerData.isLowHealth.add(player.getName());
					player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &6Warning! High damage sustained!"));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &6Adrenaline has been administered"));
				}
			}
		}

	}

	/** @deprecated */
	@EventHandler
	@Deprecated
	public void onPlayerHeal(EntityRegainHealthEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player)event.getEntity();
			if (HeroMethods.isHero(player, Heroes.IRON_MAN) && PlayerData.hasSuit.contains(player.getName()) && player.getHealth() == 11.0D) {
				player.removePotionEffect(PotionEffectType.REGENERATION);
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lJ.A.R.V.I.S &f&l>> &6Vital signs normalizing."));
			}
		}

	}
}
