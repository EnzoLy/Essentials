package net.eternalproyect.essentials;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class EssentialsAPI {

    public static String getColorOfPlayer(Player player){
        return getColorPrefix(Essentials.getInstance().getVchat().getPlayerPrefix(player) == null ? "" : Essentials.getInstance().getVchat().getPlayerPrefix(player));
    }

    private static String getColorPrefix(String prefix){
        if(prefix.isEmpty()){
            return "";
        }
        char code = 'f';
        char magic = 'f';
        for(String string : prefix.split("&")){
            if((!string.isEmpty()) && (ChatColor.getByChar(string.toCharArray()[0]) != null)){
                if(!isMagic(string.toCharArray()[0])){
                    code = string.toCharArray()[0];
                }else{
                    magic = string.toCharArray()[0];
                }
            }
        }
        ChatColor color = ChatColor.getByChar(code);
        if(magic == 'f'){
            return color.toString();
        }
        ChatColor magicColor = ChatColor.getByChar(magic);
        return color.toString() + magicColor.toString();
    }

    private static boolean isMagic(char string){
        return string == 'o' || string == 'l' || string == 'k' || string == 'n' || string == 'm';
    }
}
