package littlezhou233.diamond;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if ((e.getPlayer().hasPermission("hy.diamond") && (!e.getPlayer().hasPermission("hy.diamond.done")))) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "luckperms user " + e.getPlayer().getName() + " permission set hy.diamond.done true");
            PlayerManager.giveScore(e.getPlayer().getName(), 100);
        }
    }
}