package ru.mrflaxe.shieldup.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import ru.mrflaxe.shieldup.provider.ShieldProvider;

public class PlayerQuitListener implements Listener{

	private final ShieldProvider shieldProvider;
	
	public PlayerQuitListener(ShieldProvider shieldProvider) {
		this.shieldProvider = shieldProvider;
	}
	
	// if any player quit with our shield we remove it
	@EventHandler
	public void PlayerQuitEvent(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		if(!shieldProvider.hasPlayer(p)) return;
		
		shieldProvider.removePlayer(p);
		p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
	}
	
	public void register (JavaPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
}
