package d.studio.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import d.studio.main;
import d.studio.utils;

public class helpop implements CommandExecutor{
	public helpop(main plugin) {
		plugin.getCommand("helpop").setExecutor(this);
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player p = (Player) sender; 
		
		String message = "";


		for (int i = 0; i < args.length; i++) {
			message = message + args[i] + " ";
			
		}
		if(args == null|| message.length() == 0) {

            utils.message(p, "&cUso incorrecto del comando. \n  &8* &bFormato: /helpop <motivo>");
               
        } else if(args[0].length() >= 1) {

			for(Player player : Bukkit.getOnlinePlayers()) {
				if(player.hasPermission("ktl.staff")) {
					utils.message(player, "&3&l" + sender.getName() + " &bha solicitado ayuda.\n &8* &bMotivo: &7" + message);
				} else {
					utils.message(p, "&bSolicitud enviada al staff.");
				}
			}

		}
		return false;
		
		
	}
	
	
}
	
