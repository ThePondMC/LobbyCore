package me.garrett.lobby.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.garrett.lobby.GUI;
import me.garrett.lobby.Methods;

public class ClickEvent implements Listener {
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.equals(null)) {}
			if (e.getItem().equals(null)) {}
			if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().getDisplayName().equals(Methods.color("&c&lServer Selector"))) {
				GUI.openServers(e.getPlayer());
			}
		}
	}

}
