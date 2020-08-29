package tf.justdisablevac.voting.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tf.justdisablevac.voting.CooldownManager;
import tf.justdisablevac.voting.backbone;
import tf.justdisablevac.voting.main;

public class day implements CommandExecutor {

    private final CooldownManager cooldownManager = new CooldownManager();
    private final backbone backbone = new backbone();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            String playerName = player.getName();
            World world = Bukkit.getWorld("world");

            if(world.getTime() < 12542) {
                player.sendMessage("§cWait until night!");

            } else if(world.getTime() >= 12542) {
                if(cooldownManager.getCooldownDay(playerName)) {
                    player.sendMessage("§cYou've already voted!");

                } else {
                    backbone.decreaseRemainingPlayersDay();
                    if(backbone.getRemainingPlayersDay().equals(0)) {
                        world.setTime(1000);
                        Bukkit.broadcastMessage("§aSay hello to the new day on §2world");

                        backbone.resetOnlinePlayers();
                        backbone.resetRemainingPlayersDay();
                        cooldownManager.resetCooldowns();

                    } else {
                        Bukkit.broadcastMessage("§aNew vote for day on §2world, " + backbone.getRemainingPlayersDay() + " more needed! §aVote with /day");
                        cooldownManager.setCooldownDay(playerName);
                    }
                }
            }
        }

        return true;

    }
}
