package me.garrett.lobby;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;


public class Methods {
	private final static int CENTER_PX = 154;
	 
	public static void sendCenteredMessage(Player player, String message){
	        if(message == null || message.equals("")) player.sendMessage("");
	                message = ChatColor.translateAlternateColorCodes('&', message);
	               
	                int messagePxSize = 0;
	                boolean previousCode = false;
	                boolean isBold = false;
	               
	                for(char c : message.toCharArray()){
	                        if(c == '§'){
	                                previousCode = true;
	                                continue;
	                        }else if(previousCode == true){
	                                previousCode = false;
	                                if(c == 'l' || c == 'L'){
	                                        isBold = true;
	                                        continue;
	                                }else isBold = false;
	                        }else{
	                                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
	                                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
	                                messagePxSize++;
	                        }
	                }
	               
	                int halvedMessageSize = messagePxSize / 2;
	                int toCompensate = CENTER_PX - halvedMessageSize;
	                int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
	                int compensated = 0;
	                StringBuilder sb = new StringBuilder();
	                while(compensated < toCompensate){
	                        sb.append(" ");
	                        compensated += spaceLength;
	                }
	                player.sendMessage(sb.toString() + message);
	        }
	public static void GiveSpawnItems(Player p) {
		p.getInventory().setItem(4, makeItem("345", 1, Methods.color("&c&lServer Selector")));
	}
	public static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("CrazyAuctions");
	
	public static String color(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	public static String removeColor(String msg) {
		return ChatColor.stripColor(msg);
	}
	
	public static String getPrefix() {
		return color("PREFIX");
	}
	
	public static ItemStack makeItem(String type, int amount) {
		int ty = 0;
		if(type.contains(":")) {
			String[] b = type.split(":");
			type = b[0];
			ty = Integer.parseInt(b[1]);
		}
		Material m = Material.matchMaterial(type);
		ItemStack item = null;
		try {
			item = new ItemStack(m, amount, (short) ty);
		}catch(Exception e) {
			item = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
		}
		return item;
	}
	
	public static ItemStack makeItem(String type, int amount, String name) {
		int ty = 0;
		if(type.contains(":")) {
			String[] b = type.split(":");
			type = b[0];
			ty = Integer.parseInt(b[1]);
		}
		Material m = Material.matchMaterial(type);
		ItemStack item = null;
		try {
			item = new ItemStack(m, amount, (short) ty);
		}catch(Exception e) {
			item = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
		}
		ItemMeta me = item.getItemMeta();
		me.setDisplayName(color(name));
		item.setItemMeta(me);
		return item;
	}
	
	public static ItemStack makeItem(String type, int amount, String name, List<String> lore) {
		ArrayList<String> l = new ArrayList<>();
		int ty = 0;
		if(type.contains(":")) {
			String[] b = type.split(":");
			type = b[0];
			ty = Integer.parseInt(b[1]);
		}
		Material m = Material.matchMaterial(type);
		ItemStack item = null;
		try {
			item = new ItemStack(m, amount, (short) ty);
		}catch(Exception e) {
			item = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
		}
		ItemMeta me = item.getItemMeta();
		me.setDisplayName(color(name));
		for(String L : lore)
			l.add(color(L));
		me.setLore(l);
		item.setItemMeta(me);
		return item;
	}
	
	public static ItemStack makeItem(Material material, int amount, int type, String name) {
		ItemStack item = new ItemStack(material, amount, (short) type);
		ItemMeta m = item.getItemMeta();
		m.setDisplayName(color(name));
		item.setItemMeta(m);
		return item;
	}
	
	public static ItemStack makeItem(Material material, int amount, int type, String name, List<String> lore) {
		ArrayList<String> l = new ArrayList<>();
		ItemStack item = new ItemStack(material, amount, (short) type);
		ItemMeta m = item.getItemMeta();
		m.setDisplayName(color(name));
		for(String L : lore)
			l.add(color(L));
		m.setLore(l);
		item.setItemMeta(m);
		return item;
	}
	
	public static ItemStack makeItem(Material material, int amount, int type, String name, List<String> lore, Map<Enchantment, Integer> enchants) {
		ItemStack item = new ItemStack(material, amount, (short) type);
		ItemMeta m = item.getItemMeta();
		m.setDisplayName(name);
		m.setLore(lore);
		item.setItemMeta(m);
		item.addUnsafeEnchantments(enchants);
		return item;
	}
	
	public static ItemStack addLore(ItemStack item, String i) {
		ArrayList<String> lore = new ArrayList<>();
		ItemMeta m = item.getItemMeta();
		if(item.getItemMeta().hasLore()) {
			lore.addAll(item.getItemMeta().getLore());
		}
		lore.add(i);
		m.setLore(lore);
		item.setItemMeta(m);
		return item;
	}
	
	public static ItemStack addLore(ItemStack item, List<String> list) {
		ArrayList<String> lore = new ArrayList<>();
		ItemMeta m = item.getItemMeta();
		if(item.getItemMeta().hasLore()) lore.addAll(item.getItemMeta().getLore());
		for(String i : list)
			lore.add(color(i));
		m.setLore(lore);
		item.setItemMeta(m);
		return item;
	}
	
	public static Integer getVersion() {
		String ver = Bukkit.getServer().getClass().getPackage().getName();
		ver = ver.substring(ver.lastIndexOf('.') + 1);
		ver = ver.replaceAll("_", "").replaceAll("R", "").replaceAll("v", "");
		return Integer.parseInt(ver);
	}
	
	public static ItemStack getItemInHand(Player player) {
		if(getVersion() >= 191) {
			return player.getItemInHand();
		}else {
			return player.getItemInHand();
		}
	}
	
	public static void setItemInHand(Player player, ItemStack item) {
		if(Methods.getVersion() >= 191) {
			player.setItemInHand(item);
		}else {
			player.setItemInHand(item);
		}
	}
	
	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		}catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	public static boolean isLong(String s) {
		try {
			Long.parseLong(s);
		}catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	public static Player getPlayer(String name) {
		return Bukkit.getServer().getPlayer(name);
	}
	
	@SuppressWarnings("deprecation")
	public static OfflinePlayer getOfflinePlayer(String name) {
		return Bukkit.getServer().getOfflinePlayer(name);
	}
	
	public static Location getLoc(Player player) {
		return player.getLocation();
	}
	
	public static void runCMD(Player player, String CMD) {
		player.performCommand(CMD);
	}
	
	public static boolean isOnline(String name) {
		for(Player player : Bukkit.getServer().getOnlinePlayers()) {
			if(player.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isOnline(String name, CommandSender p) {
		for(Player player : Bukkit.getServer().getOnlinePlayers()) {
			if(player.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		p.sendMessage(color("Online?"));
		return false;
	}
	
	
	public static List<ItemStack> getPage(List<ItemStack> list, Integer page) {
		List<ItemStack> items = new ArrayList<>();
		if(page <= 0) page = 1;
		int max = 45;
		int index = page * max - max;
		int endIndex = index >= list.size() ? list.size() - 1 : index + max;
		for(; index < endIndex; index++) {
			if(index < list.size()) items.add(list.get(index));
		}
		for(; items.size() == 0; page--) {
			if(page <= 0) break;
			index = page * max - max;
			endIndex = index >= list.size() ? list.size() - 1 : index + max;
			for(; index < endIndex; index++) {
				if(index < list.size()) items.add(list.get(index));
			}
		}
		return items;
	}
	
	public static List<Integer> getPageInts(List<Integer> list, Integer page) {
		List<Integer> items = new ArrayList<>();
		if(page <= 0) page = 1;
		int max = 45;
		int index = page * max - max;
		int endIndex = index >= list.size() ? list.size() - 1 : index + max;
		for(; index < endIndex; index++) {
			if(index < list.size()) items.add(list.get(index));
		}
		for(; items.size() == 0; page--) {
			if(page <= 0) break;
			index = page * max - max;
			endIndex = index >= list.size() ? list.size() - 1 : index + max;
			for(; index < endIndex; index++) {
				if(index < list.size()) items.add(list.get(index));
			}
		}
		return items;
	}
	
	public static int getMaxPage(List<ItemStack> list) {
		int maxPage = 1;
		int amount = list.size();
		for(; amount > 45; amount -= 45, maxPage++) ;
		return maxPage;
	}
	
	public static String convertToTime(long time) {
		Calendar C = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		int total = ((int) (cal.getTimeInMillis() / 1000) - (int) (C.getTimeInMillis() / 1000));
		int D = 0;
		int H = 0;
		int M = 0;
		int S = 0;
		for(; total > 86400; total -= 86400, D++) ;
		for(; total > 3600; total -= 3600, H++) ;
		for(; total > 60; total -= 60, M++) ;
		S += total;
		return D + "d " + H + "h " + M + "m " + S + "s ";
	}
	
	public static long convertToMill(String time) {
		Calendar cal = Calendar.getInstance();
		for(String i : time.split(" ")) {
			if(i.contains("D") || i.contains("d")) {
				cal.add(Calendar.DATE, Integer.parseInt(i.replaceAll("D", "").replaceAll("d", "")));
			}
			if(i.contains("H") || i.contains("h")) {
				cal.add(Calendar.HOUR, Integer.parseInt(i.replaceAll("H", "").replaceAll("h", "")));
			}
			if(i.contains("M") || i.contains("m")) {
				cal.add(Calendar.MINUTE, Integer.parseInt(i.replaceAll("M", "").replaceAll("m", "")));
			}
			if(i.contains("S") || i.contains("s")) {
				cal.add(Calendar.SECOND, Integer.parseInt(i.replaceAll("S", "").replaceAll("s", "")));
			}
		}
		return cal.getTimeInMillis();
	}
	
	public static boolean isInvFull(Player player) {
		if(player.getInventory().firstEmpty() == -1) {
			return true;
		}
		return false;
	}
	
	
	
}