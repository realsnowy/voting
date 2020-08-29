package tf.justdisablevac.voting.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import tf.justdisablevac.voting.CooldownManager;
import tf.justdisablevac.voting.backbone;
import tf.justdisablevac.voting.main;

public class onLeave implements Listener {

    private final CooldownManager cooldownManager = new CooldownManager();
    private final backbone backbone = new backbone();

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        String playerName = event.getPlayer().getName();

        if(cooldownManager.getCooldownDay(playerName)) {
            backbone.decreaseOnlinePlayers();
            backbone.decreaseRemainingPlayersSun();

        } else if(cooldownManager.getCooldownSun(playerName)) {
            backbone.decreaseOnlinePlayers();
            backbone.decreaseRemainingPlayersDay();

        } else {
            backbone.decreaseOnlinePlayers();
            backbone.decreaseRemainingPlayersDay();
            backbone.decreaseRemainingPlayersSun();
        }


        if(main.plugin.config.get("day.remainingPlayers").equals(0)) {
            Bukkit.getWorld("world").setTime(1000);
            Bukkit.broadcastMessage("§aSay hello to the new day on §2world");

            backbone.resetOnlinePlayers();
            backbone.resetRemainingPlayersSun();
            cooldownManager.resetCooldowns();
        }

        if(main.plugin.config.get("sun.remainingPlayers").equals(0)) {
            Bukkit.getWorld("world").setStorm(false);
            Bukkit.broadcastMessage("§aSay hello to the sun on §2world");

            backbone.resetOnlinePlayers();
            backbone.resetRemainingPlayersSun();
            cooldownManager.resetCooldowns();
        }
    }
}
