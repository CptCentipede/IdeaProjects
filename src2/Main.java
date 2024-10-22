/*
Name: Miles Aether
Date: 10/24/2024
Class: CSCI 2251
Purpose: To finish an example of allowing multiple parts of a program to run at the same time
*/

// look at 048_simplified_threads_analogy_to_lab3

public class Main
{
    public static void main(String args[])
    {
        //Create new instances of ThreadDemo:
        ThreadDemo T1 = ___________
        ThreadDemo T2 = ___________

        //Start up both ThreadDemo objects:
        T1___________;
        T2___________;

        //Wait on the threads to finish.
        try{
            T1___________;
            T2___________;
        }catch(InterruptedException e){
            System.out.println("Interrupted");
        }

        System.out.println("\nFINISHED\n");
    }
}