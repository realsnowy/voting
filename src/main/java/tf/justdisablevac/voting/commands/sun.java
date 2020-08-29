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

public class sun implements CommandExecutor {

    private final CooldownManager cooldownManager = new CooldownManager();
    private final backbone backbone = new backbone();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            String playerName = player.getName();
            World world = Bukkit.getWorld("world");

            if(!world.hasStorm()) {
                player.sendMessage("§cIt's already sunny!");

            } else {
                if(cooldownManager.getCooldownSun(playerName)) {
                    player.sendMessage("§cYou've already voted!");

                } else {
                    backbone.decreaseRemainingPlayersSun();
                    if(backbone.getRemainingPlayersSun().equals(0)) {
                        world.setStorm(false);
                        Bukkit.broadcastMessage("§aSay hello to the sun on §2world");

                        backbone.resetOnlinePlayers();
                        backbone.resetRemainingPlayersSun();
                        cooldownManager.resetCooldowns();

                    } else {
                        Bukkit.broadcastMessage("§aNew vote for sun on §2world, " + backbone.getRemainingPlayersSun() + " more needed! §aVote with /sun");
                        cooldownManager.setCooldownSun(playerName);
                    }
                }
            }
        }

        return true;

    }
}
