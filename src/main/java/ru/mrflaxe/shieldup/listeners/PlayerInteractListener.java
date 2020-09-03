package ru.mrflaxe.shieldup.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import ru.mrflaxe.shieldup.provider.ShieldProvider;

public class PlayerInteractListener implements Listener {
	
	private final ShieldProvider shieldProvider;
	
	public PlayerInteractListener(ShieldProvider shieldProvider) {
		this.shieldProvider = shieldProvider;
	}
	
	@EventHandler
	public void PlayerInteractEvent(PlayerInteractEvent e) {
		Action act = e.getAction();
		
		// if player click RMB we continue work with it
		if(act == Action.RIGHT_CLICK_AIR || act == Action.RIGHT_CLICK_BLOCK) {
		
			Player p = e.getPlayer();
			PlayerInventory inv = p.getInventory();
			Material type = inv.getItemInMainHand().getType();
		
			// check if it was sword in a main hand
			if(!type.toString().endsWith("_SWORD")) return;
			
			// check if the player has an empty slot in offHand
			if(inv.getItemInOffHand().getType() == Material.AIR ) {
				
				// create new ItemStack of  unbreakable shield
				ItemStack item = new ItemStack(Material.SHIELD);
				ItemMeta meta = item.getItemMeta();
				meta.setUnbreakable(true);
				item.setItemMeta(meta);
				
				//give it to the player
				inv.setItemInOffHand(item);
				shieldProvider.addPlayer(p);
				return;
			}
			
		}
	}
		
	public void register(JavaPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

}
