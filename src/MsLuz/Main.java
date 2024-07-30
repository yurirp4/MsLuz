package MsLuz;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

import mkremins.fanciful.FancyMessage;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		ConsoleCommandSender b = Bukkit.getConsoleSender();
		b.sendMessage("§8=-=-=-=-=-=-=-=-=-=-=-=-=-==-");
		b.sendMessage("§7Versao:7-a1.0");
		b.sendMessage("§7Autor: §9yurirp4");
		b.sendMessage("§bSkype: Filipe Silva amy");
		b.sendMessage("§7stats Do Plugin: §aAtivo");
		b.sendMessage("§8=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		if (!new File(getDataFolder(), "config.yml").exists()) {
			saveDefaultConfig();
		}
	}

	@Override
	public void onDisable() {
		ConsoleCommandSender b = Bukkit.getConsoleSender();
		b.sendMessage("§8=-=-=-=-=-=-=-=-=-=-=-=-=-==-");
		b.sendMessage("§7Versao:§a1.0");
		b.sendMessage("§7Autor: §9yurirp4");
		b.sendMessage("§bSkype: Filipe Silva amy");
		b.sendMessage("§7stats Do Plugin: §cDesativo");
		b.sendMessage("§8=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
	    if (!(sender instanceof Player))
	    {
	      sender.sendMessage("§cVoce nao e um jogador");
	      return true;
	    }
	    Player p = (Player)sender;
	    if (cmd.getName().equalsIgnoreCase("luz"))
	    {
	      if (!p.hasPermission("luz.usar"))
	      {
	        p.sendMessage("§cVocê Não tem permissão");
	        return true;
	      }
	      if (args.length == 0)
	      {
	    	  new FancyMessage("§eUtilize /luz")
	    	  .then(" §a§l[ON]")
	    	  .tooltip( "\n" + "§a* Clique para Ativar a Lanterna" + "\n")
	    	  .command("/Luz on")
	    	  .then(" §eou")
	    	  .then(" §c§l[OFF]")
	    	  .tooltip("\n" + " §c* Clique para Desativar a Lanterna" + "\n")
	    	  .command("/Luz off")
	    	  .then("§e")
	    	  .send ( p );
	        
	        return true;
	      }
	      if (args[0].equalsIgnoreCase("on"))
	      {
	        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 0));
	        ActionBarAPI.sendActionBar(p, (String) getConfig().getString("Luz_on").replace("&", "§"));
	        return true;
	      }
	      if (args[0].equalsIgnoreCase("off")) {
	        p.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);
	        ActionBarAPI.sendActionBar(p, (String) getConfig().getString("Luz_off").replace("&", "§"));
	      }
	    }
	    return false;
	  }
	}
