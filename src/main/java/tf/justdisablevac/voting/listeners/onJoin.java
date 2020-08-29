package tf.justdisablevac.voting.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tf.justdisablevac.voting.CooldownManager;
import tf.justdisablevac.voting.backbone;

public class onJoin implements Listener {

    private final CooldownManager cooldownManager = new CooldownManager();
    private final backbone backbone = new backbone();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();

        if(cooldownManager.getCooldownDay(playerName)) {
            backbone.increaseOnlinePlayers();
            backbone.increaseRemainingPlayersSun();

        } else if(cooldownManager.getCooldownSun(playerName)) {
            backbone.increaseOnlinePlayers();
            backbone.increaseRemainingPlayersDay();

        } else {
            backbone.increaseOnlinePlayers();
            backbone.increaseRemainingPlayersDay();
            backbone.increaseRemainingPlayersSun();
        }
    }
}
