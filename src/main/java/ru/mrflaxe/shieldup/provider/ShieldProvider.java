package ru.mrflaxe.shieldup.provider;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ShieldProvider {
	
	private final JavaPlugin plugin;
	
	// list of players who have a shield in real moment
	private List<Player> players;
	
	public ShieldProvider(JavaPlugin plugin) {
		this.plugin = plugin;
		
		this.players = new ArrayList<>();
	}
	
	// method to add any player into the list
	public void addPlayer(Player player) {
		if(hasPlayer(player)) return;
		players.add(player);
		
	}
	
	// method to remove any player from list
	public void removePlayer(Player player) {
		players.remove(player);
	}
	
	// this method check if any player already in the list
	public boolean hasPlayer(Player player) {
		return players.contains(player);
	}
	
	// the cycle which every 5 seconds remove shields from players inventories if they don't use it
	// it's start in onEnable() method
	public void run() {
		Bukkit.getScheduler().runTaskTimer(plugin, () -> {
			players.stream()
					.filter(p -> !p.isBlocking())
					.peek(p -> p.getInventory().setItemInOffHand(new ItemStack(Material.AIR)))
					.forEach(p -> players.remove(p));
		}, 0, 100);
	}
}
