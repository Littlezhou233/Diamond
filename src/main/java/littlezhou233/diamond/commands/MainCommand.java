package littlezhou233.diamond.commands;

import littlezhou233.diamond.Diamond;
import littlezhou233.diamond.Utils.GameUtils;
import littlezhou233.diamond.PlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class MainCommand implements CommandExecutor {
    Diamond main = Diamond.getInstance();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("diamond.admin")) {
            if (args.length == 0) {
                sender.sendMessage("§e/ptladdon givescore [玩家] [次数] 给予玩家可用次数 (区分大小写)");
                sender.sendMessage("§e/ptladdon info [游戏] 查询一个游戏的信息");
                sender.sendMessage("§e/ptladdon add [游戏] [显示名称] 添加一个游戏 (默认启用)");
                sender.sendMessage("§e/ptladdon enable [游戏] 启用一个游戏");
                sender.sendMessage("§e/ptladdon disable [游戏] 停用一个游戏 (分数清零并停用)");
                sender.sendMessage("§e/ptladdon reload 重载配文");
                return true;
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("givescore")) {
                    sender.sendMessage("§e/diamond givescore [玩家] [次数] 给予玩家可用次数");
                } else if (args[0].equalsIgnoreCase("reload")) {
                    Diamond.reload(sender);
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("info")) {
                    String gamePercent1 = GameUtils.GamePercent1(args[1]);
                    ConfigurationSection cs = main.getConfig().getConfigurationSection("games");
                    if (cs.contains(args[1])) {
                        sender.sendMessage("§a游戏 " + args[1] + " 的信息:");
                        sender.sendMessage("§a名字: " + main.getConfig().getString("games." + args[1] + ".displayname").replace("&", "§"));
                        sender.sendMessage("§a票数: " + Diamond.getData().getInt("games." + args[1] + ".score"));
                        sender.sendMessage("§a受喜爱程度: " + gamePercent1 + "% (" + GameUtils.voteDegree(Double.parseDouble(gamePercent1)));
                    } else {
                        sender.sendMessage("§c没有找到游戏 " + args[1] + " ！");
                    }
                    return true;
                }
                if (args[0].equalsIgnoreCase("disable")) {
                    List<String> games = Diamond.getGameList().getStringList("games");
                    if (games.contains(args[1])) {
                        if (Diamond.getData().getBoolean("games." + args[1] + ".enabled")) {
                            Diamond.getData().set("games." + args[1] + ".enabled", false);
                            Diamond.getData().set("games." + args[1] + ".score", 0);
                            Diamond.reloadGameList();
                            Diamond.reloadData();
                            sender.sendMessage("§a" + args[1] + "成功停用！");
                        } else {
                            sender.sendMessage("§c该游戏已停用！");
                        }
                    } else {
                        sender.sendMessage("§c未找到该游戏！");
                    }
                    return true;
                }
                if (args[0].equalsIgnoreCase("enable")) {
                    List<String> games = Diamond.getGameList().getStringList("games");
                    if (games.contains(args[1])) {
                        if (!Diamond.getData().getBoolean("games." + args[1] + ".enabled")) {
                            Diamond.getData().set("games." + args[1] + ".enabled", true);
                            Diamond.getData().set("games." + args[1] + ".score", 0);
                            Diamond.reloadGameList();
                            Diamond.reloadData();
                            sender.sendMessage("§a" + args[1] + "成功启用！");
                        } else {
                            sender.sendMessage("§c该游戏已启用！");
                        }
                    } else {
                        sender.sendMessage("§c未找到该游戏！");
                    }
                    return true;
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("givescore")) {
                    String target = args[1];
                    int score = Integer.parseInt(args[2]);
                    PlayerManager.giveScore(target, score);
                    sender.sendMessage("§a已给予 " + target + " " + score + "个钻石！");
                    return true;
                }
                if (args[0].equalsIgnoreCase("add")) {
                    List<String> games = Diamond.getGameList().getStringList("games");
                    if (games.contains(args[1])) {
                        sender.sendMessage("§c已经有同样的游戏名称被添加了！");
                    } else {
                        if (Diamond.getData().getString("games.totalscore") == null) {
                            Diamond.getData().set("games.totalscore", 1);
                            games.add(args[1]);
                            Diamond.getGameList().set("games", games);
                            Diamond.getData().set("games." + args[1] + ".score", 0);
                            Diamond.getData().set("games." + args[1] + ".enabled", true);
                            main.getConfig().set("games." + args[1] + ".displayname", args[2]);
                            Diamond.reloadData();
                            Diamond.reloadGameList();
                            main.saveConfig();
                            main.reloadConfig();
                            sender.sendMessage("§a成功将 " + args[1] + " 添加到游戏实验室!");
                        } else {
                            games.add(args[1]);
                            Diamond.getGameList().set("games", games);
                            Diamond.getData().set("games." + args[1] + ".score", 0);
                            Diamond.getData().set("games." + args[1] + ".enabled", true);
                            main.getConfig().set("games." + args[1] + ".displayname", args[2]);
                            Diamond.reloadData();
                            Diamond.reloadGameList();
                            main.saveConfig();
                            main.reloadConfig();
                            sender.sendMessage("§a成功将 " + args[1] + " 添加到游戏实验室!");
                        }
                        return true;
                    }
                }
            } else {
                sender.sendMessage("§c你没有使用此命令的权限！");
                return true;
            }
        }
        return false;
    }
}
