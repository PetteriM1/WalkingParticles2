package petterim1.walkingparticles2;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.level.ParticleEffect;

public class WPcommand extends Command {

    public WPcommand() {
        super("walkingparticles");
        setPermission("walkingparticles.command");
        setUsage("/walkingparticles");
        setDescription("WalkingParticles");
        commandParameters.clear();
        commandParameters.put("default", new CommandParameter[] { new CommandParameter("walkingparticles", true, new String[] { "help", "list", "enable", "disable" })});
    }

    @Override
    public boolean execute(CommandSender s, String c, String[] a) {
        if (!s.hasPermission(getPermission())) return false;
        if (!(s instanceof Player)) {
            s.sendMessage("§cThis command only works in game");
            return true;
        }

        if (0 == a.length) {
            showHelp(s);
            return true;
        }

        String n = s.getName();

        switch (a[0].toLowerCase()) {
            case "disable":
                if (WPmain.effects.containsKey(n)) {
                    WPmain.effects.remove(n);
                    s.sendMessage("§aWalking particles disabled");
                } else {
                    s.sendMessage("§cYou don't have walking particles enabled");
                }
                break;
            case "enable":
                if (2 != a.length) {
                    showHelp(s);
                    return true;
                }

                String e = a[1].toUpperCase();
                if (!isValidParticle(e)) {
                    s.sendMessage("§cUnknown effect. Use §e/walkingparticles list §cto see a list of available particle effects.");
                    return true;
                }

                if (WPmain.bannedEffects.contains(e)) {
                    s.sendMessage("§cThis effect is banned on this server");
                    return true;
                }

                if (WPmain.effects.containsKey(n)) {
                    WPmain.effects.put(n, e);
                    s.sendMessage("§6Walking particles updated");
                } else {
                    WPmain.effects.put(n, e);
                    s.sendMessage("§aWalking particles enabled");
                }
                break;
            case "list":
                StringBuilder msg = new StringBuilder("§aAvailable walking particles:§7");
                for (ParticleEffect pe : ParticleEffect.values()) {
                    if (!WPmain.bannedEffects.contains(pe.toString())) {
                        msg.append('\n').append(pe);
                    }
                }
                s.sendMessage(msg.toString());
                break;
            case "help":
            default:
                showHelp(s);
        }

        return true;
    }

    private static void showHelp(CommandSender s) {
        s.sendMessage("§l§cWalkingParticles §r§fby PetteriM1");
        s.sendMessage("§7/walkingparticles enable <effect> - Enable walking particles");
        s.sendMessage("§7/walkingparticles disable - Disable walking particles");
        s.sendMessage("§7/walkingparticles list - Show list of all available walking particles");
        s.sendMessage("§7/walkingparticles help - Show this page");
    }

    private static boolean isValidParticle(String p) {
        try {
            ParticleEffect.valueOf(p);
            return true;
        } catch (Exception ignore) {
            return false;
        }
    }
}
