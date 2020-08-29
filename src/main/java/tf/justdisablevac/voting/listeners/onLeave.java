package tf.justdisablevac.voting.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import tf.justdisablevac.voting.CooldownManager;
import tf.justdisablevac.voting.main;

public class onLeave implements Listener {

    private final CooldownManager cooldownManager = new CooldownManager();

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        String playerName = event.getPlayer().getName();
        Integer onlinePlayers = main.plugin.config.getInt("onlinePlayers");

        Integer remainingPlayersDay = main.plugin.config.getInt("day.remainingPlayers");
        Integer remainingPlayersSun = main.plugin.config.getInt("sun.remainingPlayers");

        if(main.plugin.config.getBoolean("players.day." + playerName)) {
            main.plugin.config.set("onlinePlayers", onlinePlayers - 1);
            main.plugin.saveConfig();

        } else if(main.plugin.config.getBoolean("players.sun." + playerName)) {
            main.plugin.config.set("onlinePlayers", onlinePlayers - 1);
            main.plugin.saveConfig();

        } else {
            main.plugin.config.set("onlinePlayers", onlinePlayers - 1);

            main.plugin.config.set("day.remainingPlayers", remainingPlayersDay - 1);
            main.plugin.config.set("sun.remainingPlayers", remainingPlayersSun - 1);

            main.plugin.saveConfig();
        }


        if(main.plugin.config.get("day.remainingPlayers").equals(0)) {
            Bukkit.getWorld("world").setTime(1000);
            Bukkit.broadcastMessage("§aSay hello to the new day on §2world");

            for(Player players : Bukkit.getOnlinePlayers()) {
                cooldownManager.setCooldownDay(players.getName(), false);
            }

            main.plugin.config.set("onlinePlayers", onlinePlayers);
            main.plugin.config.set("day.remainingPlayers", onlinePlayers);

            main.plugin.config.set("players", null);

            main.plugin.saveConfig();
        }

        if(main.plugin.config.get("sun.remainingPlayers").equals(0)) {
            Bukkit.getWorld("world").setStorm(false);
            Bukkit.broadcastMessage("§aSay hello to the sun on §2world");

            for(Player players : Bukkit.getOnlinePlayers()) {
                cooldownManager.setCooldownSun(players.getName(), false);
            }

            main.plugin.config.set("onlinePlayers", onlinePlayers);
            main.plugin.config.set("sun.remainingPlayers", onlinePlayers);

            main.plugin.config.set("players", null);

            main.plugin.saveConfig();

        }
    }
}
