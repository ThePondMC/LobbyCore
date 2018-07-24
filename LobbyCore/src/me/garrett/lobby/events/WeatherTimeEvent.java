package me.garrett.lobby.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherTimeEvent implements Listener {
	@EventHandler
	public void onWeather(WeatherChangeEvent e) {
		e.setCancelled(true);
		e.getWorld().setThundering(false);
		e.getWorld().setTime(0);
	}
}
