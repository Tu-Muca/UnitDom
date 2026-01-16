package love.pxybb;

import love.pxybb.AreaCreate.ChestCreate.ChestCheck;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
    @Override
    public void onEnable() {
        // 注册箱子检测监听器
        getServer().getPluginManager().registerEvents(new ChestCheck(this), this);
    }
}
