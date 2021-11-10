package petterim1.walkingparticles2;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.level.Location;
import cn.nukkit.level.ParticleEffect;

public class WPevents implements Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void PlayerMoveEvent(PlayerMoveEvent ev) {
        Location from = ev.getFrom();
        Location to = ev.getTo();
        if (from.x != to.x || from.y != to.y || from.z != to.z) {
            Player p = ev.getPlayer();
            String effect = WPmain.effects.get(p.getName());
            if (effect != null) {
                p.getLevel().addParticleEffect(p, ParticleEffect.valueOf(effect));
            }
        }
    }
}
