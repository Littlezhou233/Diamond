package littlezhou233.diamond.Utils;


import littlezhou233.diamond.Diamond;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import static littlezhou233.diamond.Utils.GameUtils.GamePercent1;
import static littlezhou233.diamond.Utils.GameUtils.voteDegree;

public class PAPI extends PlaceholderExpansion {
    ConfigurationSection cs = Diamond.getInstance().getConfig().getConfigurationSection("games");
    public String getIdentifier() {
        return "ptladdon";
    }

    public String getAuthor() {
        return "Littlezhou233";
    }

    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player p, String params) {
        if (params.equals("diamond_totalscore")) {
            int score = Diamond.getData().getInt("games.totalscore");
            String s = IntUtils.toTrisection(score);
            return s;
        }
        if (params.equals("diamond")) {
            String score = Diamond.getData().getString("data." + p.getName() + ".score");
            if (score == null) {
                return "0";
            } else {
                return score;
            }
        }
        for (String game : Diamond.getGameList().getStringList("games")) {
            if (params.equals("diamond_degree_" + game)) {
                String percent = GamePercent1(game);
                String degree = voteDegree(Double.parseDouble(percent));
                return degree;
            }
            if (params.equals("diamond_score_" + game)) {
                String score = Diamond.getData().getString("games." + game + ".score");
                return score;
            }
            if (params.equals("diamond_status_" + game)) {
                boolean tf = Diamond.getData().getBoolean("games." + game + ".enabled");
                String status = String.valueOf(tf).replace(String.valueOf(false), "§c已停用").replace(String.valueOf(true), "§a正在进行中");
                return status;
            }
            if (params.equals("diamond_displayname_" + game)) {
                String name = Diamond.getInstance().getConfig().getString("games." + game + ".displayname").replace("&", "§");
            }
            if (params.equals("diamond_progress_" + game)) {
                ProgressBarUtils progressBarUtils = new ProgressBarUtils(20, "▮");
                int totalScore = Diamond.getData().getInt("games.totalscore");
                int gameScore = Diamond.getData().getInt("games." + game + ".score");
                int location = gameScore * 20 / totalScore;
                progressBarUtils.setLocation(location);
                return progressBarUtils.toString();
            }
        }
        return null;
    }
}
