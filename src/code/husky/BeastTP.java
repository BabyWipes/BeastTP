package code.husky;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BeastTP extends JavaPlugin
{
  BeastTP plugin;
  Logger log = Logger.getLogger("Minecraft");

  public void onEnable() {
    this.log.info("BeastTP has been Enabled");
  }

  public void onDisable() {
    this.log.info("BeastTP has been Diabled");
  }

  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    Player p = (Player)sender;
    if (cmd.getName().equalsIgnoreCase("to")) {
      if ((p.hasPermission("BeastTP.to")) || (p.hasPermission("BeastTP.*"))) {
        if (args.length == 0) {
          p.sendMessage(ChatColor.RED + "Usage: /to [Player]");
        } else if (args.length == 1) {
          Player checkee = getServer().getPlayerExact(args[0]);
          if (checkToggle(checkee) == "No") {
            p.sendMessage(ChatColor.RED + "This player has teleportation disabled!");
          } else {
            Player top = p.getServer().getPlayer(args[0]);
            p.teleport(top);
            p.sendMessage(ChatColor.GREEN + "Teleported to " + ChatColor.DARK_RED + top.getName() + ChatColor.GREEN + "!");
            top.sendMessage(ChatColor.DARK_RED + p.getName() + ChatColor.GREEN + " teleported to you!");
          }
        }
      }
      else NoPerm(p);
    }
    else if (cmd.getName().equalsIgnoreCase("tos")) {
      if ((p.hasPermission("BeastTP.tos")) || (p.hasPermission("BeastTP.*"))) {
        if (args.length == 0) {
          p.sendMessage(ChatColor.RED + "Usage: /tos [Player]");
        } else if (args.length == 1) {
          Player checkee = getServer().getPlayerExact(args[0]);
          if (checkToggle(checkee) == "No") {
            p.sendMessage(ChatColor.RED + "This player has teleportation disabled!");
          } else {
            Player top = p.getServer().getPlayer(args[0]);
            p.teleport(top);
            p.sendMessage(ChatColor.GREEN + "Teleported to " + ChatColor.DARK_RED + top.getName() + ChatColor.GREEN + "!");
          }
        }
      }
      else NoPerm(p);
    }
    else if (cmd.getName().equalsIgnoreCase("from")) {
      if ((p.hasPermission("BeastTP.from")) || (p.hasPermission("BeastTP.*"))) {
        if (args.length == 0) {
          p.sendMessage(ChatColor.RED + "Usage: /from [Player]");
        } else if (args.length == 1) {
          Player checkee = getServer().getPlayerExact(args[0]);
          if (checkToggle(checkee) == "No") {
            p.sendMessage(ChatColor.RED + "This player has teleportation disabled!");
          } else {
            Player fromp = p.getServer().getPlayer(args[0]);
            fromp.teleport(p);
            p.sendMessage(ChatColor.GREEN + "Teleported " + ChatColor.DARK_RED + fromp.getName() + ChatColor.GREEN + "to you!");
            fromp.sendMessage(ChatColor.DARK_RED + p.getName() + ChatColor.GREEN + " teleported you to them!");
          }
        }
      }
      else NoPerm(p);
    }
    else if (cmd.getName().equalsIgnoreCase("froms")) {
      if ((p.hasPermission("BeastTP.froms")) || (p.hasPermission("BeastTP.*"))) {
        if (args.length == 0) {
          p.sendMessage(ChatColor.RED + "Usage: /froms [Player]");
        } else if (args.length == 1) {
          Player checkee = getServer().getPlayerExact(args[0]);
          if (checkToggle(checkee) == "No") {
            p.sendMessage(ChatColor.RED + "This player has teleportation disabled!");
          } else {
            Player fromp = p.getServer().getPlayer(args[0]);
            fromp.teleport(p);
            p.sendMessage(ChatColor.GREEN + "Teleported " + ChatColor.DARK_RED + fromp.getName() + ChatColor.GREEN + "to you!");
          }
        }
      }
      else NoPerm(p);
    }
    else if (cmd.getName().equalsIgnoreCase("tpoff")) {
      if ((p.hasPermission("BeastTP.tptoggle")) || (p.hasPermission("BeastTP.*"))) {
        String onoroff = "No";
        tpToggle(p, onoroff);
      } else {
        NoPerm(p);
      }
      p.sendMessage(ChatColor.GREEN + "Disabled players from teleporting to and from you!");
    } else if (cmd.getName().equalsIgnoreCase("tpon")) {
      if ((p.hasPermission("BeastTP.tptoggle")) || (p.hasPermission("BeastTP.*"))) {
        String onoroff = "Yes";
        tpToggle(p, onoroff);
        p.sendMessage(ChatColor.GREEN + "Enabled players to teleport to and from you!");
      } else {
        NoPerm(p);
      }
    }
    return false;
  }

  private void NoPerm(Player p) {
    p.sendMessage(ChatColor.RED + "You don't have permission to use this!");
  }

  public void tpToggle(Player p, String onoroff) {
    YamlConfiguration config = YamlConfiguration.loadConfiguration(new File("plugins/BeastTP/tpAllow.yml"));
    if (onoroff == "Yes") {
      config.set(p.getName() + ".tpAllow", "Yes");
      try {
        config.save("plugins/BeastTP/tpAllow.yml");
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else if (onoroff == "No") {
      config.set(p.getName() + ".tpAllow", "No");
      try {
        config.save("plugins/BeastTP/DisableTP.yml");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private String checkToggle(Player checkee) {
    YamlConfiguration config = YamlConfiguration.loadConfiguration(new File("plugins/BeastTP/tpAllow.yml"));
    return config.getString(checkee.getName() + ".tpAllow");
  }
}