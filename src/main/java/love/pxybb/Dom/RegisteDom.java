package love.pxybb.Dom;

import love.pxybb.main;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisteDom {
    public RegisteDom(String name, Player owner, BoundingBox boundingBox){
        main.DomsYaml.set(name + ".owner", owner.getName());
        List<Integer> bounding = new ArrayList<>();
        bounding.add((int) boundingBox.getMinX());
        bounding.add((int) boundingBox.getMinZ());
        bounding.add((int) boundingBox.getMaxX());
        bounding.add((int) boundingBox.getMaxZ());
        main.DomsYaml.set(name + ".bounding", bounding);
        try {
            main.DomsYaml.save(main.Doms);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
