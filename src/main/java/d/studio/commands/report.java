package d.studio.commands;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import d.studio.main;
import d.studio.utils;
import d.studio.events.DiscordWebhook;
import java.awt.Color;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;

public class report implements CommandExecutor {
    public report(main plugin) {
        plugin.getCommand("report").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        String message = "";
        //String coords = "" + p.getLocation().getX() + ", " + p.getLocation().getY() + ", " + p.getLocation().getZ();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        for (int i = 1; i < args.length; i++) {
            message = message + args[i] + " ";

        }


        TextComponent reporte = new TextComponent(ChatColor.AQUA+""+ChatColor.UNDERLINE+"Teletransportarse al jugador");
        reporte.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + args[0]));

        if (args == null || message.length() == 0) {

            utils.message(p, "&cUso incorrecto del comando. \n  &8* &bFormato: /report <usuario> <motivo>");

        } else if (args[0].length() >= 1) {

            // utils.broadcast("&3&l"+sender.getName()+" &bha reportado a &7"+args[0]+"\n
            // &8* &bMotivo: &7"+message, "ktl.staff");
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("ktl.staff")) {
                    utils.message(player, "&3&l" + sender.getName() + " &bha reportado a &7" + args[0] + "\n &8* &bMotivo: &7" + message);
                    player.spigot().sendMessage(reporte);

                } else {
                    utils.message(p, "&bReporte enviado al staff.");
                }
            }

            // discord webhook
            DiscordWebhook webhook = new DiscordWebhook(
                    "https://discord.com/api/webhooks/1008153652717957210/M4dUj6qav1RNefrqWTmPtD5A72f1YQLuBuqFDkxVvz0nSzNfBg63iVZ6Lb0oW8XyVTKI");
            webhook.setUsername("Sistema de reportes");
            webhook.setAvatarUrl(
                    "https://cdn.discordapp.com/icons/834063060045267026/420870d676f1c5f016235bcb1fa0dae6.png?size=4096");
            webhook.addEmbed(new DiscordWebhook.EmbedObject()
                    .setTitle("Nuevo Reporte")
                    .setColor(Color.CYAN)
                    .setThumbnail( "https://cdn.discordapp.com/icons/834063060045267026/420870d676f1c5f016235bcb1fa0dae6.png?size=4096")
                    .addField("Reportado", args[0], true)
                    .addField("Modalidad", "Survival", true)
                    .addField("Fecha", "" + f.format(LocalDateTime.now()), true)
                    .addField("Razon", message, false)
                    //.addField("Coordenadas", "" + coords, false)
                   
                    .setFooter("Sistema de reportes de Krataland | Reportante: " + p.getName(),
                            "https://cdn.discordapp.com/icons/834063060045267026/420870d676f1c5f016235bcb1fa0dae6.png?size=4096"));
            try {
                webhook.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return true;
    }
}
