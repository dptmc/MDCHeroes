package io.dpteam.MDCHeroes.manager;

import java.util.HashMap;
import org.bukkit.potion.PotionEffectType;

public enum Heroes {
	HUMAN("Human", Universe.NONE, (HashMap)null),
	FLASH("Flash", Universe.DC, new HashMap() {
		{
			this.put(PotionEffectType.SPEED, 2);
			this.put(PotionEffectType.REGENERATION, 0);
			this.put(PotionEffectType.INCREASE_DAMAGE, 1);
		}
	}),
	SUPERMAN("Superman", Universe.DC, new HashMap() {
		{
			this.put(PotionEffectType.SPEED, 0);
			this.put(PotionEffectType.REGENERATION, 0);
			this.put(PotionEffectType.INCREASE_DAMAGE, 2);
			this.put(PotionEffectType.DAMAGE_RESISTANCE, 0);
		}
	}),
	IRON_MAN("Iron Man", Universe.MARVEL, new HashMap() {
	}),
	THOR("Thor", Universe.MARVEL, new HashMap() {
		{
			this.put(PotionEffectType.SPEED, 0);
			this.put(PotionEffectType.REGENERATION, 0);
			this.put(PotionEffectType.INCREASE_DAMAGE, 2);
			this.put(PotionEffectType.DAMAGE_RESISTANCE, 1);
			this.put(PotionEffectType.ABSORPTION, 1);
			this.put(PotionEffectType.FAST_DIGGING, 1);
		}
	});

	private String simpleName;
	private Universe universe;
	private HashMap potionEffects;

	private Heroes(String simpleName, Universe universe, HashMap potionEffects) {
		this.simpleName = simpleName;
		this.universe = universe;
		this.potionEffects = potionEffects;
	}

	public String getSimpleName() {
		return this.simpleName;
	}

	public Universe getUniverse() {
		return this.universe;
	}

	public HashMap getPotionEffects() {
		return this.potionEffects;
	}
}
