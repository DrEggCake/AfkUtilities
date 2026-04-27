package me.dreggcake.afkUtilities.hooks;

import me.dreggcake.afkUtilities.config.AfkConfigYaml;
import org.bukkit.entity.Player;
import org.mineacademy.fo.model.HookManager;

import java.util.List;

public class WorldGuardHook {

    // for checking if region exists, or if the player is in the region

    public static boolean regionExists(String name) {
        return HookManager.getRegion(name) != null;
    }

    public static boolean isInRegion(Player player) {
        String regionName = AfkConfigYaml.getAfkConfigYaml().getRgName();

        if (regionName == null || regionName.isEmpty())
            return false;

        List<String> regionsAtLocation = HookManager.getRegions(player.getLocation());

        return regionsAtLocation.contains(regionName);
    }
}
