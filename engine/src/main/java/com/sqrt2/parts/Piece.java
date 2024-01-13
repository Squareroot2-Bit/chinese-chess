package com.sqrt2.parts;

import com.sqrt2.attributes.*;
import com.sqrt2.parts.pieceimpl.*;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Piece
 * @Description
 * @date 2024/1/3 5:30
 * @Author Squareroot_2
 */
@Getter
public abstract class Piece {
    private final Color color;
    private final Name name;

    /**
     * @param face     当前局面
     * @param location 该棋子所在位置
     * @return 该棋子在不吃子的情况下可以走到的位置
     */
    public final List<Location> getMovableLocations(Face face, Location location) {
        return getReachableLocations(face, location, false);
    }

    /**
     * @param face     当前局面
     * @param location 该棋子所在位置
     * @return 该棋子在吃子的情况下可以走到的位置
     */
    public final List<Location> getAssaultableLocations(Face face, Location location) {
        return getReachableLocations(face, location, true);
    }

    /**
     * @param face     当前局面
     * @param location 该棋子所在位置
     * @return 该棋子可以到达的位置
     */
    public final List<Location> getReachableLocations(Face face, Location location) {
        List<Location> reachableLocations = getMovableLocations(face, location);
        reachableLocations.addAll(getAssaultableLocations(face, location));
        return reachableLocations;
    }

    /**
     * @param face     当前局面
     * @param location 该棋子所在位置
     * @param attack   是否吃子
     * @return 该棋子可以到达的位置
     */
    public abstract List<Location> getReachableLocations(Face face, Location location, boolean attack);

    /**
     * @param piece 需要比较的棋子对象
     * @return 判断两个棋子是否相同
     */
    public final boolean equals(Piece piece) {
        return color == piece.color && name == piece.name;
    }

    /**
     * @param color 棋子颜色
     * @param name  棋子名称
     * @return 当需要获取棋子对象时，从PieceMap中获取已创建好的棋子对象，不必新建
     */
    public static Piece get(Color color, Name name) {
        return PieceMap[color.ordinal()][name.ordinal()];
    }

    private static final Piece[][] PieceMap;

    static {
        PieceMap = new Piece[2][7];
        @FunctionalInterface
        interface PieceGetter {
            Piece getPiece(Color color, Name name);
        }
        Map<Name, PieceGetter> builderMap = new HashMap<>();
        builderMap.put(Name.King, King::new);
        builderMap.put(Name.Guard, Guard::new);
        builderMap.put(Name.Minister, Minister::new);
        builderMap.put(Name.Horse, Horse::new);
        builderMap.put(Name.Rook, Rook::new);
        builderMap.put(Name.Cannon, Cannon::new);
        builderMap.put(Name.Pawn, Pawn::new);
        for (Color color : Color.values()) {
            for (Name name : Name.values()) {
                PieceMap[color.ordinal()][name.ordinal()] =
                        builderMap.get(name).getPiece(color, name);
            }
        }
    }

    /**
     * @param color 棋子颜色
     * @param name  棋子名称
     */
    protected Piece(Color color, Name name) {
        this.color = color;
        this.name = name;
    }

}
