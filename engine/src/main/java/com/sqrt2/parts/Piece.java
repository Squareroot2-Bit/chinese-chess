package com.sqrt2.parts;

import com.sqrt2.attributes.Color;
import com.sqrt2.attributes.Location;
import com.sqrt2.attributes.Name;

import java.util.List;

/**
 * @ClassName Piece
 * @Description
 * @date 2024/1/3 5:30
 * @Author Squareroot_2
 */
public abstract class Piece {
    Color color;
    Name name;

    abstract List<Location> getMoveableLocations(Face face, Location location);

    abstract List<Location> getAttachableLocations(Face face, Location location);
}
