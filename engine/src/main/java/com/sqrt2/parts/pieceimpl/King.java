package com.sqrt2.parts.pieceimpl;

import com.sqrt2.attributes.*;
import com.sqrt2.parts.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName King
 * @Description
 * @date 2024/1/11 1:07
 * @Author Squareroot_2
 */
public class King extends Piece {
    public King(Color color, Name name) {
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
            if (!end.isInPalace(getColor())) continue;
            Piece piece = face.getPiece(end);
            if (attack) {
                if (piece != null && piece.getColor() != getColor())
                    locations.add(end);
            } else {
                if (piece == null)
                    locations.add(end);
            }
        }
        if (attack) {
            Location vector = Location.get(0, 0), direction = null;
            switch (getColor()) {
                case Red -> direction = Location.get(0, -1);
                case Black -> direction = Location.get(0, 1);
            }
            Piece piece;
            while (true) {
                vector = vector.add(direction);
                end = location.add(vector);
                if (!end.isInBoard()) break;
                piece = face.getPiece(end);
                if (piece != null) {
                    if (piece.getColor() != getColor() &&
                        piece.getName() == Name.King)
                        locations.add(end);
                    break;
                }
            }
        }
        return locations;
    }


    private static final Location[] MovableRange = {
            Location.get(0, 1),
            Location.get(0, -1),
            Location.get(1, 0),
            Location.get(-1, 0)};
}


