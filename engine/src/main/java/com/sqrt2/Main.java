package com.sqrt2;

import com.sqrt2.attributes.Location;
import com.sqrt2.parts.Face;

/**
 * @ClassName ${NAME}
 * @Description
 * @date 2024/1/3 3:17
 * @Author Squareroot_2
 */
public class Main {
    public static void main(String[] args) {
        Face face = new Face();
        System.out.println(face);
        Face move = face.move(Location.get(7, 7), Location.get(7, 0));
        System.out.println(face.getPiece(Location.get(7, 7)).getAssaultableLocations(face, Location.get(7, 7)));
        System.out.println(move);
        Face move1 = move.move(Location.get(8, 0), Location.get(8, 1));
        System.out.println(move1);
    }
}