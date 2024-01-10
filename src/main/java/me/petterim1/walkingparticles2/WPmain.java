package me.petterim1.walkingparticles2;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

import java.util.*;

public class WPmain extends PluginBase {

    private Config data;

    static Map<String, String> effects;

    static Set<String> bannedEffects;

    @Override
    public void onEnable() {
        if (getDescription().getAuthors().size() != 1 || !getDescription().getAuthors().get(0).equals("PetteriM1") || !getDescription().getVersion().startsWith("2") || !getDescription().getName().equals("WalkingParticles") || !getDescription().getDescription().equals("Walking particles plugin for Nukkit")) {
            System.exit(1);
            return;
        }
        saveDefaultConfig();
        saveResource("data.yml");
        data = new Config(getDataFolder() + "/data.yml", Config.YAML);
        effects = (Map<String, String>) data.get("effects");
        if (null == effects) {
            effects = new HashMap<>();
            getLogger().info("No data found");
        }
        bannedEffects = new HashSet<>(getConfig().getStringList("bannedEffects"));
        getServer().getCommandMap().register("walkingparticles", new WPcommand());
        getServer().getPluginManager().registerEvents(new WPevents(), this);
        getLogger().info("§cWalkingParticles §aby PetteriM1 enabled!");
        getLogger().info("§6Please consider leaving a rating on nukkitx.com if you like the plugin :)");
    }

    @Override
    public void onDisable() {
        data.set("effects", effects);
        data.save(false);
    }
}
