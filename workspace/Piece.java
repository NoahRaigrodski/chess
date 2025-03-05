
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Piece {
  private final boolean color;
  private BufferedImage img;

  public Piece(boolean isWhite, String img_file) {
    this.color = isWhite;

    try {
      if (this.img == null) {
        this.img = ImageIO.read(getClass().getResource(img_file));
      }
    } catch (IOException e) {
      System.out.println("File not found: " + e.getMessage());
    }
  }

  public boolean getColor() {
    return color;
  }

  public Image getImage() {
    return img;
  }

  public void draw(Graphics g, Square currentSquare) {
    int x = currentSquare.getX();
    int y = currentSquare.getY();

    g.drawImage(this.img, x, y, null);
  }

  // TO BE IMPLEMENTED!
  // return a list of every square that is "controlled" by this piece. A square is
  // controlled
  // if the piece can capture into it legally.
  public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
    ArrayList<Square> controlledSquares = new ArrayList<Square>();

    controlledSquares.add(board[start.getRow()][start.getCol()]);

    int num = 2;

    // Booleans to determine if a piece will stay on the board
    boolean canControlLeft = start.getRow() - num >= 0;
    boolean canControlRight = start.getRow() + num < 8;
    boolean canControlDown = start.getCol() + num < 8;
    boolean canControlUp = start.getCol() - num >= 0;
    boolean canTipToeUp = start.getRow() - 1 >= 0;
    boolean canTipToeDown = start.getRow() + 1 < 8;

    // If the piece can capture in a direction without moving off the board, then add
    // that square to controlledSquares
    if (canControlDown) {
      controlledSquares.add(board[start.getRow()][start.getCol() + num]);
    }
    if (canControlUp) {
      controlledSquares.add(board[start.getRow()][start.getCol() - num]);
    }
    if (canControlRight) {
      controlledSquares.add(board[start.getRow() + num][start.getCol()]);
    }
    if (canControlLeft) {
      controlledSquares.add(board[start.getRow() - num][start.getCol()]);
    }
    if (canControlRight && canControlDown) {
      controlledSquares.add(board[start.getRow() + num][start.getCol() + num]);
    }
    if (canControlRight && canControlUp) {
      controlledSquares.add(board[start.getRow() + num][start.getCol() - num]);
    }
    if (canControlLeft && canControlDown) {
      controlledSquares.add(board[start.getRow() - num][start.getCol() + num]);
    }
    if (canControlLeft && canControlUp) {
      controlledSquares.add(board[start.getRow() - num][start.getCol() - num]);
    }
    if (start.getOccupyingPiece().getColor() && canTipToeUp){
      controlledSquares.add(board[start.getRow()-1][start.getCol()]);
    }
    if (!start.getOccupyingPiece().getColor() && canTipToeDown){
      controlledSquares.add(board[start.getRow()+1][start.getCol()]);
    }


    return controlledSquares;
  }

  // The Italian Kratos piece can move 2 squares in every direction, no more and no less. There is an exception when going toward the opposing side of the board (either up or down), Kratos can tip toe 1 square forward.
  // The direction has to be strict. For example Kratos can move 2 squares northwest, north, northeast, east, southeast, south, southwest, or west.
  // Kratos can only move 1 square if its forward. Forward meaning towards the other end of the board.
  // precondition: The board has been created with squares and start isn't null
	// postcondition: A list of legal moves for a piece on the start sqaure is returned
  public ArrayList<Square> getLegalMoves(Board b, Square start) {
    ArrayList<Square> legalMoves = new ArrayList<Square>();
    int num = 2;

    // Booleans to determine if a piece will stay on the board
    boolean canMoveUp = start.getRow() - num >= 0;
    boolean canMoveDown = start.getRow() + num < 8;
    boolean canMoveRight = start.getCol() + num < 8;
    boolean canMoveLeft = start.getCol() - num >= 0;
    boolean canTipToeUp = start.getRow() - 1 >= 0;
    boolean canTipToeDown = start.getRow() + 1 < 8;

    // Testing
    System.out.println("The piece can move right " + canMoveRight);
    System.out.println("The piece can move left " + canMoveLeft);
    System.out.println("The piece can move down " + canMoveDown);
    System.out.println("The piece can move up " + canMoveUp);
    System.out.println("The piece can tip toe up " + canTipToeUp);

    // if its the piece's turn then check for legal moves
    if (start.getOccupyingPiece().getColor() == b.getTurn()) {
      // If the piece can move in a direction without moving off the board, then add
      // that move to legalMoves
      if (canMoveRight) {
        legalMoves.add(b.getSquareArray()[start.getRow()][start.getCol() + num]);
      }
      if (canMoveLeft) {
        legalMoves.add(b.getSquareArray()[start.getRow()][start.getCol() - num]);
      }
      if (canMoveDown) {
        legalMoves.add(b.getSquareArray()[start.getRow() + num][start.getCol()]);
      }
      if (canMoveUp) {
        legalMoves.add(b.getSquareArray()[start.getRow() - num][start.getCol()]);
      }
      if (canMoveRight && canMoveDown) {
        legalMoves.add(b.getSquareArray()[start.getRow() + num][start.getCol() + num]);
      }
      if (canMoveRight && canMoveUp) {
        legalMoves.add(b.getSquareArray()[start.getRow() - num][start.getCol() + num]);
      }
      if (canMoveLeft && canMoveDown) {
        legalMoves.add(b.getSquareArray()[start.getRow() + num][start.getCol() - num]);
      }
      if (canMoveLeft && canMoveUp) {
        legalMoves.add(b.getSquareArray()[start.getRow() - num][start.getCol() - num]);
      }
      if (start.getOccupyingPiece().getColor() && canTipToeUp){
        legalMoves.add(b.getSquareArray()[start.getRow()-1][start.getCol()]);
      }
      if (!start.getOccupyingPiece().getColor() && canTipToeDown){
        legalMoves.add(b.getSquareArray()[start.getRow()+1][start.getCol()]);
      }


      // Checks if any of the moves in legalMoves can't be legal because a square is
      // already occupied by the same colored piece
      for (int i = legalMoves.size() - 1; i >= 0; i--) {
        Square move = legalMoves.get(i);
        if (move.isOccupied() && move.getOccupyingPiece().getColor() == start.getOccupyingPiece().getColor()) {
          legalMoves.remove(i);
        }
      }

    }

    return legalMoves;
  }
}