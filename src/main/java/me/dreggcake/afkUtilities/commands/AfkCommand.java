package me.dreggcake.afkUtilities.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Optional;
import me.dreggcake.afkUtilities.AfkUtilities;
import me.dreggcake.afkUtilities.config.AfkConfigYaml;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;

@CommandAlias("afk|afkarea")
public class AfkCommand extends BaseCommand {

    @Default
    public void onAfk(Player sender, @Optional String targetName) {
        if(!canTeleport()){
            Common.tell(sender,"&c Failed, please contact an administrator");
            return;
        }

        if (targetName == null) {
            sender.teleport(AfkConfigYaml.getAfkConfigYaml().getAfkLocation());
            Common.tell(sender, "You have been teleported to &d&lAfk Area");
            return;
        }
        if (!sender.hasPermission("afkutilities.player")) {
            Common.tell(sender, "&cYou don't have permission to run this command!");
            return;
        }
        Player target = Bukkit.getPlayerExact(targetName);
        if (target == null) {
            Common.tell(sender, "&cPlayer '" + targetName + "' not found!");
            return;
        }
        target.teleport(AfkConfigYaml.getAfkConfigYaml().getAfkLocation());
        Common.tell(sender, "&bYou have teleported &u" + target.getName() + "&r&b to the AFK area.");
        Common.tell(target, "You have been teleported to &d&lAfk Area &b by " + sender.getName());

    }


    private boolean canTeleport(){
        Location location = AfkConfigYaml.getAfkConfigYaml().getAfkLocation();
        return location.getX() != 0 && location.getY() != 0 && location.getZ() != 0 && !location.getWorld().getName().isEmpty();
    }


}
