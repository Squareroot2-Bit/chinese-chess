package com.sqrt2.parts.pieceimpl;

import com.sqrt2.attributes.Color;
import com.sqrt2.attributes.Location;
import com.sqrt2.attributes.Name;
import com.sqrt2.parts.Face;
import com.sqrt2.parts.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Rook
 * @Description
 * @date 2024/1/11 1:11
 * @Author Squareroot_2
 */
public class Rook extends Piece {
    public Rook(Color color, Name name) {
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
                    if (movingTag[i] == 1 &&
                        piece != null && piece.getColor() != getColor()) {
                        location.add(end);
                    }
                } else {
                    if (movingTag[i] == 0 && piece == null)
                        locations.add(end);
                }
                movingRange[i].add(movingDirection[i]);
            } while (movingTag[i] >= 1);
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
