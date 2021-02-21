package littlezhou233.diamond.Utils;

import littlezhou233.diamond.Diamond;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class GameUtils {
    public static String GamePercent(String game) {
        int gameScore = Diamond.getData().getInt("games." + game + ".score");
        int totalScore = Diamond.getData().getInt("games.totalscore");
        NumberFormat formatter = new DecimalFormat("00.0");
        double gs = gameScore * 1.0;
        double ts = totalScore * 1.0;
        Double score = gs / ts;
        String Percent = formatter.format(score);
        String gamePercent = String.valueOf(Double.parseDouble(Percent) * 100) + "%";
        return gamePercent;
    }

    public static String GamePercent1(String game) { // without "%"
        int gameScore = Diamond.getData().getInt("games." + game + ".score");
        int totalScore = Diamond.getData().getInt("games.totalscore");
        NumberFormat formatter = new DecimalFormat("00.0");
        double gs = gameScore * 1.0;
        double ts = totalScore * 1.0;
        Double score = gs / ts;
        String Percent = formatter.format(score);
        String gamePercent = String.valueOf(Double.parseDouble(Percent) * 100);
        return gamePercent;
    }

    public static String voteDegree(double gPercent) {

        if (gPercent <= 30.0) {
            return "§c非常冷门";
        }
        if (gPercent <= 60.0) {
            return "§e受人喜爱";
        }
        if (gPercent > 60.0) {
            return "§a非常火爆";
        }
        return null;
    }

}
