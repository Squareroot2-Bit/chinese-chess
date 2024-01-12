package com.sqrt2.parts.pieceimpl;

import com.sqrt2.attributes.Color;
import com.sqrt2.attributes.Location;
import com.sqrt2.attributes.Name;
import com.sqrt2.parts.Face;
import com.sqrt2.parts.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Cannon
 * @Description
 * @date 2024/1/11 1:12
 * @Author Squareroot_2
 */
public class Cannon extends Piece {
    public Cannon(Color color, Name name) {
        super(color, name);
    }

    /**
     * @param face     当前局面
     * @param location 该棋子所在位置
     * @return 该棋子在不吃子的情况下可以走到的位置
     */
    @Override
    public List<Location> getMovableLocations(Face face, Location location) {
        return null;
    }

    /**
     * @param face     当前局面
     * @param location 该棋子所在位置
     * @return 该棋子在吃子的情况下可以走到的位置
     */
    @Override
    public List<Location> getAssaultableLocations(Face face, Location location) {
        return null;
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
        int[] movingTag = {0, 0, 0, 0};
        Location[] movingRange = {
                Location.get(0, 1),
                Location.get(0, -1),
                Location.get(1, 0),
                Location.get(-1, 0),
        };
        for (int i = 0; i < 4; i++) {
            do {
                end = location.add(movingRange[i]);
                if (!end.isInBoard()) break;
                Piece piece = face.getPiece(end);
                if (piece != null) movingTag[i]++;
                if (attack) {
                    if (movingTag[i] == 2 &&
                        piece != null && piece.getColor() != getColor()) {
                        location.add(end);
                    }
                } else {
                    if (movingTag[i] == 0 && piece == null)
                        locations.add(end);
                }
                movingRange[i].add(movingDirection[i]);
            } while (movingTag[i] >= 2);
        }
        return locations;
    }

    private static final Location[] movingDirection = {
            Location.get(0, 1),
            Location.get(0, -1),
            Location.get(1, 0),
            Location.get(-1, 0),
    };
}
