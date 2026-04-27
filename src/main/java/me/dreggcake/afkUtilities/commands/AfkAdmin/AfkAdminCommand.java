package me.dreggcake.afkUtilities.commands.AfkAdmin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import me.dreggcake.afkUtilities.AfkUtilities;
import me.dreggcake.afkUtilities.config.AfkConfigYaml;
import me.dreggcake.afkUtilities.hooks.WorldGuardHook;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;

@CommandAlias("afkutilities")
@CommandPermission("afkutilities.admin")
public class AfkAdminCommand extends BaseCommand {

    @Subcommand("setLocation")
    public void onAfkLocation(Player sender) {
        Location location = sender.getLocation();
        AfkConfigYaml.getAfkConfigYaml().setAfkLocation(location);
        Common.tell(sender,"&bUpdated Afk Area spawn location");
    }

    @Subcommand("setRegion")
    public void onRegion(Player sender, String regionName) {
        if (!WorldGuardHook.regionExists(regionName)) {
            Common.tell(sender, "&cUnknown region '" + regionName + "'");
            return;
        }
        AfkConfigYaml.getAfkConfigYaml().setRgName(regionName);
        Common.tell(sender, "&bUpdated afk region to " + regionName);

    }

}
