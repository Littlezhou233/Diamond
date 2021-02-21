package littlezhou233.diamond;

import org.bukkit.entity.Player;

public class PlayerManager {
    public Player p;
    public PlayerManager(Player p) {
        p = p;
    }
    public static void giveScore(String name, int score) {
        Diamond.getData().set("data." + name + ".score", Diamond.getData().getInt("data." + name + ".score") + score);
        Diamond.reloadData();
    }
    public static void removeScore(String name, int score) {
        Diamond.getData().set("data." + name + ".score", Diamond.getData().getInt("data." + name + ".score") - score);
        Diamond.reloadData();
    }
    public static void giveGameScore(String name, int score) {
        Diamond.getData().set("games.totalscore", Diamond.getData().getInt("games.totalscore") + score);
        Diamond.getData().set("games." + name + ".score", Diamond.getData().getInt("games." + name + ".score") + score);
        Diamond.reloadData();
    }
}
