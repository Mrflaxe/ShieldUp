package ru.mrflaxe.shieldup;

import org.bukkit.plugin.java.JavaPlugin;

import ru.mrflaxe.shieldup.listeners.InventoryClickListener;
import ru.mrflaxe.shieldup.listeners.InventoryOpenListener;
import ru.mrflaxe.shieldup.listeners.PlayerDeathListener;
import ru.mrflaxe.shieldup.listeners.PlayerInteractListener;
import ru.mrflaxe.shieldup.listeners.PlayerQuitListener;
import ru.mrflaxe.shieldup.listeners.PlayerSwapHandItemsListener;
import ru.mrflaxe.shieldup.provider.ShieldProvider;

public class ShieldUp extends JavaPlugin {
	
	private ShieldProvider shieldProvider;
	
	@Override
	public void onEnable() {
		this.shieldProvider = new ShieldProvider(this);
		shieldProvider.run();
		
		registerEvents();
	}
	
	private void registerEvents() {
		new InventoryOpenListener(shieldProvider).register(this);
		new PlayerSwapHandItemsListener(shieldProvider).register(this);
		new PlayerDeathListener(shieldProvider).register(this);
		new PlayerInteractListener(shieldProvider).register(this);
		new InventoryClickListener(shieldProvider).register(this);
		new PlayerQuitListener(shieldProvider).register(this);
	}
	
}
