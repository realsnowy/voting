package tf.justdisablevac.voting;

import org.bukkit.plugin.java.JavaPlugin;
import tf.justdisablevac.voting.commands.VoteCommand;
import tf.justdisablevac.voting.listeners.LeaveListener;

public final class Voting extends JavaPlugin {

    @Override
    public void onEnable() {
        VoteHandler dayHandler = new VoteHandler(getConfig(), this,
                (world) -> world.setTime(1000), (world) -> world.getTime() >= 12542, "day");
        VoteHandler sunHandler = new VoteHandler(getConfig(), this,
                (world) -> world.setStorm(false), (world) -> world.hasStorm(), "sun");

        getCommand("day").setExecutor(new VoteCommand(dayHandler));
        getCommand("sun").setExecutor(new VoteCommand(sunHandler));

        getServer().getPluginManager().registerEvents(new LeaveListener(dayHandler, sunHandler), this);

    }
}
