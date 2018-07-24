package me.garrett.lobby;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.garrett.lobby.events.ClickEvent;
import me.garrett.lobby.events.FoodLossEvent;
import me.garrett.lobby.events.JoinEvent;
import me.garrett.lobby.events.MobSpawningEvent;
import net.milkbowl.vault.chat.Chat;

public class CoreMain extends JavaPlugin implements Listener  {
	private Plugin plugin;
	  private String format;
	  private Chat vaultChat = null;
	public void onEnable() {
		plugin = this;
		saveDefaultConfig();
		
		// Command Registers
		
		// Event Registers
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		registerEvents(this, new GUI());
		registerEvents(this, new JoinEvent());
		registerEvents(this, new ClickEvent());
		registerEvents(this, new MobSpawningEvent());
		registerEvents(this, new FoodLossEvent());
		refreshVault();
		reloadConfigValues();
	}
	
	public Plugin getPlugin() {
		return plugin;
	}
	public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
		Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
		}
	  private void reloadConfigValues()
	  {
	    this.format = Methods.color(getConfig().getString("format", "<{prefix}{name}{suffix}> {message}")
	      .replace("{name}", "%1$s")
	      .replace("{message}", "%2$s"));
	  }
	  private void refreshVault()
	  {
	    Chat vaultChat = (Chat)getServer().getServicesManager().load(Chat.class);
	    if (vaultChat != this.vaultChat) {
	      getLogger().info("New Vault Chat implementation registered: " + (vaultChat == null ? "null" : vaultChat.getName()));
	    }
	    this.vaultChat = vaultChat;
	  }
    @EventHandler(priority=EventPriority.LOWEST)
    public void onChatLow(AsyncPlayerChatEvent e)
    {
      e.setFormat(this.format);
    }
    
    @EventHandler(priority=EventPriority.HIGHEST)
    public void onChatHigh(AsyncPlayerChatEvent e)
    {
      String format = e.getFormat();
      if ((this.vaultChat != null) && (format.contains("{prefix}"))) {
        format = format.replace("{prefix}", Methods.color(this.vaultChat.getPlayerPrefix(e.getPlayer())));
      }
      if ((this.vaultChat != null) && (format.contains("{suffix}"))) {
        format = format.replace("{suffix}", Methods.color(this.vaultChat.getPlayerSuffix(e.getPlayer())));
      }
      e.setFormat(format);
    }
}
