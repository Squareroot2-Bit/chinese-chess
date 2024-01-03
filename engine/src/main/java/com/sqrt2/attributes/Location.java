package com.sqrt2.attributes;

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

    /**
     * @param x
     * @param y
     * @return 位置的改变
     */
    public Location add(int x, int y) {
        return new Location(this.x + x, this.y + y);
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

    public boolean equals(Location l) {
        return x == l.x && y == l.y;
    }


    private static final Location[][] LocationGuardCanReach = {{
            //Red
            new Location(3, 9),
            new Location(5, 9),
            new Location(4, 8),
            new Location(3, 7),
            new Location(5, 7)
    }, {    //Black
            new Location(3, 0),
            new Location(5, 0),
            new Location(4, 1),
            new Location(3, 2),
            new Location(5, 2)
    }};

    private static final Location[][] LocationMinisterCanReach = {{
            //Red
            new Location(2, 9),
            new Location(6, 9),
            new Location(0, 7),
            new Location(4, 7),
            new Location(8, 7),
            new Location(2, 5),
            new Location(6, 5)
    }, {    //Black
            new Location(2, 0),
            new Location(6, 0),
            new Location(0, 2),
            new Location(4, 2),
            new Location(8, 2),
            new Location(2, 4),
            new Location(6, 4)
    }};

}
