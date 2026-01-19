package love.pxybb.Dom;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

public class Dom {
    String name;
    Player owner;
    BoundingBox boundingBox;

    Dom(String name, Player owner, BoundingBox boundingBox){
        this.name = name;
        this.owner = owner;
        this.boundingBox = boundingBox;
    }



    BoundingBox getBoundingBox(){
        return this.boundingBox;
    }

    String getName(){
        return this.name;
    }

    Player getOwner(){
        return this.owner;
    }
}
