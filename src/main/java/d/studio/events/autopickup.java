package d.studio.events;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class autopickup implements Listener {

    ConsoleCommandSender console = Bukkit.getConsoleSender();

    @EventHandler
    public void onBlockBreak(org.bukkit.event.block.BlockBreakEvent e) {

        if (e.getPlayer().getGameMode() == org.bukkit.GameMode.SURVIVAL) {
            if (e.getPlayer().getInventory().firstEmpty() != -1) {
                e.getPlayer().getInventory().addItem(new ItemStack(e.getBlock().getType(),
                        1));
                e.getBlock().setType(org.bukkit.Material.AIR);
            } else {
                console.sendMessage(e.getPlayer().getName() + " se quedo sin espacio en el inventario.");
            }
        }

    }

}
