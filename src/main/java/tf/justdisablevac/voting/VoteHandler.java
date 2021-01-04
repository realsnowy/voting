package tf.justdisablevac.voting;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class VoteHandler {

    private List<Player> hasVoted = new ArrayList<>();

    private World world;
    private String VOTEMESSAGE;
    private String SUCCESSMESSAGE;
    private String ALREADYVOTEDMESSAGE;
    private String WAITUNTILNIGHTMESSAGE;
    private String CONFIG_WORLD;
    private final Voting plugin;

    private final Consumer<World> onSuccess;
    private final Predicate<World> ifPossible;

    public VoteHandler(FileConfiguration config, Voting plugin, Consumer<World> onSuccess, Predicate<World> ifPossible, String prefix) {
        world = Bukkit.getServer().getWorld(config.getString("world"));
        VOTEMESSAGE = config.getString(prefix + "VoteMessage");
        SUCCESSMESSAGE = config.getString(prefix + "SuccessMessage");
        ALREADYVOTEDMESSAGE = config.getString("alreadyVotedMessage");
        WAITUNTILNIGHTMESSAGE = config.getString(prefix + "NotReadyMessage");
        this.onSuccess = onSuccess;
        this.ifPossible = ifPossible;
        CONFIG_WORLD = world.getName();
        this.plugin = plugin;
    }

    public boolean hasVoted(Player player) {
        return hasVoted.contains(player);
    }

    public void vote(Player player) {

        if(ifPossible.test(world)) {

            if(!hasVoted(player)) {
                hasVoted.add(player);
                check();

            } else {
                player.sendMessage(ALREADYVOTEDMESSAGE);
            }
        } else {
            player.sendMessage(WAITUNTILNIGHTMESSAGE);
        }
    }

    public void removeVote(Player player) {
        hasVoted.remove(player);
    }

    public void check() {
        Bukkit.getScheduler().runTask(plugin, () -> { //fuck bukkit for me having to run it 1 tick later, i hope you all die an never code ever again you fucking pieces of shit. YOU TOOK MY WIFE AND KIDS AND NOW YOU DO THIS HUH? YOU WANT ME TO LOSE MY JOB U FUCKERS????? die.
            int playerCount = Bukkit.getOnlinePlayers().size();

            if(hasVoted.containsAll(Bukkit.getOnlinePlayers())) {

                Bukkit.broadcastMessage(String.format(SUCCESSMESSAGE, CONFIG_WORLD));
                onSuccess.accept(world);

                for(Player players : Bukkit.getOnlinePlayers()) {
                    removeVote(players);
                }

            } else {
                Bukkit.broadcastMessage(String.format(VOTEMESSAGE, CONFIG_WORLD, (playerCount - hasVoted.size())));
            }
        });
    }
}
