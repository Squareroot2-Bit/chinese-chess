package com.sqrt2.parts.pieceimpl;

import com.sqrt2.attributes.Color;
import com.sqrt2.attributes.Location;
import com.sqrt2.attributes.Name;
import com.sqrt2.parts.Face;
import com.sqrt2.parts.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Pawn
 * @Description
 * @date 2024/1/11 1:12
 * @Author Squareroot_2
 */
public class Pawn extends Piece {
    public Pawn(Color color, Name name) {
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
        int colorTag = getColor().ordinal();
        int locationTag = location.isInArea(getColor()) ? 0 : 1;

        for (int i = 0; i < 4; i++) {
            if (!MovableMatrix[colorTag][locationTag][i]) continue;
            end = location.add(MovableRange[i]);
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

        return locations;
    }

    private static final Location[] MovableRange = {
            Location.get(0, 1),
            Location.get(0, -1),
            Location.get(1, 0),
            Location.get(-1, 0)};
    private static final boolean[][][] MovableMatrix = {
            {
                    {false, true, false, false},    //红未过河兵
                    {false, true, true, true}       //红过河兵
            },
            {
                    {true, false, false, false},    //黑未过河兵
                    {true, false, true, true}       //黑过河兵
            }
    };
}
