package com.sqrt2.attributes;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @ClassName Location
 * @Description
 * @date 2024/1/3 3:51
 * @Author Squareroot_2
 */
public record Location(int x, int y) {
    public static final int MaxX = 8;
    public static final int MaxY = 9;

    private static final int BufferSize = 21;
    private static final int BufferOffset = 10;


    /**
     * @param x
     * @param y
     * @return 获取Location对象，若缓存区有则不创建对象
     */
    public static Location get(int x, int y) {
        int xo = x + BufferOffset, yo = y + BufferOffset;
        if (xo >= 0 && xo < BufferSize && yo >= 0 && yo < BufferSize)
            if (LocationBuffer != null) {
                return LocationBuffer[xo][yo];
            }
        return new Location(x, y);
    }

    /**
     * @param x
     * @param y
     * @return 位置的改变
     */
    public Location add(int x, int y) {
        return get(this.x + x, this.y + y);
    }

    /**
     * @param l
     * @return 位置的改变
     */
    public Location add(Location l) {
        return add(l.x, l.y);
    }

    /**
     * @return 该位置是否在棋盘内
     */
    public boolean isInBoard() {
        return x >= 0 && x <= MaxX && y >= 0 && y <= MaxY;
    }

    /**
     * @param color 颜色
     * @return 判断该位置是否在某方区域内
     */
    public boolean isInArea(Color color) {
        if (isInBoard()) {
            return switch (color) {
                case Red -> y >= 5;
                case Black -> y <= 4;
            };
        } else return false;
    }

    /**
     * @param color 颜色
     * @return 判断该位置是否在某方九宫格内
     */
    public boolean isInPalace(Color color) {
        if (isInArea(color) && x >= 3 && x <= 5) {
            return switch (color) {
                case Red -> y >= 7;
                case Black -> y <= 2;
            };
        } else return false;
    }

    /**
     * @param color 颜色
     * @return 判断该位置某方的士能否到达
     */
    public boolean canGuardReach(Color color) {
        if (isInPalace(color)) {
            Location[] locations = LocationGuardCanReach[color.ordinal()];
            return Arrays.stream(locations).anyMatch(this::equals);
        } else return false;
    }

    /**
     * @param color 颜色
     * @return 判断该位置某方的相/象能否到达
     */
    public boolean canMinisterReach(Color color) {
        if (isInArea(color)) {
            Location[] locations = LocationMinisterCanReach[color.ordinal()];
            return Arrays.stream(locations).anyMatch(this::equals);
        } else return false;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean equals(@NotNull Location l) {
        return x == l.x && y == l.y;
    }

    private static final Location[][] LocationGuardCanReach;
    private static final Location[][] LocationMinisterCanReach;
    /**
     * 缓存区
     */
    private static final Location[][] LocationBuffer;

    static {
        LocationBuffer = new Location[BufferSize][BufferSize];
        for (int i = 0; i < BufferSize; i++) {
            for (int j = 0; j < BufferSize; j++) {
                LocationBuffer[i][j] =
                        new Location(i - BufferOffset, j - BufferOffset);
            }
        }
        LocationGuardCanReach = new Location[][]{{
                //Red
                get(3, 9), get(5, 9),
                get(4, 8),
                get(3, 7), get(5, 7)
        }, {    //Black
                get(3, 0), get(5, 0),
                get(4, 1),
                get(3, 2), get(5, 2)
        }};
        LocationMinisterCanReach = new Location[][]{{
                //Red
                get(2, 9), get(6, 9),
                get(0, 7), get(4, 7), get(8, 7),
                get(2, 5), get(6, 5)
        }, {    //Black
                get(2, 0), get(6, 0),
                get(0, 2), get(4, 2), get(8, 2),
                get(2, 4), get(6, 4)
        }};
    }
}
