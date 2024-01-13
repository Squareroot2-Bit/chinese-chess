package com.sqrt2.parts;

import com.sqrt2.ChessException;
import com.sqrt2.attributes.Color;
import com.sqrt2.attributes.Location;
import com.sqrt2.attributes.Name;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Face
 * @Description
 * @date 2024/1/3 5:30
 * @Author Squareroot_2
 */
public class Face {
    public static final int Width = 9, Height = 10;

    /**
     * 轮到走棋的一方颜色
     */
    @Getter
    private final Color movingColor;
    private final Piece[][] face;

    /**
     * @param location 棋盘上的某个位置
     * @return 若该位置在棋盘上，返回该位置上的棋子
     */
    public Piece getPiece(Location location) {
        if (location.isInBoard())
            return face[location.x()][location.y()];
        else throw new ChessException("该位置不在棋盘上！");
    }

    /**
     * @param location 棋盘上某位置
     * @param piece    将该棋子置于位置上
     */
    public void setPiece(Location location, Piece piece) {
        if (location.isInBoard())
            face[location.x()][location.y()] = piece;
        else throw new ChessException("该位置不在棋盘上！");
    }

    /**
     * @param from 将位于该位置上的棋子移动
     * @param to   移动到该位置上
     * @return 返回移动后的新局面
     */
    public Face move(Location from, Location to) {
        Piece fromPiece = getPiece(from);
        if (fromPiece == null)
            throw new ChessException("没有棋子要被移动！");
        if (fromPiece.getColor() != movingColor)
            throw new ChessException("该方不能移动棋子！");
        List<Location> reachableLocations =
                fromPiece.getReachableLocations(this, from);
        if (reachableLocations.stream().anyMatch(it -> it.equals(to))) {
            Face newFace = new Face(this);
            newFace.setPiece(to, fromPiece);
            newFace.setPiece(from, null);
            return newFace;
        } else throw new ChessException("棋子不能移动到该位置！");
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String string;
        Piece piece;
        for (int j = 0; j < Height; j++) {
            for (int i = 0; i < Width; i++) {
                piece = face[i][j];
                if (piece == null) {
                    stringBuilder.append(" ");
                } else {
                    string = piece.getName().toString().substring(0, 1);
                    switch (piece.getColor()) {
                        case Red -> stringBuilder.append(string.toUpperCase());
                        case Black -> stringBuilder.append(string.toLowerCase());
                    }
                }
            }
            stringBuilder.append('\n');
        }
        string = "It's " + movingColor + "'s turn to play.";
        stringBuilder.append(string).append('\n');
        return stringBuilder.toString();
    }

    public Face() {
        this(Color.Red, InitialFace);
    }

    /**
     * 拷贝构造函数
     *
     * @param oldFace 旧的局面
     */
    public Face(Face oldFace) {
        movingColor = oldFace.getMovingColor() == Color.Red ? Color.Black : Color.Red;
        face = new Piece[Width][Height];
        for (int i = 0; i < Width; i++) {
            System.arraycopy(oldFace.face[i], 0, face[i], 0, Height);
        }
    }

    public Face(Color movingColor, Piece[][] face) {
        this.movingColor = movingColor;
        this.face = face;
    }
}
