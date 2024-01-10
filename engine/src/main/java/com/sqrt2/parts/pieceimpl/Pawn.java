package com.sqrt2.parts.pieceimpl;

import com.sqrt2.attributes.Color;
import com.sqrt2.attributes.Location;
import com.sqrt2.attributes.Name;
import com.sqrt2.parts.Face;
import com.sqrt2.parts.Piece;

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
     * @return 该棋子在不吃子的情况下可以走到的位置
     */
    @Override
    public List<Location> getMoveableLocations(Face face, Location location) {
        return null;
    }

    /**
     * @param face     当前局面
     * @param location 该棋子所在位置
     * @return 该棋子在吃子的情况下可以走到的位置
     */
    @Override
    public List<Location> getAttachableLocations(Face face, Location location) {
        return null;
    }
}
