package com.sqrt2.parts;

import com.sqrt2.ChessException;
import com.sqrt2.attributes.Color;
import com.sqrt2.attributes.Location;
import com.sqrt2.attributes.Name;
import lombok.Getter;

/**
 * @ClassName Face
 * @Description
 * @date 2024/1/3 5:30
 * @Author Squareroot_2
 */
@Getter
public class Face {
    public static final int Width = 9, Height = 10;

    private final Color movingColor;
    private final Piece[][] face;

    public Piece get(Location location) {
        if (location.isInBoard())
            return face[location.x()][location.y()];
        else throw new ChessException("该位置不在棋盘上！");
    }

    private static final Piece[][] InitialFace;

    static {
        InitialFace = new Piece[Width][Height];
        for (int i = 0; i < Width; i++) {
            for (int j = 0; j < Height; j++) {
                InitialFace[i][j] = null;
            }
        }
        InitialFace[0][0] = Piece.get(Color.Black, Name.Rook);
        InitialFace[1][0] = Piece.get(Color.Black, Name.Horse);
        InitialFace[2][0] = Piece.get(Color.Black, Name.Minister);
        InitialFace[3][0] = Piece.get(Color.Black, Name.Guard);
        InitialFace[4][0] = Piece.get(Color.Black, Name.King);
        InitialFace[5][0] = Piece.get(Color.Black, Name.Guard);
        InitialFace[6][0] = Piece.get(Color.Black, Name.Minister);
        InitialFace[7][0] = Piece.get(Color.Black, Name.Horse);
        InitialFace[8][0] = Piece.get(Color.Black, Name.Rook);
        InitialFace[1][2] = Piece.get(Color.Black, Name.Cannon);
        InitialFace[7][2] = Piece.get(Color.Black, Name.Cannon);
        InitialFace[0][3] = Piece.get(Color.Black, Name.Pawn);
        InitialFace[2][3] = Piece.get(Color.Black, Name.Pawn);
        InitialFace[4][3] = Piece.get(Color.Black, Name.Pawn);
        InitialFace[6][3] = Piece.get(Color.Black, Name.Pawn);
        InitialFace[8][3] = Piece.get(Color.Black, Name.Pawn);

        InitialFace[0][9] = Piece.get(Color.Red, Name.Rook);
        InitialFace[1][9] = Piece.get(Color.Red, Name.Horse);
        InitialFace[2][9] = Piece.get(Color.Red, Name.Minister);
        InitialFace[3][9] = Piece.get(Color.Red, Name.Guard);
        InitialFace[4][9] = Piece.get(Color.Red, Name.King);
        InitialFace[5][9] = Piece.get(Color.Red, Name.Guard);
        InitialFace[6][9] = Piece.get(Color.Red, Name.Minister);
        InitialFace[7][9] = Piece.get(Color.Red, Name.Horse);
        InitialFace[8][9] = Piece.get(Color.Red, Name.Rook);
        InitialFace[1][7] = Piece.get(Color.Red, Name.Cannon);
        InitialFace[7][7] = Piece.get(Color.Red, Name.Cannon);
        InitialFace[0][6] = Piece.get(Color.Red, Name.Pawn);
        InitialFace[2][6] = Piece.get(Color.Red, Name.Pawn);
        InitialFace[4][6] = Piece.get(Color.Red, Name.Pawn);
        InitialFace[6][6] = Piece.get(Color.Red, Name.Pawn);
        InitialFace[8][6] = Piece.get(Color.Red, Name.Pawn);
    }

    public Face() {
        this(Color.Red, InitialFace);
    }

    public Face(Color movingColor, Piece[][] face) {
        this.movingColor = movingColor;
        this.face = face;
    }
}
