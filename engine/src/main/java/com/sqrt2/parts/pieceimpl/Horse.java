package com.sqrt2.parts.pieceimpl;

import com.sqrt2.attributes.Color;
import com.sqrt2.attributes.Location;
import com.sqrt2.attributes.Name;
import com.sqrt2.parts.Face;
import com.sqrt2.parts.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Horse
 * @Description
 * @date 2024/1/11 1:11
 * @Author Squareroot_2
 */
public class Horse extends Piece {
    public Horse(Color color, Name name) {
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
        Location lag, end;
        for (Location[] vectors : MovableMap) {
            lag = location.add(vectors[0]);
            if (face.getPiece(lag) != null) continue;
            for (int i = 1; i < 2; i++) {
                end = location.add(vectors[i]);
                if (!end.isInBoard()) continue;
                Piece piece = face.getPiece(end);
                if (attack) {
                    if (piece != null && piece.getColor() != getColor())
                        locations.add(end);
                } else {
                    if (piece == null)
                        locations.add(end);
                }
            }
        }
        return locations;
    }

    private static final Location[][] MovableMap = {
            {Location.get(0, 1), Location.get(1, 2), Location.get(-1, 2)},
            {Location.get(0, -1), Location.get(1, -2), Location.get(-1, -2)},
            {Location.get(1, 0), Location.get(2, 1), Location.get(2, -1)},
            {Location.get(-1, 0), Location.get(-2, 1), Location.get(-2, -1)},
    };
}
