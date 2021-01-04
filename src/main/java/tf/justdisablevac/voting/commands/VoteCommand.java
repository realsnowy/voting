package tf.justdisablevac.voting.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tf.justdisablevac.voting.VoteHandler;

public class VoteCommand implements CommandExecutor {

    private final VoteHandler voteHandler;

    public VoteCommand(VoteHandler voteHandler) {
        this.voteHandler = voteHandler;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            voteHandler.vote(player);
        } else {
            sender.sendMessage("§cYou have to be a player to use this feature");
        }

        return true;

    }

}
