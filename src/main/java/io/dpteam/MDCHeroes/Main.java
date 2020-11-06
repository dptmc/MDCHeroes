package io.dpteam.MDCHeroes;

import java.io.File;
import java.util.Iterator;
import io.dpteam.MDCHeroes.cmd.Heroes;
import io.dpteam.MDCHeroes.cmd.universes.DC;
import io.dpteam.MDCHeroes.cmd.universes.Marvel;
import io.dpteam.MDCHeroes.heroes.DC.Flash;
import io.dpteam.MDCHeroes.heroes.DC.Superman;
import io.dpteam.MDCHeroes.heroes.Marvel.Ironman;
import io.dpteam.MDCHeroes.heroes.Marvel.Thor;
import io.dpteam.MDCHeroes.listeners.PlayerDeath;
import io.dpteam.MDCHeroes.listeners.PlayerFall;
import io.dpteam.MDCHeroes.listeners.PlayerGamemodeChange;
import io.dpteam.MDCHeroes.listeners.PlayerJoin;
import io.dpteam.MDCHeroes.listeners.PlayerLeave;
import io.dpteam.MDCHeroes.listeners.PlayerRespawn;
import io.dpteam.MDCHeroes.manager.PlayerData;
import io.dpteam.MDCHeroes.manager.Universe;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

public class Main extends JavaPlugin {
	public static Main plugin;
	public static FileConfiguration config;
	public static File cfile;
	public static String prefixHeroes = "&2&lHeroes &f&l>>";
	public static String prefixDC = "&b&lDC &f&l>>";
	public static String prefixMarvel = "&4&lMarvel &f&l>>";

	public Main() {
		super();
	}

	public static Main getPlugin() {
		return plugin;
	}

	public static void sendMessageHeroes(Player player, String message) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefixHeroes + " " + message));
	}

	public static void sendMessageDC(Player player, String message) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefixDC + " " + message));
	}

	public static void sendMessageMarvel(Player player, String message) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefixMarvel + " " + message));
	}

	public void onEnable() {
		plugin = this;
		config = this.getConfig();
		config.options().copyDefaults(true);
		this.saveDefaultConfig();
		cfile = new File(this.getDataFolder(), "config.yml");
		this.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerFall(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerGamemodeChange(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
		this.getServer().getPluginManager().registerEvents(new Flash(), this);
		this.getServer().getPluginManager().registerEvents(new Superman(), this);
		this.getServer().getPluginManager().registerEvents(new Thor(), this);
		this.getServer().getPluginManager().registerEvents(new Ironman(), this);
		this.getCommand("heroes").setExecutor(new Heroes());
		this.getCommand("dc").setExecutor(new DC());
		this.getCommand("marvel").setExecutor(new Marvel());
		System.out.println("MDCHeroes Enabled");
	}

	@Override
	public void onDisable() {
		Iterator var1 = Bukkit.getServer().getOnlinePlayers().iterator();

		while(var1.hasNext()) {
			Player pl = (Player)var1.next();
			if (((io.dpteam.MDCHeroes.manager.Heroes)PlayerData.playerHeroes.get(pl.getUniqueId())).getUniverse() == Universe.DC || ((io.dpteam.MDCHeroes.manager.Heroes)PlayerData.playerHeroes.get(pl.getUniqueId())).getUniverse() == Universe.MARVEL) {
				Iterator var3 = pl.getActivePotionEffects().iterator();

				while(var3.hasNext()) {
					PotionEffect effect = (PotionEffect)var3.next();
					pl.removePotionEffect(effect.getType());
				}
			}

			if (PlayerData.hasSuit.contains(pl.getName())) {
				pl.getInventory().setBoots((ItemStack)null);
				pl.getInventory().setLeggings((ItemStack)null);
				pl.getInventory().setChestplate((ItemStack)null);
				pl.getInventory().setHelmet((ItemStack)null);
				PlayerData.hasSuit.remove(pl.getName());
			}
			System.out.println("MDCHeroes Disabled");
		}
	}
}
