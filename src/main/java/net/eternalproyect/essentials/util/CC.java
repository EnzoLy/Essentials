package net.eternalproyect.essentials.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.org.apache.regexp.internal.RE;
import net.milkbowl.vault.chat.Chat;
import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class CC {

	private static final Map<String, ChatColor> MAP;

	public static final String BLUE;
	public static final String AQUA;
	public static final String YELLOW;
	public static final String RED;
	public static final String GRAY;
	public static final String GOLD;
	public static final String GREEN;
	public static final String WHITE;
	public static final String BLACK;
	public static final String BOLD;
	public static final String ITALIC;
	public static final String UNDER_LINE;
	public static final String STRIKE_THROUGH;
	public static final String RESET;
	public static final String MAGIC;
	public static final String DARK_BLUE;
	public static final String DARK_AQUA;
	public static final String DARK_GRAY;
	public static final String DARK_GREEN;
	public static final String DARK_PURPLE;
	public static final String DARK_RED;
	public static final String PINK;
	public static final String MENU_BAR;
	public static final String CHAT_BAR;
	public static final String SB_BAR;
	public static final String UNICODE_VERTICAL_BAR ;
	public static final String UNICODE_CAUTION;
	public static final String UNICODE_ARROW_LEFT;
	public static final String UNICODE_ARROW_RIGHT;
	public static final String UNICODE_ARROWS_LEFT;
	public static final String UNICODE_ARROWS_RIGHT;
	public static final String UNICODE_HEART;

	static {
		MAP = new HashMap<>();
		MAP.put("pink", ChatColor.LIGHT_PURPLE);
		MAP.put("orange", ChatColor.GOLD);
		MAP.put("purple", ChatColor.DARK_PURPLE);

		for (ChatColor chatColor : ChatColor.values()) {
			MAP.put(chatColor.name().toLowerCase().replace("_", ""), chatColor);
		}

		BLUE = ChatColor.BLUE.toString();
		AQUA = ChatColor.AQUA.toString();
		YELLOW = ChatColor.YELLOW.toString();
		RED = ChatColor.RED.toString();
		GRAY = ChatColor.GRAY.toString();
		GOLD = ChatColor.GOLD.toString();
		GREEN = ChatColor.GREEN.toString();
		WHITE = ChatColor.WHITE.toString();
		BLACK = ChatColor.BLACK.toString();
		BOLD = ChatColor.BOLD.toString();
		ITALIC = ChatColor.ITALIC.toString();
		UNDER_LINE = ChatColor.UNDERLINE.toString();
		STRIKE_THROUGH = ChatColor.STRIKETHROUGH.toString();
		RESET = ChatColor.RESET.toString();
		MAGIC = ChatColor.MAGIC.toString();
		DARK_BLUE = ChatColor.DARK_BLUE.toString();
		DARK_AQUA = ChatColor.DARK_AQUA.toString();
		DARK_GRAY = ChatColor.DARK_GRAY.toString();
		DARK_GREEN = ChatColor.DARK_GREEN.toString();
		DARK_PURPLE = ChatColor.DARK_PURPLE.toString();
		DARK_RED = ChatColor.DARK_RED.toString();
		PINK = ChatColor.LIGHT_PURPLE.toString();
		MENU_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH.toString() + "------------------------";
		CHAT_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH.toString() + "------------------------------------------------";
		SB_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH.toString() + "----------------------";

		UNICODE_VERTICAL_BAR = CC.GRAY + StringEscapeUtils.unescapeJava("\u2503");
		UNICODE_CAUTION = StringEscapeUtils.unescapeJava("\u26a0");
		UNICODE_ARROW_LEFT = StringEscapeUtils.unescapeJava("\u25C0");
		UNICODE_ARROW_RIGHT = StringEscapeUtils.unescapeJava("\u25B6");
		UNICODE_ARROWS_LEFT = StringEscapeUtils.unescapeJava("\u00AB");
		UNICODE_ARROWS_RIGHT = StringEscapeUtils.unescapeJava("\u00BB");
		UNICODE_HEART = StringEscapeUtils.unescapeJava("\u2764");
	}

	public static Set<String> getColorNames() {
		return MAP.keySet();
	}

	public static ChatColor getColorFromName(String name) {
		if (MAP.containsKey(name.trim().toLowerCase())) {
			return MAP.get(name.trim().toLowerCase());
		}

		ChatColor color;

		try {
			color = ChatColor.valueOf(name.toUpperCase().replace(" ", "_"));
		} catch (Exception e) {
			return null;
		}

		return color;
	}

	public static String translate(String in) {
		return ChatColor.translateAlternateColorCodes('&', in);
	}

	public static List<String> translate(List<String> lines) {
		List<String> toReturn = new ArrayList<>();

		for (String line : lines) {
			toReturn.add(ChatColor.translateAlternateColorCodes('&', line));
		}

		return toReturn;
	}

	public static List<String> translate(String[] lines) {
		List<String> toReturn = new ArrayList<>();

		for (String line : lines) {
			if (line != null) {
				toReturn.add(ChatColor.translateAlternateColorCodes('&', line));
			}
		}

		return toReturn;
	}

	public static ChatColor toChatColor(Material material){
		switch (material){
			case WHITE_WOOL:{
				return ChatColor.WHITE;
			}
			case BLACK_WOOL:{
				return ChatColor.BLACK;
			}
			case BLUE_WOOL:{
				return ChatColor.DARK_BLUE;
			}
			case CYAN_WOOL:{
				return ChatColor.DARK_AQUA;
			}
			case GRAY_WOOL:{
				return ChatColor.DARK_GRAY;
			}
			case GREEN_WOOL:{
				return ChatColor.DARK_GREEN;
			}
			case LIGHT_BLUE_WOOL:{
				return ChatColor.BLUE;
			}
			case LIGHT_GRAY_WOOL:{
				return ChatColor.GRAY;
			}
			case LIME_WOOL:{
				return ChatColor.GREEN;
			}
			case MAGENTA_WOOL:{
				return ChatColor.LIGHT_PURPLE;
			}
			case ORANGE_WOOL:{
				return ChatColor.GOLD;
			}
			case PURPLE_WOOL:{
				return ChatColor.DARK_PURPLE;
			}
			case RED_WOOL:{
				return ChatColor.RED;
			}
			case YELLOW_WOOL:{
				return ChatColor.YELLOW;
			}
			case BROWN_WOOL:
			default: return ChatColor.WHITE;
		}
	}
}
