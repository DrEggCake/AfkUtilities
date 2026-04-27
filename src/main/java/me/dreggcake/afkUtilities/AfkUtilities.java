package me.dreggcake.afkUtilities;

import co.aikar.commands.PaperCommandManager;
import me.dreggcake.afkUtilities.commands.AfkAdmin.AfkAdminCommand;
import me.dreggcake.afkUtilities.commands.AfkCommand;
import me.dreggcake.afkUtilities.commands.AfkReloadCommand;
import me.dreggcake.afkUtilities.commands.AfkUtilitiesCommand;
import me.dreggcake.afkUtilities.config.AfkConfigYaml;
import me.dreggcake.afkUtilities.listeners.AfkTimerListener;
import org.bukkit.Bukkit;
import org.mineacademy.fo.plugin.SimplePlugin;

public final class AfkUtilities extends SimplePlugin {
    private PaperCommandManager commandManager;

    @Override
    protected void onPluginStart() {
        // loading post world the commands and the config whcih saves "world" key
        Bukkit.getScheduler().runTask(this, () -> {
            registerCommands();
            getServer().getPluginManager().registerEvents(new AfkTimerListener(), this);
            AfkConfigYaml.load();
        });

    }

    private void registerCommands() {
        commandManager = new PaperCommandManager(this);


        // optional: enable unstable api to use help
        commandManager.enableUnstableAPI("help");


        // 7: Register your commands - This first command demonstrates adding an exception handler to that command
        commandManager.registerCommand(new AfkUtilitiesCommand());
        commandManager.registerCommand(new AfkReloadCommand());
        commandManager.registerCommand(new AfkCommand());
        commandManager.registerCommand(new AfkAdminCommand());

        // 9: Register default exception handler for any command that doesn't supply its own
        commandManager.setDefaultExceptionHandler((command, registeredCommand, sender, args, t) -> {
            getLogger().warning("Error occurred while executing command " + command.getName());
            return false; // mark as unhandeled, sender will see default message
        });


    }
}


