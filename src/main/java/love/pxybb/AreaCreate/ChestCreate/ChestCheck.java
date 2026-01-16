package love.pxybb.AreaCreate.ChestCreate;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

public class ChestCheck implements Listener {
    private final Plugin plugin;
    
    public ChestCheck(Plugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block placedBlock = event.getBlockPlaced();
        
        // 检查放置的是否是火把
        if (placedBlock.getType() == Material.TORCH || 
            placedBlock.getType() == Material.WALL_TORCH ||
            placedBlock.getType() == Material.REDSTONE_TORCH ||
            placedBlock.getType() == Material.REDSTONE_WALL_TORCH) {
            
            // 检查周围是否有箱子
            checkSurroundingChests(placedBlock);
        }
    }
    
    private void checkSurroundingChests(Block torchBlock) {
        // 检查四个水平方向
        BlockFace[] faces = {BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST};
        
        for (BlockFace face : faces) {
            Block adjacentBlock = torchBlock.getRelative(face);
            
            // 如果相邻方块是箱子
            if (adjacentBlock.getType() == Material.CHEST || 
                adjacentBlock.getType() == Material.TRAPPED_CHEST) {
                
                // 检查这个箱子是否被四个面的火把包围
                if (isChestSurroundedByTorches(adjacentBlock)) {
                    triggerCustomEvent(adjacentBlock);
                }
            }
        }
    }
    
    private boolean isChestSurroundedByTorches(Block chestBlock) {
        BlockFace[] faces = {BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST};
        int torchCount = 0;
        
        for (BlockFace face : faces) {
            Block adjacentBlock = chestBlock.getRelative(face);
            Material material = adjacentBlock.getType();
            
            // 检查是否是火把类型
            if (material == Material.TORCH || 
                material == Material.WALL_TORCH ||
                material == Material.REDSTONE_TORCH ||
                material == Material.REDSTONE_WALL_TORCH) {
                torchCount++;
            }
        }
        
        // 四个面都被火把包围
        return torchCount == 4;
    }
    
    private void triggerCustomEvent(Block chestBlock) {
        plugin.getLogger().info("检测到被四个火把包围的箱子: " + chestBlock.getLocation());
        
        // 这里可以触发你想要的自定义事件
        // 例如：
        // Bukkit.getPluginManager().callEvent(new CustomChestEvent(chestBlock));
        
        // 或者执行其他逻辑
        // chestBlock.getWorld().spawnParticle(Particle.FLAME, chestBlock.getLocation().add(0.5, 1, 0.5), 10);
    }
}