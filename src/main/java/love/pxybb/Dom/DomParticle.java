package love.pxybb.Dom;

import com.destroystokyo.paper.ParticleBuilder;
import java.util.List;

import love.pxybb.main;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

import java.util.ArrayList;

public class DomParticle {
    public DomParticle(Player player) {
        Dom dom = LoadingDom.PlayerInDomMap.get(player);
        BoundingBox boundingBox = dom.getBoundingBox();
        List<Player> List = new ArrayList<>();
        List.add(player);
        ParticleBuilder particleBuilder = new ParticleBuilder(Particle.GLOW_SQUID_INK).count(1).offset(0,0,0).extra(0);
        ArrayList<double[]> boundaryPoints = getBoundaryPoints(boundingBox.getMinX(), boundingBox.getMinZ(), boundingBox.getMaxX(), boundingBox.getMaxZ(), 1);
        for (int a = 1; a < 65; a++){
            double finalA = a;
            Bukkit.getScheduler().scheduleSyncDelayedTask(main.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    for (double i = player.getLocation().getBlockY()-64+(finalA/4); i < player.getLocation().getY()+64+(finalA/4) ; i += 16){
                        for (double[] point : boundaryPoints){
                            Location location = new Location(player.getWorld(), point[0], i, point[1]);
                            particleBuilder.location(location).spawn();
                        }
                    }
                }
            },a);
        }
    }

    ArrayList<double[]> getBoundaryPoints(double x1, double y1, double x2, double y2, double step) {
        if (step <= 0) {
            throw new IllegalArgumentException("Step must be positive.");
        }

        // 标准化矩形：确保 (x1,y1) 是左下，(x2,y2) 是右上
        double minX = Math.min(x1, x2);
        double maxX = Math.max(x1, x2);
        double minY = Math.min(y1, y2);
        double maxY = Math.max(y1, y2);

        ArrayList<double[]> points = new ArrayList<double[]>();

        // 上边：y = maxY, x 从 minX 到 maxX
        for (double x = minX; x <= maxX; x += step) {
            points.add(new double[]{x, maxY});
        }

        // 下边：y = minY, x 从 minX 到 maxX
        for (double x = minX; x <= maxX; x += step) {
            points.add(new double[]{x, minY});
        }

        // 左边：x = minX, y 从 minY + step 到 maxY - step（避免重复角点）
        for (double y = minY + step; y < maxY; y += step) {
            points.add(new double[]{minX, y});
        }

        // 右边：x = maxX, y 从 minY + step 到 maxY - step
        for (double y = minY + step; y < maxY; y += step) {
            points.add(new double[]{maxX, y});
        }

        return points;
    }

}
