public class ThreadOperation extends Thread
{
    //instance variables
    private int[][] A;
    private int[][] B;
    private int[][] matrixResult;
    private String quadrant;

    //constructor
    ThreadOperation(int[][] A, int[][] B, int[][] matrixResult, String quadrant)
    {
        //set the instance variables to the arguments added to ThreadOperation object
        this.A = A;
        this.B = B;
        this.matrixResult = matrixResult;
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
            //if there are an even number of rows, odd number of indexes
            if (rows % 2 == 0)
            {
                rowEnd = (rows/2) - 1;
            }
            //if there are an odd number of rows, even number of indexes
            else
            {
                rowEnd = rows/2;
            }

            //if there are an even number of columns, odd number of indexes
            if (columns % 2 == 0)
            {
                colEnd = (columns/2) - 1;
            }
            //if there are an odd number of columns, even number of indexes
            else
            {
                colEnd = columns/2;
            }
        }
        //if 2nd quad change row end and col start/end
        if (quadrant == "Second Quadrant")
        {
            //columns end at the index of the last column
            colEnd = columns - 1;

            //if there are an even number of rows, odd number of indexes
            if (rows % 2 == 0)
            {
                rowEnd = (rows/2) - 1;
            }
            //if there are an odd number of rows, even number of indexes
            else
            {
                rowEnd = rows/2;
            }

            //if there are an even number of columns, odd number of indexes
            if (columns % 2 == 0)
            {
                colStart = (columns/2) - 1;
            }
            //if there are an odd number of columns, even number of indexes
            else
            {
                colStart = columns;
            }
        }
        //if 3rd quad change row start/end and col end
        if(quadrant == "Third Quadrant")
        {
            //rows end  at the index of the last row
            rowEnd = rows - 1;

            //if there are an even number of rows, odd number of indexes
            if (rows % 2 == 0)
            {
                rowStart = rows/2;
            }
            //if there are an odd number of rows, even number of indexes
            else
            {
                rowStart = (rows/2) + 1;
            }

            //if there are an even number of columns, odd number of indexes
            if (columns % 2 == 0)
            {
                colEnd = (columns/2) - 1;
            }
            //if there are an odd number of columns, even number of indexes
            else
            {
                colEnd = columns/2;
            }
        }
        //if 4th quad change row/col end/start
        if(quadrant == "Fourth Quadrant")
        {
            //rows and columns  end at the index of their last value
            rowEnd = rows - 1;
            colEnd = columns - 1;

            //if there are an even number of rows, odd number of indexes
            if (rows % 2 == 0)
            {
                rowStart = rows/2;
            }
            //if there are an odd number of rows, even number of indexes
            else
            {
                rowStart = (rows/2) + 1;
            }

            //if there are an even number of columns, odd number of indexes
            if (columns % 2 == 0)
            {
                colStart = columns/2;
            }
            //if there are an odd number of columns, even number of indexes
            else
            {
                colStart = (columns/2) + 1;
            }
        }

        //store the values for the row/col start/end into array and return it.
        int[] indexes = {rowStart, rowEnd, colStart, colEnd};
        return indexes;
    }   //end getQuadrantIndexes

    @Override
    public void run()
    {
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
                matrixResult[r][c]= A[r][c] + B[r][c];
            }
        }
    }  //end run
}   //end class
