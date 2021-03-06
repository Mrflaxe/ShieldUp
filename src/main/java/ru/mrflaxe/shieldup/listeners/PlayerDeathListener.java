package ru.mrflaxe.shieldup.listeners;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import ru.mrflaxe.shieldup.provider.ShieldProvider;

public class PlayerDeathListener implements Listener {

	private final ShieldProvider shieldProvider;
	
	public PlayerDeathListener(ShieldProvider shieldProvider) {
		this.shieldProvider = shieldProvider;
	}
	
	// if any player dies with our shield we remove it
	@EventHandler (priority = EventPriority.HIGH)
	public void PlayerDeathEvent (org.bukkit.event.entity.PlayerDeathEvent e) {
		Player p = e.getEntity();
		
		if(!shieldProvider.hasPlayer(p)) return;
		
		shieldProvider.removePlayer(p);
		
		List<ItemStack> drops = e.getDrops();
		List<ItemStack> blackList = drops.parallelStream()
				.filter(i -> i.getType() == Material.SHIELD)
				.filter(i -> i.getItemMeta().isUnbreakable())
				.collect(Collectors.toList());
		
		blackList.forEach(i -> drops.remove(i));
	}
	
	public void register(JavaPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
}
