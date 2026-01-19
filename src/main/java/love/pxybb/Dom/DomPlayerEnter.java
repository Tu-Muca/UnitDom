package love.pxybb.Dom;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.BoundingBox;

public class DomPlayerEnter implements Listener {
    @EventHandler
    public void onPlayerEnterDom(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Dom EnterDom = null;
        for (Dom dom : LoadingDom.doms){
            BoundingBox boundingBox = dom.getBoundingBox();
            if (boundingBox.contains(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ())){
                // 玩家进入Dom区域
                EnterDom = dom;
                break;
            }
        }

        if (EnterDom == null){
            if (LoadingDom.PlayerInDomMap.get(player) != null){
                player.sendMessage("你离开了" + LoadingDom.PlayerInDomMap.get(player).getName() + "的Dom区域！");
                LoadingDom.PlayerInDomMap.remove(player);
            }
            return;}
        if (LoadingDom.PlayerInDomMap.get(player) == EnterDom){
            return;
        }
        BoundingBox boundingBox = EnterDom.getBoundingBox();
        player.sendMessage("欢迎来到" + EnterDom.getName() + "的Dom区域！");
        LoadingDom.PlayerInDomMap.put(player, EnterDom);
        new DomParticle(player);
    }
}
