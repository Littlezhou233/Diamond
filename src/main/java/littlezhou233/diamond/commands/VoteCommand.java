package littlezhou233.diamond.commands;

import littlezhou233.diamond.Diamond;
import littlezhou233.diamond.PlayerManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class VoteCommand implements CommandExecutor {
    Diamond main = Diamond.getInstance();
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (args.length == 2) {
            ConfigurationSection cs = main.getConfig().getConfigurationSection("games");
            if (cs.contains(args[0])) {
                int pl = Integer.parseInt(args[1]);
                int player = Diamond.getData().getInt("data." + p.getName() + ".score");
                boolean enabled = Diamond.getData().getBoolean("games." + args[0] + ".enabled");
                if (enabled) {
                    if (player >= 1) {
                        String name = main.getConfig().getString("games." + args[0] + ".displayname");
                        PlayerManager.removeScore(p.getName(), pl);
                        PlayerManager.giveGameScore(args[0], pl);
                        p.sendMessage("§6你给游戏 §a§l" + name.replace("&", "§") + " §6投上了" + pl + "颗人气钻石！");
                    } else {
                        p.sendMessage("§c你没有足够的票数！");
                    }
                    return true;
                } else {
                    p.sendMessage("§c该游戏已暂停投票！");
                }
            }
        }
        return false;
    }
}
