
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

//You will be implmenting a part of a function and a whole function in this document. Please follow the directions for the 
//suggested order of completion that should make testing easier.
@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener, MouseMotionListener {
    // Resource location constants for piece images
    private static final String RESOURCES_WBISHOP_PNG = "wbishop.png";
    private static final String RESOURCES_BBISHOP_PNG = "bbishop.png";
    private static final String RESOURCES_WKNIGHT_PNG = "wknight.png";
    private static final String RESOURCES_BKNIGHT_PNG = "bknight.png";
    private static final String RESOURCES_WROOK_PNG = "wrook.png";
    private static final String RESOURCES_BROOK_PNG = "brook.png";
    private static final String RESOURCES_WKING_PNG = "wking.png";
    private static final String RESOURCES_BKING_PNG = "bking.png";
    private static final String RESOURCES_BQUEEN_PNG = "bqueen.png";
    private static final String RESOURCES_WQUEEN_PNG = "wqueen.png";
    private static final String RESOURCES_WPAWN_PNG = "wpawn.png";
    private static final String RESOURCES_BPAWN_PNG = "bpawn.png";
    private static final String RESOURCES_BITALIANKRATOS_PNG = "bItalianKratos.png";
    private static final String RESOURCES_WITALIANKRATOS_PNG = "wItalianKratos.png";
    private static final String RESOURCES_BPOPE_PNG = "bpope.png";
    private static final String RESOURCES_WPOPE_PNG = "wpope.png";
    private static final String RESOURCES_BMONK_PNG = "bmonk.png";
    private static final String RESOURCES_WMONK_PNG = "wmonk.png";
    private static final String RESOURCES_WHELICOPTER_PNG = "whelicopter.png";
    private static final String RESOURCES_BHELICOPTER_PNG = "bhelicopter.png";

    // Logical and graphical representations of board
    private final Square[][] board;
    private final GameWindow g;

    // contains true if it's white's turn.
    private boolean whiteTurn;

    // if the player is currently dragging a piece this variable contains it.
    private Piece currPiece;
    private Square fromMoveSquare;

    // used to keep track of the x/y coordinates of the mouse.
    private int currX;
    private int currY;

    // precondition: g is not null
    // postcondition: An 8x8 chess board made up of squares is created
    public Board(GameWindow g) {
        this.g = g;
        board = new Square[8][8];
        setLayout(new GridLayout(8, 8, 0, 0));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        // TO BE IMPLEMENTED FIRST

        // for (.....)
        // populate the board with squares here. Note that the board is composed of 64
        // squares alternating from
        // white to black.
        boolean white = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    white = true;
                } else {
                    white = false;
                }
                board[i][j] = new Square(this, white, i, j);
                this.add(board[i][j]);
            }
        }

        initializePieces();

        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));

        whiteTurn = true;

    }

    // precondition: The board has been created with squares
    // postcondition: black pieces pieces are at the top of the board and white
    // pieces pieces are put at the bottom of the board
    private void initializePieces() {

        // Loops through every square
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                // Puts the black pieces on the first row
                if (x == 0 && (y == 1 || y == 6)) {
                    board[x][y].put(new ItalianKratos(false, RESOURCES_BITALIANKRATOS_PNG));
                }
                // Puts the white pieces on the last row
                else if (x == 7 && (y == 1 || y == 6)) {
                    board[x][y].put(new ItalianKratos(true, RESOURCES_WITALIANKRATOS_PNG));
                }
                if (x == 0 && y == 4) {
                    board[x][y].put(new King(false, RESOURCES_BKING_PNG));
                } else if (x == 7 && y == 4) {
                    board[x][y].put(new King(true, RESOURCES_WKING_PNG));
                }
                if (x == 0 && y == 3) {
                    board[x][y].put(new Queen(false, RESOURCES_BQUEEN_PNG));
                } else if (x == 7 && y == 3) {
                    board[x][y].put(new Queen(true, RESOURCES_WQUEEN_PNG));
                }
                if (x == 0 && (y == 5 || y == 2)) {
                    board[x][y].put(new Pope(false, RESOURCES_BPOPE_PNG));
                } else if (x == 7 && (y == 5 || y == 2)) {
                    board[x][y].put(new Pope(true, RESOURCES_WPOPE_PNG));
                }
                if (x == 0 && (y == 0 || y == 7)) {
                    board[x][y].put(new Helicopter(false, RESOURCES_BHELICOPTER_PNG));
                } else if (x == 7 && (y == 0 || y == 7)) {
                    board[x][y].put(new Helicopter(true, RESOURCES_WHELICOPTER_PNG));
                }
                if (x == 1) {
                    board[x][y].put(new Monk(false, RESOURCES_BMONK_PNG));
                } else if (x == 6) {
                    board[x][y].put(new Monk(true, RESOURCES_WMONK_PNG));
                }
            }
        }

    }

    public Square[][] getSquareArray() {
        return this.board;
    }

    public boolean getTurn() {
        return whiteTurn;
    }

    public void setCurrPiece(Piece p) {
        this.currPiece = p;
    }

    public Piece getCurrPiece() {
        return this.currPiece;
    }

    @Override
    public void paintComponent(Graphics g) {

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square sq = board[x][y];
                if (sq == fromMoveSquare)
                    sq.setBorder(BorderFactory.createLineBorder(Color.blue));
                sq.paintComponent(g);

            }
        }
        if (currPiece != null) {
            if ((currPiece.getColor() && whiteTurn)
                    || (!currPiece.getColor() && !whiteTurn)) {
                final Image img = currPiece.getImage();
                g.drawImage(img, currX, currY, null);
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        currX = e.getX();
        currY = e.getY();

        Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

        if (sq.isOccupied()) {
            currPiece = sq.getOccupyingPiece();
            fromMoveSquare = sq;
            System.out.println(currPiece.toString());
            if (!currPiece.getColor() && whiteTurn)
                return;
            if (currPiece.getColor() && !whiteTurn)
                return;
            sq.setDisplay(false);
        }
        repaint();
    }

    // precondition: The board has been properly created; e isn't null
    // postcondition: currPiece is moved to whatever square the mouse dragged it to
    // if the square is a legal move; the game is updated
    @Override
    public void mouseReleased(MouseEvent e) {

        if (currPiece != null) {

            Square endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
            Piece previousOccupier;

            // if endSquare is a legal move then move currPiece to endSquare, and remove it
            // from fromMoveSquare, and change the turn
            if (whiteTurn == currPiece.getColor()) {
                if (currPiece.getLegalMoves(this, fromMoveSquare).contains(endSquare)) {
                    // Saves the piece in endSquare if there is one
                    previousOccupier = endSquare.getOccupyingPiece();

                    endSquare.put(currPiece);
                    fromMoveSquare.removePiece();
                    whiteTurn = !whiteTurn;

                    // if the currPiece color is in check then only let the color's pieces move if
                    // it gets them out of check
                    // Also doesn't let a piece to put itself in check
                    // Will undo a capture if that capture puts the king in check
                    if (isInCheck(currPiece.getColor())) {
                        fromMoveSquare.put(currPiece);
                        endSquare.removePiece();
                        if (previousOccupier != null) {
                            endSquare.put(previousOccupier);
                        }
                        whiteTurn = !whiteTurn;
                    }
                }
            }

            // Allows Monks to capture a piece of the opposite color that is in front of
            // them. Code made by the Monk creator.
            if (currPiece instanceof Monk) {

                if (currPiece.getColor() == true && endSquare != null && endSquare.getOccupyingPiece() != null
                        && endSquare.getOccupyingPiece().getColor() == false
                        && currPiece.getControlledSquares(board, fromMoveSquare).contains(endSquare)) {
                    endSquare.put(currPiece);
                    fromMoveSquare.removePiece();
                    whiteTurn = !whiteTurn;
                }

                if (currPiece.getColor() == false && endSquare != null && endSquare.getOccupyingPiece() != null
                        && endSquare.getOccupyingPiece().getColor() == true
                        && currPiece.getControlledSquares(board, fromMoveSquare).contains(endSquare)) {
                    endSquare.put(currPiece);
                    fromMoveSquare.removePiece();
                    whiteTurn = !whiteTurn;
                }

            }

            // Removes the highlight
            for (Square[] row : board) {
                for (Square s : row) {
                    s.setBorder(null);
                }
            }

            fromMoveSquare.setDisplay(true);
            currPiece = null;
            repaint();
        }

    }

    // precondition - the board is initialized and contains a king of either color.
    // The boolean kingColor corresponds to the color of the king we wish to know
    // the status of.
    // postcondition - returns true of the king is in check and false otherwise.
    public boolean isInCheck(boolean kingColor) {
        Square KingsLocation = null;

        // Loop through every square to find the king that is the kingColor
        for (Square[] row : board) {
            for (Square s : row) {
                if (s.getOccupyingPiece() instanceof King && s.getOccupyingPiece().getColor() == kingColor) {
                    KingsLocation = s;
                    // once the king is found, break
                    break;
                }
            }
        }

        // loop through every square to find squares that have a piece on them that are
        // not the king's color
        // check each piece's controlled squares arrayList to see if it contains the
        // king's square
        // If it does return true; else return false
        for (Square[] row : board) {
            for (Square s : row) {
                if (s.getOccupyingPiece() != null) {
                    if (s.getOccupyingPiece().getColor() != kingColor) {
                        if (s.getOccupyingPiece().getControlledSquares(board, s).contains(KingsLocation)) {
                            System.out.println(s.getOccupyingPiece().toString() + " is putting "
                                    + KingsLocation.getOccupyingPiece().toString() + " in check");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currX = e.getX() - 24;
        currY = e.getY() - 24;

        if (currPiece != null) {
            for (Square s : currPiece.getLegalMoves(this, fromMoveSquare)) {
                s.setBorder(BorderFactory.createLineBorder(Color.red));
            }

            // for (Square s : currPiece.getControlledSquares(board, fromMoveSquare)) {
            // s.setBorder(BorderFactory.createLineBorder(Color.yellow));
            // }
        }

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}