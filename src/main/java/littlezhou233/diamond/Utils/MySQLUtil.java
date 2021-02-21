package littlezhou233.diamond.Utils;

import littlezhou233.diamond.Diamond;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.*;

public class MySQLUtil {
    public static Connection ct;
    public static void trymysql() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Bukkit.getConsoleSender().sendMessage("[PTLAddon] 数据库驱动加载完成.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String ip = Diamond.getInstance().getConfig().getString("MySQL.host");
        String user = Diamond.getInstance().getConfig().getString("MySQL.user");
        String pass = Diamond.getInstance().getConfig().getString("MySQL.password");
        String port = Diamond.getInstance().getConfig().getString("MySQL.port");
        String db = Diamond.getInstance().getConfig().getString("MySQL.database");

        try {
            ct= DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+db, user, pass);
            Bukkit.getConsoleSender().sendMessage("[PTlAddon] 数据库已连接.");
            ct.prepareStatement("create table if not exists `player_data` (`uuid` varchar(40) not null , `scores` varchar(40), primary key (`uuid`))").execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void addScores(Player p, int score) {
        try {
            PreparedStatement ps = ct.prepareStatement("insert into `player_data` values (?, ?)");
            ResultSet rs = ct.prepareStatement("select `player_data` from `player_data` where `uuid`=?").executeQuery();
            ps.setString(1, p.getUniqueId().toString());
            ps.setInt(2, rs.getInt("scores") + score);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
