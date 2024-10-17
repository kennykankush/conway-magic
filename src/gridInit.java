import java.util.Arrays;
import java.util.List;

public class gridInit {

    String fileName;
    private int objRow, objCol, objStartX, objStartY;
    boolean[][] objGrid;
    boolean[][] objGridNext;
    int counter;

    public void setupGrid(String fileName){
        int[] config_grid = golLoader.coordinateFetcher(fileName);
        List<String[]> config_cell = golLoader.cellFetcher(fileName);

        int offsetX = 1;
        int offsetY = 1;

        this.fileName = fileName;
        this.objRow = config_grid[0];
        this.objCol = config_grid[1];
        this.objStartX = config_grid[2];
        this.objStartY = config_grid[3];

        objGrid = new boolean[objRow][objCol];

        for(int i = 0; i < config_cell.size(); i++){
            String [] cellRow = config_cell.get(i);

                for (int j = 0; j < cellRow.length; j++){
                    int gridX = objStartX + i;
                    int gridY = objStartY + j;

                    if (cellRow[j].equals("*")) {
                        objGrid[gridX][gridY] = true;

                    }

                }
            
        }
    }
    public void printGrid(){
        for(int i = 0; i < objGrid.length; i++){ 
            boolean[] line = objGrid[i];
            System.out.println(Arrays.toString(line));
        
        }
    }
    public void howManyAreAroundMeAndShouldIStayOrGo(){

        objGridNext = new boolean[objRow][objCol];

        for (int row = 0; row < objGrid.length; row++){                 //0
            for (int col = 0; col < objGrid[row].length; col++){        //0
                //  System.out.println("Accessing: row " + row + ",col " + col); 
                    int howManyAreAroundMe = 0;

                    for (int searchX = -1; searchX <= 1; searchX++){
                        for (int searchY = -1; searchY <= 1; searchY++){
                            int neighbourX = row + searchX;   //
                            int neighbourY= col + searchY;    //

                            if (((neighbourX < 0) || (neighbourX > objGrid.length - 1)) || ((neighbourY < 0) || (neighbourY > objGrid[row].length - 1))){
                                continue;
                            } else {

                                if (objGrid[neighbourX][neighbourY] == false || ((neighbourX == row) && neighbourY == col)){
                                    continue;

                                } else {
                                    howManyAreAroundMe++;         
                                    // System.out.println("Found one at " + "row " + neighbourX + ", col" + neighbourY) ; 
                                    // System.out.println("Adding to tally")     
                                }

                            }
                            
                        }

                    }

                    if (objGrid[row][col] == true && (howManyAreAroundMe == 2 || howManyAreAroundMe == 3)){
                        // System.out.println("I will stay alive" + "I am at row"+ row + col);
                        objGridNext[row][col] = true;
                    }

                    if (objGrid[row][col] == false && howManyAreAroundMe == 3){
                        // System.out.println("I will resurrect" + "I am at row"+ row + col);
                        objGridNext[row][col] = true;
                    }

                    if (objGrid[row][col] == true && ((howManyAreAroundMe <= 1) || (howManyAreAroundMe > 4))){
                        // System.out.println("Die" + "I am at row"+ row + col);
                        objGridNext[row][col] = false;

                    }


                }
            }

        objGrid = objGridNext;
   
        }
    public void play(int generation, String fileName){

        setupGrid(fileName);
        printGrid();

        for(int i = 1; i < generation; i++){
            System.out.println("Frame: " + i);
            System.out.println("====================");
            howManyAreAroundMeAndShouldIStayOrGo();
            printGrid();

        }

    } 


}


    



