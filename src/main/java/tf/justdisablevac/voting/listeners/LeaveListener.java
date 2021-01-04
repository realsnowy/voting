package tf.justdisablevac.voting.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import tf.justdisablevac.voting.VoteHandler;

public class LeaveListener implements Listener {

    private final VoteHandler dayHandler;
    private final VoteHandler sunHandler;

    public LeaveListener(VoteHandler dayHandler, VoteHandler sunHandler) {
        this.dayHandler = dayHandler;
        this.sunHandler = sunHandler;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        dayHandler.removeVote(player);
        dayHandler.check();

        sunHandler.removeVote(player);
        sunHandler.check();
    }

}
