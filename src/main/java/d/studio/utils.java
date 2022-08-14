package d.studio;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class utils {
    public static String chat( String s ){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static void message(Player p, String s){
        p.sendMessage(chat(s));
    }

    public static void console(CommandSender c, String s){
        c.sendMessage(chat(s));
    }

    public static void broadcast(String s){
        Bukkit.broadcastMessage(chat(s));
    }
    


}




