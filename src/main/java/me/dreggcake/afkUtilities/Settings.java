package me.dreggcake.afkUtilities;

import org.mineacademy.fo.settings.SimpleSettings;

public final class Settings extends SimpleSettings {

    private static void init() {
        PLUGIN_PREFIX = getString("Prefix");
    }

    @Override
    protected int getConfigVersion() {
        return 1;
    }
}
