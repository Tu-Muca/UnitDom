package love.pxybb.AreaCreate.StructureHandle;

import love.pxybb.Dom.LoadingDom;
import love.pxybb.Dom.RegisteDom;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;

public class StructureCheck implements Listener {
    private final Plugin plugin;
    
    public StructureCheck(Plugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block placedBlock = event.getBlockPlaced();
        if (event.getBlockAgainst().getType() != Material.CRAFTING_TABLE) {
            return;
        }
        // 检查放置的是否是火把
        if (placedBlock.getType() != Material.WALL_TORCH) {
            return;
        }

        // 获取对象
        Player player = event.getPlayer();
        Block againstBlock = event.getBlockAgainst();
        BlockFace[] faces = {BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST};

        // 检查相邻的方块是否都是火把
        for (BlockFace face : faces) {
            Block adjacentBlock = againstBlock.getRelative(face);
            Material material = adjacentBlock.getType();
            if (material != Material.WALL_TORCH) {
                return;
            }
        }

        // 触发事件
        BoundingBox boundingBox = new BoundingBox(againstBlock.getX() - 10, -64, againstBlock.getZ() - 10, againstBlock.getX() + 10, 320, againstBlock.getZ() + 10);
        new RegisteDom(player.getName(), player, boundingBox);
        new LoadingDom(player.getName());
        player.sendMessage("Dom创建成功！");
    }
}