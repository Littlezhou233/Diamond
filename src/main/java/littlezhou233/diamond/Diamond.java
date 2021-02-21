package littlezhou233.diamond;

import littlezhou233.diamond.Utils.GameUtils;
import littlezhou233.diamond.Utils.MySQLUtil;
import littlezhou233.diamond.Utils.PAPI;
import littlezhou233.diamond.commands.MainCommand;
import littlezhou233.diamond.commands.VoteCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.util.logging.Level;

public final class Diamond extends JavaPlugin {
    public static void reload(CommandSender sender) {
        getInstance().reloadConfig();
        reloadData();
        reloadGameList();
        int game = 0;
        for (String games : getInstance().getConfig().getConfigurationSection("games").getKeys(false)) {
            game++;
        }
        sender.sendMessage("§e加载了 " + game + " 个游戏！");
        sender.sendMessage("§a配文重载完成！");
    }
    public static Diamond instance;
    public static File config;
    public static File data;
    public static File games;
    public static FileConfiguration dataconfig;
    public static FileConfiguration game;
    public static FileConfiguration getData() { return dataconfig;}
    public static FileConfiguration getGameList() { return game;}
    public static void reloadData() {
        try {
            dataconfig.save(data);
            dataconfig.load(data);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    public static void reloadGameList() {
        try {
            game.save(games);
            game.load(games);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    public static void saveData() {
        try {
            dataconfig.save(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Diamond getInstance() { return instance;}
    @Override
    public void onEnable() {
        // Plugin startup logic
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            instance = this;
            MySQLUtil.trymysql();
            config = new File(getDataFolder(), "config.yml");
            data = new File(getDataFolder(), "data.yml");
            games = new File(getDataFolder(), "games.yml");
            if (!getDataFolder().exists()) {
                getDataFolder().mkdir();
            }
            if (!config.exists()) {
                try {
                    config.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dataconfig = YamlConfiguration.loadConfiguration(data);
                getConfig().addDefault("games.default.displayname", "默认");
                getConfig().addDefault("MySQL.host", "localhost");
                getConfig().addDefault("MySQL.port", 3306);
                getConfig().addDefault("MySQL.user", "root");
                getConfig().addDefault("MySQL.password", 123456);
                getConfig().addDefault("MySQL.database", "diamond");
                getConfig().options().copyDefaults(true);
                saveConfig();
            }
            if (!data.exists()) {
                saveResource("data.yml", false);
                dataconfig = YamlConfiguration.loadConfiguration(data);
            } else {
                dataconfig = YamlConfiguration.loadConfiguration(data);
            }
            if (!games.exists()) {
                saveResource("games.yml", false);
                game = YamlConfiguration.loadConfiguration(games);
            } else {
                game = YamlConfiguration.loadConfiguration(games);
            }
            getCommand("vote").setExecutor(new VoteCommand());
            getCommand("ptladdon").setExecutor(new MainCommand());
            getLogger().info("Enabled PTLAddon version 1.0-SHAPSHOT by Littlezhou233.");
            new PAPI().register();
            if (new PAPI().register()) {
                getLogger().info("§aSuccessfully hooked to PlaceholderAPI");
            }
        } else {
            getLogger().log(Level.WARNING, "Can't find PAPI, disabling...");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
