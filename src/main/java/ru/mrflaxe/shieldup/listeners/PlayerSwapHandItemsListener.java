package ru.mrflaxe.shieldup.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import ru.mrflaxe.shieldup.provider.ShieldProvider;

public class PlayerSwapHandItemsListener implements Listener {

	private final ShieldProvider shieldProvider;
	
	public PlayerSwapHandItemsListener(ShieldProvider shieldProvider) {
		this.shieldProvider = shieldProvider;
	}
	
	// if any player swap hand items we check this event for our shield
	@EventHandler
	public void PlayerSwapHandItemsEvent (org.bukkit.event.player.PlayerSwapHandItemsEvent e) {
		Player p = e.getPlayer();
		
		// if the player has our shield we remove it
		if(!shieldProvider.hasPlayer(p)) return;
		
		shieldProvider.removePlayer(p);
		e.getMainHandItem().setType(Material.AIR);
	}
	
	public void register(JavaPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
}
