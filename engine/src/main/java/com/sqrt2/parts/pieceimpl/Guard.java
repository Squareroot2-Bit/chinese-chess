package com.sqrt2.parts.pieceimpl;

import com.sqrt2.attributes.Color;
import com.sqrt2.attributes.Location;
import com.sqrt2.attributes.Name;
import com.sqrt2.parts.Face;
import com.sqrt2.parts.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Guard
 * @Description
 * @date 2024/1/11 1:11
 * @Author Squareroot_2
 */
public class Guard extends Piece {
    public Guard(Color color, Name name) {
        super(color, name);
    }

    /**
     * @param face     当前局面
     * @param location 该棋子所在位置
     * @param attack   是否吃子
     * @return 该棋子可以到达的位置
     */
    @Override
    public List<Location> getReachableLocations(Face face, Location location, boolean attack) {
        List<Location> locations = new ArrayList<>();
        Location end;
        for (Location vector : MovableRange) {
            end = location.add(vector);
            if (!end.canGuardReach(getColor())) continue;
            Piece piece = face.getPiece(end);
            if (attack) {
                if (piece != null && piece.getColor() != getColor())
                    locations.add(end);
            } else {
                if (piece == null)
                    locations.add(end);
            }
        }
        return locations;
    }

    private static final Location[] MovableRange = {
            Location.get(1, 1),
            Location.get(-1, -1),
            Location.get(1, -1),
            Location.get(-1, 1)};
}
