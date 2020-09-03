package ru.mrflaxe.shieldup.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import ru.mrflaxe.shieldup.provider.ShieldProvider;

public class InventoryOpenListener implements Listener {
	
	private final ShieldProvider shieldProvider;
	
	public InventoryOpenListener(ShieldProvider shieldProvider) {
		this.shieldProvider = shieldProvider;
	}
	
	// if player open inventory with a shield we remove it
	@EventHandler
	public void InventoryOpenEvent (InventoryOpenEvent e) {
		Player p = (Player) e.getPlayer();
		
		if(!shieldProvider.hasPlayer(p)) return;

		shieldProvider.removePlayer(p);
		p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
		
	}
	
	public void register(JavaPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
}
