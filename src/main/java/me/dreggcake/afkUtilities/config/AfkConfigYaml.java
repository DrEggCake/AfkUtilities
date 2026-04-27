package me.dreggcake.afkUtilities.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.mineacademy.fo.settings.YamlConfig;

public class AfkConfigYaml extends YamlConfig {
    private static AfkConfigYaml instance;

    private World world;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private long defaultAfkTime;
    private String rgName;

    public static void load() {
        instance = new AfkConfigYaml();
        instance.loadConfiguration(null, "config.yml");
    }

    public static AfkConfigYaml getAfkConfigYaml() {
        return instance;
    }

    public static void reloadConfig() {
        if (instance != null) {
            instance.reload();
        }
    }
    @Override
    protected void onLoad() {
        // if none of the files are set in the generated config, add some default values
        if(!isSet("afk.world")){
            setPathPrefix("afk");
            set("world", "world");
            set("x", 0);
            set("y", 64);
            set("z", 0);
            set("yaw", 0);
            set("pitch", 0);
            setPathPrefix(null);
            set("default", 60);
            set("worldguard.regionId", "afkRegion");
            save();
        }

        setPathPrefix("afk");
        world = Bukkit.getWorld(getString("world"));
        x = getDouble("x");
        y = getDouble("y");
        z = getDouble("z");
        yaw = getDouble("yaw").floatValue();
        pitch = getDouble("pitch").floatValue();
        setPathPrefix(null);

        defaultAfkTime = getInteger("default");
        rgName = getString("worldguard.regionId").toLowerCase();
    }

    public Location getAfkLocation() {
        return new Location(world, x, y, z, yaw, pitch);
    }

    public void setAfkLocation(Location location) {
        this.world = location.getWorld();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();

        setPathPrefix("afk");
        set("world", world.getName());
        set("x", x);
        set("y", y);
        set("z", z);
        set("yaw", yaw);
        set("pitch", pitch);
        setPathPrefix(null);
        save(); // Persist to disk
    }

    public long getDefaultAfkTime() {
        return defaultAfkTime;
    }

    public String getRgName() {
        return rgName;
    }

    public void setRgName(String rgName) {
        this.rgName = rgName;
        set("worldguard.regionId", rgName);
        save();
    }
}