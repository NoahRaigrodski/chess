//Adam Staviarsky

//Piece name: Monk
//Monk is essentially a pawn with reversed functions. It moves diagonally by one square
//and captures pieces in front of it. Unlike a pawn, It can't move by 2 squares initially.
//Monk's color is also important as it sets the direction of his movement (White monk will
//move upward and black Monk will move downward).
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

/*
//To make this code work, add this to the mouseReleased method in class Board:

//This allows the piece to move diagonally:

if(currPiece.getColor()== true && currPiece.getLegalMoves(this,fromMoveSquare).contains(endSquare)  ) {
    		endSquare.put(currPiece);
    		fromMoveSquare.put(null);
}

if(currPiece.getColor()== false && currPiece.getLegalMoves(this,fromMoveSquare).contains(endSquare)  ) {
    		endSquare.put(currPiece);
    		fromMoveSquare.put(null);


//And this allows to capture:
 
if(currPiece.getColor()== true && endSquare!=null && endSquare.getOccupyingPiece()!=null && endSquare.getOccupyingPiece().getColor()==false && currPiece.getControlledSquares(board,fromMoveSquare).contains(endSquare) ) {
        	endSquare.put(currPiece);
    		fromMoveSquare.put(null);
}

if(currPiece.getColor()== false && endSquare!=null && endSquare.getOccupyingPiece()!=null && endSquare.getOccupyingPiece().getColor()==true && currPiece.getControlledSquares(board,fromMoveSquare).contains(endSquare) ) {
        	endSquare.put(currPiece);
    		fromMoveSquare.put(null);
}


*/

//you will need to implement two functions in this file.
public class Monk extends Piece {

	public Monk(boolean isWhite, String img_file) {
		super(isWhite, img_file);

	}

	public String toString() {
		if (this.getColor()) {
			return "A white Monk";
		} else {
			return "A black Monk";
		}
	}

	// TO BE IMPLEMENTED!
	// return a list of every square that is "controlled" by this piece. A square is
	// controlled
	// if the piece capture into it legally.

	// Pre-condition: not null for both parameters, board is set up (8*8 squares)
	// Post-condition: returns an ArrayList with a set of squares that it controls
	// based on the color of the selected piece
	public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {

		ArrayList<Square> sss = new ArrayList<Square>();

		if ((start.getOccupyingPiece()).getColor() == true) {
			if (start.getRow() - 1 >= 0) {
				sss.add(board[start.getRow() - 1][start.getCol()]);
			}

		}

		if ((start.getOccupyingPiece()).getColor() == false) {
			if (start.getRow() + 1 < 8)

				sss.add(board[start.getRow() + 1][start.getCol()]);

		}

		return sss;
	}

	// TO BE IMPLEMENTED!
	// implement the move function here
	// it's up to you how the piece moves, but at the very least the rules should be
	// logical and it should never move off the board!
	// returns an arraylist of squares which are legal to move to
	// please note that your piece must have some sort of logic. Just being able to
	// move to every square on the board is not
	// going to score any points.

	// Pre-condition: not null for both parameters
	// Post-condition: returns an ArrayList with a set of squares where it can
	// legally move to based on the color of the selected piece
	public ArrayList<Square> getLegalMoves(Board b, Square start) {
		ArrayList<Square> moves = new ArrayList<Square>();

		// Added by me, Noah, to avoid null pointer exceptions. 
		if (start.getOccupyingPiece() != null) {

			if ((start.getOccupyingPiece()).getColor() == true) {
				if (start.getRow() - 1 >= 0 && start.getCol() + 1 < 8
						&& !((b.getSquareArray()[start.getRow() - 1][start.getCol() + 1]).isOccupied())) {
					moves.add(b.getSquareArray()[start.getRow() - 1][start.getCol() + 1]);
				}
				if (start.getRow() - 1 >= 0 && start.getCol() - 1 >= 0
						&& !((b.getSquareArray()[start.getRow() - 1][start.getCol() - 1]).isOccupied())) {
					moves.add(b.getSquareArray()[start.getRow() - 1][start.getCol() - 1]);
				}
			}
			if ((start.getOccupyingPiece()).getColor() == false) {
				if (start.getRow() + 1 < 8 && start.getCol() + 1 < 8
						&& !((b.getSquareArray()[start.getRow() + 1][start.getCol() + 1]).isOccupied())) {
					moves.add(b.getSquareArray()[start.getRow() + 1][start.getCol() + 1]);
				}
				if (start.getRow() + 1 < 8 && start.getCol() - 1 >= 0
						&& !((b.getSquareArray()[start.getRow() + 1][start.getCol() - 1]).isOccupied())) {
					moves.add(b.getSquareArray()[start.getRow() + 1][start.getCol() - 1]);
				}

			}
		}

		return moves;
	}
}