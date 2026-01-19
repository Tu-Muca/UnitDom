package love.pxybb.Dom;

import love.pxybb.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LoadingDom {
    public static final ArrayList<Dom> doms = new ArrayList<>();
    public static final Map<Player, Dom> PlayerInDomMap = new java.util.HashMap<>();

    public LoadingDom(String name){
        List<Integer> range = main.DomsYaml.getIntegerList(name + ".bounding");
        BoundingBox boundingBox = new BoundingBox(range.get(0), -64, range.get(1), range.get(2), 320, range.get(3));
        Player owner = Bukkit.getPlayer(main.DomsYaml.getString(name + ".owner"));
        Dom dom = new Dom(name,owner,boundingBox);
        doms.add(dom);
    }
}
