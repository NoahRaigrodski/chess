//Piece type: Pope
//Piece by Minjun Kim
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//The pope piece is an extraordinary piece which the bishop can be promoted to. It can move diagnally like a bishop, and it can move 1 unit in NESW directions.
//The bishop could be promoted to a pope after the bishop reaches the other end of the chessboard, just like a pawn promotion. 
//However, the condition is not included in the code.
//Make the spawncondition at your own risk.
public class Pope extends Piece {

  
    public Pope(boolean color, String img_file) {
      super(color, img_file);

      
        
    }

    public String toString() {
      if (color) {
        return("A " + "white Pope");
      
      } else {
        return("A " + "black Pope");

      }
      
    }
    
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
        System.out.println("entering legalMoves"+System.currentTimeMillis());
          ArrayList<Square> legal = new ArrayList<Square>();
          if (start.getCol() > 0 && start.getCol() < 7) {
    
              legal.add(board[start.getRow()][start.getCol() + 1]);
              legal.add(board[start.getRow()][start.getCol() - 1]);
             
          } else if(start.getCol() == 0) {
            
              legal.add(board[start.getRow()][start.getCol() + 1]);
  
          } else if(start.getCol() == 7) {
  
              legal.add(board[start.getRow()][start.getCol() - 1]);
          }
  
          if (start.getRow() > 0 && start.getRow() < 7) {
    
              legal.add(board[start.getRow() + 1][start.getCol()]);
              legal.add(board[start.getRow() - 1][start.getCol()]);
  
          } else if(start.getRow() == 0) {
  
              legal.add(board[start.getRow() + 1][start.getCol()]);
              
          } else if(start.getRow() == 7) {
           
              legal.add(board[start.getRow() - 1][start.getCol()]);
              
          }
  
            //down and right
          for (int j = 1; start.getCol() + j <= 7 && start.getRow() + j <= 7; j++) {          
            if (!board[start.getRow() + j][start.getCol() + j].isOccupied()) {
                legal.add(board[start.getRow() + j][start.getCol() + j]);  
              }
              else if (board[start.getRow() + j][start.getCol() + j].isOccupied()){
                legal.add(board[start.getRow() + j][start.getCol() + j]);
                break;
              }
              else {
                break;
              }
          } 
          //up and left
          for (int j = 1; start.getCol() - j >= 0 && start.getRow() - j >= 0; j++) {          
            if (!board[start.getRow() - j][start.getCol() - j].isOccupied()) {
                legal.add(board[start.getRow() - j][start.getCol() - j]);  
              }
              else if (board[start.getRow() - j][start.getCol() - j].isOccupied()){
                legal.add(board[start.getRow() - j][start.getCol() - j]);
                break;
              }
              else {
                break;
              }
          } 
          //up and right
          for (int j = 1; start.getCol() - j >= 0 && start.getRow() + j <= 7; j++) {          
            if (!board[start.getRow() + j][start.getCol() - j].isOccupied()) {
                legal.add(board[start.getRow() + j][start.getCol() - j]);  
              }
              else if (board[start.getRow() + j][start.getCol() - j].isOccupied()){
                legal.add(board[start.getRow() + j][start.getCol() - j]);
                break;
              }
              else {
                break;
              }
          } 
          //down and left
          for (int j = 1; start.getCol() + j <= 7 && start.getRow() - j >= 0; j++) {          
            if (!board[start.getRow() - j][start.getCol() + j].isOccupied()) {
                legal.add(board[start.getRow() - j][start.getCol() + j]);  
              }
              else if (board[start.getRow() - j][start.getCol() + j].isOccupied()){
                legal.add(board[start.getRow() - j][start.getCol() + j]);
                break;
              }
              else {
                break;
              }
          } 
          return(legal);
      
        
    }
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //The piece is the pope where they can movce like a bishop diagnally with infinite amounts of the units, or the piece could move NESW with 1 units like a king.
    public ArrayList<Square> getLegalMoves(Board b, Square start) {
        ArrayList<Square> legal = new ArrayList<Square>();
        if (start.getCol() > 0 && start.getCol() < 7) {
          if (!b.getSquareArray()[start.getRow()][start.getCol() + 1].isOccupied()) {

            legal.add(b.getSquareArray()[start.getRow()][start.getCol() + 1]);

          } else if (!b.getSquareArray()[start.getRow()][start.getCol() + 1].getOccupyingPiece().getColor() == this.getColor()) {

            legal.add(b.getSquareArray()[start.getRow()][start.getCol() + 1]);
            
          }

          if (!b.getSquareArray()[start.getRow()][start.getCol() - 1].isOccupied()) {
            legal.add(b.getSquareArray()[start.getRow()][start.getCol() - 1]);


          } else if (!b.getSquareArray()[start.getRow()][start.getCol() - 1].getOccupyingPiece().getColor() == this.getColor()) {
  
            legal.add(b.getSquareArray()[start.getRow()][start.getCol() - 1]);
            
          }

        } else if(start.getCol() == 0) {
          if (!b.getSquareArray()[start.getRow()][start.getCol() + 1].isOccupied()) {
            legal.add(b.getSquareArray()[start.getRow()][start.getCol() + 1]);

          } else if (!b.getSquareArray()[start.getRow()][start.getCol() + 1].getOccupyingPiece().getColor() == this.getColor()) {

            legal.add(b.getSquareArray()[start.getRow()][start.getCol() + 1]);
            
          }
        } else if(start.getCol() == 7) {

          if (!b.getSquareArray()[start.getRow()][start.getCol() - 1].isOccupied()) {

            legal.add(b.getSquareArray()[start.getRow()][start.getCol() - 1]);

          } else if (!b.getSquareArray()[start.getRow()][start.getCol() - 1].getOccupyingPiece().getColor() == this.getColor()) {

            legal.add(b.getSquareArray()[start.getRow()][start.getCol() - 1]);
            
          }
        }

        if (start.getRow() > 0 && start.getRow() < 7) {

          if (!b.getSquareArray()[start.getRow() + 1][start.getCol()].isOccupied()) {
  
            legal.add(b.getSquareArray()[start.getRow() + 1][start.getCol()]);
            
          } else if (!b.getSquareArray()[start.getRow() + 1][start.getCol()].getOccupyingPiece().getColor() == this.getColor()) {

            legal.add(b.getSquareArray()[start.getRow() + 1][start.getCol()]);
            
          }

          if (!b.getSquareArray()[start.getRow() - 1][start.getCol()].isOccupied()) {
  
            legal.add(b.getSquareArray()[start.getRow() - 1][start.getCol()]);
            
          } else if (!b.getSquareArray()[start.getRow() - 1][start.getCol()].getOccupyingPiece().getColor() == this.getColor()) {

            legal.add(b.getSquareArray()[start.getRow() - 1][start.getCol()]);
            
          }

        } else if(start.getRow() == 0) {
          if (!b.getSquareArray()[start.getRow() + 1][start.getCol()].isOccupied()) {

            legal.add(b.getSquareArray()[start.getRow() + 1][start.getCol()]);
            
          } else if (!b.getSquareArray()[start.getRow() + 1][start.getCol()].getOccupyingPiece().getColor() == this.getColor()) {

            legal.add(b.getSquareArray()[start.getRow() + 1][start.getCol()]);
            
          }
        } else if(start.getRow() == 7) {
          if (!b.getSquareArray()[start.getRow() - 1][start.getCol()].isOccupied()) {

            legal.add(b.getSquareArray()[start.getRow() - 1][start.getCol()]);
            
          } else if (!b.getSquareArray()[start.getRow() - 1][start.getCol()].getOccupyingPiece().getColor() == this.getColor()) {

            legal.add(b.getSquareArray()[start.getRow() - 1][start.getCol()]);
            
          }
        }
        
        
        //down and right
        /*
        for (int i = -7; start.getCol() + i <= 7 && start.getRow() + i <= 7; i++) {          
          
          if (start.getCol() + i >= 0 && start.getRow() + i >= 0 && start.getCol() + i <= 7 && 
          start.getRow() + i <= 7 && blocked[0] == false) {

            legal.add(b.getSquareArray()[start.getRow() + i][start.getCol() + i]);
            if (b.getSquareArray()[start.getRow() + i][start.getCol() + i].isOccupied()) {
              blocked[0] = true;
            }

          } 
          if (start.getCol() - i >= 0 && start.getRow() - i >= 0 && start.getCol() - i <= 7 && 
          start.getRow() - i <= 7 && blocked[1] == false) {

            legal.add(b.getSquareArray()[start.getRow() - i][start.getCol() - i]);
            if (b.getSquareArray()[start.getRow() - i][start.getCol() - i].isOccupied()) {
              blocked[1] = true;
            }

          } 
          if (start.getCol() - i >= 0 && start.getRow() + i >= 0 && start.getCol() - i <= 7 && 
          start.getRow() + i <= 7 && blocked[2] == false) {

            legal.add(b.getSquareArray()[start.getRow() + i][start.getCol() - i]);
            if (b.getSquareArray()[start.getRow() + i][start.getCol() - i].isOccupied()) {
              blocked[2] = true;
            }

          } else if (start.getCol() + i >= 0 && start.getRow() - i >= 0 && start.getCol() + i <= 7 && 
          start.getRow() - i <= 7 && blocked[3] == false) {

            legal.add(b.getSquareArray()[start.getRow() - i][start.getCol() + i]);
            if (b.getSquareArray()[start.getRow() - i][start.getCol() + i].isOccupied()) {
              blocked[3] = true;
            }

          }*/

           /*else if (start.getColor() == b.getSquareArray()[start.getRow() - i][start.getCol() + i].getColor()) {
            legal.add(b.getSquareArray()[start.getRow() - i][start.getCol() + i]);

          } */
      








          //down and right
        for (int j = 1; start.getCol() + j <= 7 && start.getRow() + j <= 7; j++) {          
          if (!b.getSquareArray()[start.getRow() + j][start.getCol() + j].isOccupied()) {
              legal.add(b.getSquareArray()[start.getRow() + j][start.getCol() + j]);  
            }
            else if (b.getSquareArray()[start.getRow() + j][start.getCol() + j].getOccupyingPiece().getColor()!= color){
              legal.add(b.getSquareArray()[start.getRow() + j][start.getCol() + j]);
              break;
            }
            else {
              break;
            }
        } 
        //up and left
        for (int j = 1; start.getCol() - j >= 0 && start.getRow() - j >= 0; j++) {          
          if (!b.getSquareArray()[start.getRow() - j][start.getCol() - j].isOccupied()) {
              legal.add(b.getSquareArray()[start.getRow() - j][start.getCol() - j]);  
            }
            else if (b.getSquareArray()[start.getRow() - j][start.getCol() - j].getOccupyingPiece().getColor()!= color){
              legal.add(b.getSquareArray()[start.getRow() - j][start.getCol() - j]);
              break;
            }
            else {
              break;
            }
        } 
        //up and right
        for (int j = 1; start.getCol() - j >= 0 && start.getRow() + j <= 7; j++) {          
          if (!b.getSquareArray()[start.getRow() + j][start.getCol() - j].isOccupied()) {
              legal.add(b.getSquareArray()[start.getRow() + j][start.getCol() - j]);  
            }
            else if (b.getSquareArray()[start.getRow() + j][start.getCol() - j].getOccupyingPiece().getColor()!= color){
              legal.add(b.getSquareArray()[start.getRow() + j][start.getCol() - j]);
              break;
            }
            else {
              break;
            }
        } 
        //down and left
        for (int j = 1; start.getCol() + j <= 7 && start.getRow() - j >= 0; j++) {          
          if (!b.getSquareArray()[start.getRow() - j][start.getCol() + j].isOccupied()) {
              legal.add(b.getSquareArray()[start.getRow() - j][start.getCol() + j]);  
            }
            else if (b.getSquareArray()[start.getRow() - j][start.getCol() + j].getOccupyingPiece().getColor()!= color){
              legal.add(b.getSquareArray()[start.getRow() - j][start.getCol() + j]);
              break;
            }
            else {
              break;
            }
        } 
        return(legal);
    }
}

