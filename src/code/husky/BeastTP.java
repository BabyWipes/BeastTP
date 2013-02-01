package code.husky;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BeastTP extends JavaPlugin
{
  private static final Logger log = Logger.getLogger("Minecraft");
  List<String> toc = new ArrayList<String>();
  List<String> fromc = new ArrayList<String>();

  public void onEnable() {
    log.info("[BeastTP] Enabled.");
  }

  public void onDisable() {
    log.info("[BeastTP] DISABLED!");
  }

  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    Player s = (Player)sender;
    if (commandLabel.equalsIgnoreCase("to")) {
      if (!canGoTo((Player)sender)) {
        sender.sendMessage(ChatColor.RED + "You are not allowed to use this BeastTP function!");
        return true;
      }

      for (Player p : getServer().getOnlinePlayers()) {
        if ((p.getName().toLowerCase().contains(args[0].toLowerCase())) || (p.getDisplayName().toLowerCase().contains(args[0].toLowerCase()))) {
          if (!this.toc.contains(p.getName())) {
            Teleport((Player)sender, p);
            sender.sendMessage(ChatColor.GREEN + "Successfully teleported to " + p.getDisplayName());
            teleportedMessage(p, (Player)sender, true);
          } else {
            s.sendMessage(ChatColor.RED + "You're not able to TP to " + p.getName() + " because they disabled their TP!");
          }
          return true;
        }
      }

      sender.sendMessage(ChatColor.RED + "Couldn't find target for " + args[0]);
      return true;
    }if (commandLabel.equalsIgnoreCase("from")) {
      if (!canFrom((Player)sender)) {
        sender.sendMessage(ChatColor.RED + "You are not allowed to use this BeastTP function!");
        return true;
      }

      for (Player p : getServer().getOnlinePlayers()) {
        if ((p.getName().toLowerCase().contains(args[0].toLowerCase())) || (p.getDisplayName().toLowerCase().contains(args[0].toLowerCase()))) {
          if (!this.fromc.contains(p.getName())) {
            Teleport(p, (Player)sender);
            sender.sendMessage(ChatColor.GREEN + "Successfully teleported " + p.getDisplayName() + " to you");
            teleportedMessage(p, (Player)sender, false);
          } else {
            s.sendMessage(ChatColor.RED + "You're not able to TP " + p.getName() + " to you because they disabled their TP!");
          }
          return true;
        }
      }

      sender.sendMessage(ChatColor.RED + "Couldn't find target for " + args[0]);
      return true;
    }if (commandLabel.equalsIgnoreCase("dfrom")) {
      this.fromc.add(s.getName());
      s.sendMessage(ChatColor.GREEN + "Disabled people TP'ing you to them!");
      return true;
    }if (commandLabel.equalsIgnoreCase("dto")) {
      this.toc.add(s.getName());
      s.sendMessage(ChatColor.GREEN + "Disabled people TP'ing to you!");
      return true;
    }if (commandLabel.equalsIgnoreCase("sto")) {
      if (!canGoTo((Player)sender)) {
        sender.sendMessage(ChatColor.RED + "You are not allowed to use this BeastTP function!");
        return true;
      }

      for (Player p : getServer().getOnlinePlayers()) {
        if ((p.getName().toLowerCase().contains(args[0].toLowerCase())) || (p.getDisplayName().toLowerCase().contains(args[0].toLowerCase()))) {
          if (!this.toc.contains(p.getName())) {
            Teleport((Player)sender, p);
            sender.sendMessage(ChatColor.GREEN + "Successfully teleported to " + p.getDisplayName());
          } else {
            s.sendMessage(ChatColor.RED + "You're not able to TP to " + p.getName() + " because they disabled their TP!");
          }
          return true;
        }
      }

      sender.sendMessage(ChatColor.RED + "Couldn't find target for " + args[0]);
      return true;
    }if (commandLabel.equalsIgnoreCase("sfrom")) {
      if (!canFrom((Player)sender)) {
        sender.sendMessage(ChatColor.RED + "You are not allowed to use this BeastTP function!");
        return true;
      }

      for (Player p : getServer().getOnlinePlayers()) {
        if ((p.getName().toLowerCase().contains(args[0].toLowerCase())) || (p.getDisplayName().toLowerCase().contains(args[0].toLowerCase()))) {
          if (!this.fromc.contains(p.getName())) {
            Teleport(p, (Player)sender);
            sender.sendMessage(ChatColor.GREEN + "Successfully teleported " + p.getDisplayName() + " to you");
          } else {
            s.sendMessage(ChatColor.RED + "You're not able to TP " + p.getName() + " to you because they disabled their TP!");
          }
          return true;
        }
      }

      sender.sendMessage(ChatColor.RED + "Couldn't find target for " + args[0]);
      return true;
    }
    return false;
  }

  public void teleportedMessage(Player target, Player relatedPlayer, boolean to) {
    if (to)
      target.sendMessage(ChatColor.GREEN + relatedPlayer.getDisplayName() + " teleported to you!");
    else
      target.sendMessage(ChatColor.GREEN + "You were teleported to " + relatedPlayer.getDisplayName());
  }

  public void Teleport(Player target, Player destination) {
    target.teleport(destination);
  }

  public boolean canGoTo(Player p)
  {
    return p.hasPermission("BeastTP.to");
  }

  public boolean canFrom(Player p)
  {
    return p.hasPermission("BeastTP.from");
  }
}