package d.studio;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import d.studio.commands.helpop;
import d.studio.commands.report;
import d.studio.events.autopickup;

public class main extends JavaPlugin
{

  
  @Override
  public void onEnable()
  {
    
    new report(this);
    new helpop(this);
    
    //eventos
    PluginManager pl = getServer().getPluginManager();

		pl.registerEvents(new autopickup(), this);

    
  }
  
  

}
