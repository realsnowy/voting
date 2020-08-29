package tf.justdisablevac.voting.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tf.justdisablevac.voting.main;

public class onJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();
        Integer onlinePlayers = main.plugin.config.getInt("onlinePlayers");

        Integer remainingPlayersDay = main.plugin.config.getInt("day.remainingPlayers");
        Integer remainingPlayersSun = main.plugin.config.getInt("sun.remainingPlayers");

        if(main.plugin.config.getBoolean("players.day." + playerName)) {
            main.plugin.config.set("onlinePlayers", onlinePlayers + 1);
            main.plugin.saveConfig();

        } else if(main.plugin.config.getBoolean("players.sun." + playerName)) {
            main.plugin.config.set("onlinePlayers", onlinePlayers + 1);
            main.plugin.saveConfig();

        } else {
            main.plugin.config.set("onlinePlayers", onlinePlayers + 1);

            main.plugin.config.set("day.remainingPlayers", remainingPlayersDay + 1);
            main.plugin.config.set("sun.remainingPlayers", remainingPlayersSun + 1);

            main.plugin.saveConfig();
        }
    }
}
