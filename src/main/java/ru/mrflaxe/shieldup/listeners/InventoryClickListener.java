package ru.mrflaxe.shieldup.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import ru.mrflaxe.shieldup.provider.ShieldProvider;

public class InventoryClickListener implements Listener{
	
	private final ShieldProvider shieldProvider;
	
	public InventoryClickListener(ShieldProvider shieldProvider) {
		this.shieldProvider = shieldProvider;
	}
	
	// if the player makes clicks in inventory and have our shield we remove it
	@EventHandler
	public void InventoryClickEvent(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
		if(!shieldProvider.hasPlayer(p)) return;
		
		shieldProvider.removePlayer(p);
		p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
	}
	
	public void register(JavaPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
 
}
