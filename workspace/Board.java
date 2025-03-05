
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
                    board[x][y].put(new Piece(false, RESOURCES_BITALIANKRATOS_PNG));
                }
                // Puts the white pieces on the last row
                else if (x == 7 && (y == 1 || y == 6)) {
                    board[x][y].put(new Piece(true, RESOURCES_WITALIANKRATOS_PNG));
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
            if (!currPiece.getColor() && whiteTurn)
                return;
            if (currPiece.getColor() && !whiteTurn)
                return;
            sq.setDisplay(false);
        }
        repaint();
    }

    // precondition: The board has been properly created; e isn't null
    // postcondition: currPiece is moved to whatever square the mouse dragged it to if the square is a legal move; the game is updated
    @Override
    public void mouseReleased(MouseEvent e) {

        if (currPiece != null) {

            Square endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
            // if endSquare is a legal move then move currPiece to endSquare, and remove it
            // from fromMoveSquare, and change the turn
            if (currPiece.getLegalMoves(this, fromMoveSquare).contains(endSquare)) {
                endSquare.put(currPiece);
                fromMoveSquare.removePiece();
                whiteTurn = !whiteTurn;
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