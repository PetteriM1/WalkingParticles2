package petterim1.walkingparticles2;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

import java.util.*;

public class WPmain extends PluginBase {

    private Config data;

    static Map<String, String> effects;

    static Set<String> bannedEffects;

    @Override
    public void onEnable() {
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
    }

    @Override
    public void onDisable() {
        data.set("effects", effects);
        data.save(false);
    }
}
