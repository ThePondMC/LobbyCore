package me.garrett.lobby.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.garrett.lobby.Methods;

public class DeathRespawnEvent implements Listener {
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		if (e.getEntity() instanceof Player) {
		Methods.GiveSpawnItems(e.getEntity().getPlayer());
		e.setDeathMessage(null);
		}
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		
	}
}
