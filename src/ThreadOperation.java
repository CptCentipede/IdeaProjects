public class ThreadOperation extends Thread
{
    //instance variables
    private int[][] A;
    private int[][] B;
    private String quadrant;

    //constructor
    ThreadOperation(int[][] A, int[][] B, String quadrant)
    {
        //set the instance variables to the arguments added to ThreadOperation object
        this.A = A;
        this.B = B;
        this.quadrant = quadrant;
    }  //end constructor

    //method that finds the quadrants to be added together
    public static int[] getQuadrantIndexes(int rows, int columns, String quadrant)
    {
        //create variables for the start/end of rows/columns
        int rowStart = 0;
        int rowEnd = 0;
        int colStart = 0;
        int colEnd = 0;

        //if it is the 1st quad change the end of rows/cols
        if(quadrant == "First Quadrant")
        {
            rowEnd = rows/2;
            colEnd = columns/2;
        }
        //if 2nd quad change row end and col start/end
        if (quadrant == "Second Quadrant")
        {
            rowEnd = rows/2;
            colStart = (columns/2)+1;
            colEnd = columns;
        }
        //if 3rd quad change row start/end and col end
        if(quadrant == "Third Quadrant")
        {
            rowStart = (rows/2)+1;
            rowEnd = rows;
            colEnd = columns/2;
        }
        //if 4th quad change row/col end/start
        if(quadrant == "Fourth Quadrant")
        {
            rowStart = (rows/2)+1;
            rowEnd = rows;
            colStart = (columns/2)+1;
            colEnd = columns;
        }

        //store the values for the row/col start/end into array and return it.
        int[] indexes = {rowStart, rowEnd, colStart, colEnd};
        return indexes;
    }   //end getQuadrantIndexes

    @Override
    public void run()
    {
        //C belongs in main, but I am unsure how to store the data from each thread into that 2darray
        int[][] C = new int[4][6];

        //get the indexes for the beginnings and ends of the rows and columns
        int[] indexes = getQuadrantIndexes(A.length, A[0].length, quadrant);

        //create variables using data from indexes for row/column start/finish
        int rowStart = indexes[0];
        int rowEnd = indexes[1];
        int colStart = indexes[2];
        int colEnd = indexes[3];

        //loop through the rows and columns
        for(int r=rowStart;  r<rowEnd;  r++)
        {
            for(int c=colStart; c<colEnd; c++)
            {
                //set the given index of C to the addition of A and B
                C[r][c]= A[r][c] + B[r][c];
            }

            //not sure if this entire method should stay in main, but to print something
            //this has to be put here
            print2dArray(C);
        }
    }  //end run

    //method that prints out the 2D array
    public static void print2dArray(int[][] matrix)
    {
        //loop through the rows
        for(int i=0; i< matrix.length; i++)
        {
            //loop through the columns
            for (int j = 0; j<matrix[i].length; j++)
            {
                //print the value
                System.out.printf("%d ", matrix[i][j]);
            }   //end for loop columns
            //print a line, so the next row can print below
            System.out.println();
        }   //end for loop rows
    }  //end print2dArray
}   //end class
