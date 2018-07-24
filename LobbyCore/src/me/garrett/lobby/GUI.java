package me.garrett.lobby;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;


public class GUI implements Listener {
	
	public static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("LobbyCore");
	
	public static void openServers(Player player) {
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', "&b&lServers"));
		inv.setItem(0, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(1, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(2, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(3, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(4, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(5, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(6, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(7, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(8, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(9, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(17, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(18, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(26, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(27, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(35, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(36, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(44, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(45, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(46, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(47, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(48, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(49, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(50, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(51, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(52, Methods.makeItem("160:15", 1, Methods.color(" ")));
		inv.setItem(53, Methods.makeItem("160:15", 1, Methods.color(" ")));
		player.openInventory(inv);
	}
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		
		final Inventory inv = e.getInventory();
		if(inv != null) {
			if(inv.getName().contains(ChatColor.translateAlternateColorCodes('&', "&b&lServers"))) {
				e.setCancelled(true);
			}
		}
	}
}
