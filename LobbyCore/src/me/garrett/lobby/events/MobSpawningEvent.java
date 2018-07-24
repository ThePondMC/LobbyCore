package me.garrett.lobby.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class MobSpawningEvent implements Listener {
	
	@EventHandler
	public void onSpawn(EntitySpawnEvent e) {
		if (e.getEntity() instanceof Player) {
			return;
		} else {
			e.setCancelled(true);
		}
	}

}
