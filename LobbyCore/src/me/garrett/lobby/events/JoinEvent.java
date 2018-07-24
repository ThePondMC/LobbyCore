package me.garrett.lobby.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.netty.buffer.Unpooled;
import me.garrett.lobby.Methods;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload;


public class JoinEvent implements Listener {
	
	public static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("LobbyCore");
	@EventHandler
	public void onLeave(PlayerJoinEvent e) {
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = (Player) e.getPlayer();
		e.setJoinMessage(null);
		Methods.sendCenteredMessage(p, Methods.color("&a&m-------------------------------------------------"));
		Methods.sendCenteredMessage(p, Methods.color("&f&lWelcome to (Name Here)"));
		Methods.sendCenteredMessage(p, Methods.color(" "));
		Methods.sendCenteredMessage(p, Methods.color("&eStore: &6https://store.namesoon.com/"));
		Methods.sendCenteredMessage(p, Methods.color("&a&m-------------------------------------------------"));
		p.getInventory().clear();
		p.getInventory().setHeldItemSlot(4);
		Methods.GiveSpawnItems(p);
		new BukkitRunnable() {

            @Override
            public void run() {
                openBook(p);
            }
        }.runTaskLater(plugin, 5L);
         }
	
	  private void openBook(Player player)
	    {
	        ItemStack book = new ItemStack(Material.WRITTEN_BOOK); //Create book ItemStack
	        BookMeta meta = (BookMeta)book.getItemMeta(); //Get BookMeta
	        meta.addPage(Methods.color("   &2&lNew Gamemode!\n  &2Skyblock Release!\n\n&rOur newest gamemode &lskyblock&r "
	        		+ "has been released! "
	        		+ "for more information on the gamemode you can visit our forums!")); //Add a page
	        book.setItemMeta(meta); //Set meta
	        ItemStack oldItem = player.getItemInHand(); //Get item in hand so we can set it back
	        player.setItemInHand(book); //Set item in hand to book
	        PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("MC|BOpen", new PacketDataSerializer(Unpooled.buffer())); //Create packet that tells the player to open a book
	        CraftPlayer craftPlayer = (CraftPlayer)player; //Craftplayer for sending packet
	        craftPlayer.getHandle().playerConnection.sendPacket(packet); //Send packet
	        player.setItemInHand(oldItem); //Set item in hand back
	    }
}
