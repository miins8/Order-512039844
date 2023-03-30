package Flood_it;

import java.awt.Color;
import javalib.impworld.*;
import javalib.worldimages.*;
import java.util.ArrayList;
import tester.Tester;

public class ExamplesFloodIt {
    public static void main(String[] args) {
        ExamplesFloodIt tests = new ExamplesFloodIt();
        Tester.runReport(tests, false, false);
    }

    //the first number is the size of the board, the second is 
    //the number of colors used in the board (total 14)
	FloodItWorld world1 = new FloodItWorld(30, 6);

	// Test state rendering
	public void testMakeScene(Tester t) {
	    world1.bigBang(world1.size * 20, world1.size * 20, 0.5);
	}
}

class Cell {
    int x;
    int y;
    Color color;
    boolean flooded; // Whether this cell has been "flooded"
    
	Cell left;   // The cell to this one's left
	Cell top;    // The cell immediately above this one
	Cell right;  // The cell to this one's right
	Cell bottom; // The cell immediately below this one

	// Constructor: Create a new Cell with given x/y and color
	public Cell(int xCoord, int yCoord, Color givenColor) {
	    x = xCoord;
	    y = yCoord;
	    color = givenColor;

	    flooded = false;

	    left = null; top = null; right= null ; bottom=null ;
 }
	
	  /**
     * Produce an image depicting ourselves.
     */
	public WorldImage drawCell() {

	return new RectangleImage(20 ,20 ,
			      OutlineMode.SOLID,
			      color);
      }  
}


class FloodItWorld extends World {

    int size;
    int numOfColors;
    ArrayList<Cell> board;

	//Constructor: Create a FloodItWorld object with custom board size and number of colors
	public FloodItWorld(int givenSize, int numColors) {
	    size = givenSize;
	    numOfColors = numColors;

	    // Create an empty board
	    this.board = new ArrayList<>();

               // Fill the (empty) board with Cells of random colors.
	       for (int y = 0; y < size; y++) {
		     for (int x = 0; x < size; x++) { 
		        this.board.add(new Cell(x ,y ,generateRandomColor()));
		  }
	       }

	     linksCells();
	  }

	/**
     * Link each cell in the game to its neighbors in left,right,
     * up, down direction If there is no neighbor(say rightmost column)
     *
     */
	void linksCells() { 
	     for(Cell c : this.board ) {
		 //
		 if(c.x != 0 && c.y != 0 ){
		    c.left   = getCellAt(c.x -1,c.y);
		    c.top    = getCellAt(c.x,c.y-1);  		    
                  }else{  
		     if( c.x==0 &&c.y==0 ){ /*Top left corner case*/
			 continue ;
	             }
	            else if( c.y == 0 ) {/*First Row edge case*/}
	            else{/*first Column edge case*/}				   
		   }
	        }
	  }

	
       /**
      return cell at coordinate x,y 
      If out of bound then will return null
      **/
        Cell getCellAt(int row,int col){
              if(row>=size||col>=size||
                row<0 || col< 0 )
                   return null ;

             return this.board.get(col*size+row);

         }



/**
   Randomly generate one color
*/
      Color generateRandomColor() {
	return new Color[]{
        Color.BLUE,
	Color.RED,
	Color.GREEN, 
        Color.YELLOW,
        Color.MAGENTA,  
        Color.WHITE,   
        Color.PINK,    
   	Color.BLACK,     
   	       
   /* Additional colors */
       Color.ORANGE,
       Color.CYAN,
       Color.DARK_GRAY,
       Color.GRAY,
       Color.LIGHT_GRAY  
        }[(int)(Math.random()* this.numOfColors)];

      }
    

	public WorldScene makeScene() {

	    WorldScene scene = new WorldScene(size * 20 , size*20 ) ;
	    for(Cell c : this.board) {
		  scene.placeImageXY(c.drawCell(),c.x*20+10,c.y*20+10);
           }

	return scene ;

      }


}