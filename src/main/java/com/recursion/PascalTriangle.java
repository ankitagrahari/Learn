package com.recursion;


/**
 *            1 2 3 4 5 6 7 8 9 10 11 12 13
 * 1          1
 * 2          1 1
 * 3          1 2 1
 * 4          1 3 3 1
 * 5          1 4 6 4 1
 * 6          1 5 10 10 5 1
 *            1 6 15 20 15 6 1
 *            1 7 21 35 35 21 7 1
 *            1 8 28 56 70 56 28 8 1
 *            1 9 36 84 126 126 84 36 9 1
 */
public class PascalTriangle {

    public int pascalTriangle(int row, int col){

        //Base case
        if(col==1 || row==col)
            return 1;

        //Recurrence Relation
        return pascalTriangle(row-1, col-1) + pascalTriangle(row-1, col);
    }

    public static void main(String[] args) {
        PascalTriangle obj = new PascalTriangle();
        System.out.println(obj.pascalTriangle(5, 3));
    }

}
