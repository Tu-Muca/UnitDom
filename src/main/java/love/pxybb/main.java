package love.pxybb;

import love.pxybb.AreaCreate.StructureHandle.StructureCheck;
import love.pxybb.Dom.DomPlayerEnter;
import love.pxybb.Dom.LoadingDom;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;


public class main extends JavaPlugin {
    public static File Doms;
    public static YamlConfiguration DomsYaml;
    public static Plugin plugin;

    @Override
    public void onEnable() {
        // 注册箱子检测监听器
        getServer().getPluginManager().registerEvents(new StructureCheck(this), this);
        getServer().getPluginManager().registerEvents(new DomPlayerEnter(), this);

        Doms = new File(getDataFolder(), "Doms.yml");
        DomsYaml = YamlConfiguration.loadConfiguration(Doms);
        plugin = this;

        // 加载Doms
        @NotNull Set<String> DomsList = DomsYaml.getKeys(false);
        Bukkit.getConsoleSender().sendMessage(String.valueOf(DomsList));
        if (!DomsList.isEmpty()){
            for (String Name : DomsList){
                new LoadingDom(Name);
            }
        }
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
