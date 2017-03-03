/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.util.*;

/**
 *
 * @author Clarity
 */
public class MineSweeper {
    public static int choseX;
    public static int choseY;
    public static int findX;
    public static int findY;
    public static int foundMines;
    public static int numMines = 10;
      public static final String ANSI_RESET = "\u001B[0m";
public static final String ANSI_BLACK = "\u001B[30m";
public static final String ANSI_RED = "\u001B[31m";
public static final String ANSI_GREEN = "\u001B[32m";
public static final String ANSI_YELLOW = "\u001B[33m";
public static final String ANSI_BLUE = "\u001B[34m";
public static final String ANSI_PURPLE = "\u001B[35m";
public static final String ANSI_CYAN = "\u001B[36m";
public static final String ANSI_WHITE = "\u001B[37m";
    public static int numFlags = 10;
    static Node a0, a1, a2, a3, a4, a5, a6, a7, a8;
    static Node b0, b1, b2, b3, b4, b5, b6, b7, b8;
    static Node c0, c1, c2, c3, c4, c5, c6, c7, c8;
    static Node d0, d1, d2, d3, d4, d5, d6, d7, d8;
    static Node e0, e1, e2, e3, e4, e5, e6, e7, e8;
    static Node f0, f1, f2, f3, f4, f5, f6, f7, f8;
    static Node g0, g1, g2, g3, g4, g5, g6, g7, g8;
    static Node h0, h1, h2, h3, h4, h5, h6, h7, h8;
    static Node i0, i1, i2, i3, i4, i5, i6, i7, i8;
    public static int player;
    static boolean play = true;
    static Node[][] board = {
        {a0, a1, a2, a3, a4, a5, a6, a7, a8},
        {b0, b1, b2, b3, b4, b5, b6, b7, b8},
        {c0, c1, c2, c3, c4, c5, c6, c7, c8},
        {d0, d1, d2, d3, d4, d5, d6, d7, d8},
        {e0, e1, e2, e3, e4, e5, e6, e7, e8},
        {f0, f1, f2, f3, f4, f5, f6, f7, f8},
        {g0, g1, g2, g3, g4, g5, g6, g7, g8},
        {h0, h1, h2, h3, h4, h5, h6, h7, h8},
        {i0, i1, i2, i3, i4, i5, i6, i7, i8}
    };
    public static final int h = 9;
    public static final int w = 9;

    public static void main(String[] args) {
        // TODO code application logic here
        
        init();
        print();
        mines();
        while (play) {
            input();
        }
    }

    public static void init() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                board[i][j] = new Node();
            }
        }
        for (int i = 0; i < h; i++) {//height
            for (int j = 0; j < w; j++) {//width
                boolean up = false, down = false, left = false, right = false;
                if (i < h - 1) {//if not bottom
                    down = true;
                    board[i][j].setD(board[i + 1][j]);
                }
                if (j < w - 1) {//if not right
                    right = true;
                    board[i][j].setR(board[i][j + 1]);

                }
                if (i != 0) {//if not top
                    up = true;
                    board[i][j].setU(board[i - 1][j]);

                }
                if (j != 0) {//if not left
                    left = true;
                    board[i][j].setL(board[i][j - 1]);
                }

                if (up && left) {
                    board[i][j].setUl(board[i - 1][j - 1]);
                }
                if (up && right) {
                    board[i][j].setUr(board[i - 1][j + 1]);
                }
                if (down && left) {
                    board[i][j].setDl(board[i + 1][j - 1]);
                }
                if (down && right) {
                    board[i][j].setDr(board[i + 1][j + 1]);
                }

            }
        }
        
    }
    public static void input(){
        Scanner sc = new Scanner(System.in);
        String input;
        System.out.print("Flag (y or n): ");
        input = sc.nextLine().toLowerCase();
        if(input.equals("y")){
            flag();
        }else{
        System.out.print("x: ");
        input = sc.nextLine().toLowerCase();
        if(input.equals("")){
            System.out.println("please enter a number");
            input();
        }else{
       choseX = Integer.parseInt(input);
        if (choseX<=0 || choseX > w){
            System.out.println("invalid x number!");
            input();
        }else{
        System.out.print("y: ");
        input = sc.nextLine().toLowerCase();
        choseY = Integer.parseInt(input);
        if (choseY<=0 || choseY > h){
            System.out.println("invalid y number!");
            input();
        }else{
            uncover();
            print();
            winCheck();
        }
        }
        }
    }
    }
    public static void flag(){
        Scanner sc = new Scanner(System.in);
        String input;
        if(numFlags > 0){
            System.out.println("Number of Flags left: "+numFlags);
        System.out.print("x: ");
        input = sc.nextLine().toLowerCase();
        if(input.equals("")){
            System.out.println("please enter a number");
            flag();
        }else{
       choseX = Integer.parseInt(input);
        if (choseX<=0 || choseX > w){
            System.out.println("invalid x number!");
            flag();
        }else{
        System.out.print("y: ");
        input = sc.nextLine().toLowerCase();
        choseY = Integer.parseInt(input);
        if (choseY<=0 || choseY > h){
            System.out.println("invalid y number!");
            flag();
        }else{
            if(board[choseY-1][choseX-1].flagged == true){
                
            }else{
            numFlags--;
            }
            
            board[choseY-1][choseX-1].flagged = true;
            if(board[choseY-1][choseX-1].v ==10){
                foundMines++;
            }
            print();
        }
        }
        }
    }else{
            System.out.println("NOT ENOUGH FLAGS");
            
        }
    }
    public static void uncover(){
        
        
        if(board[choseY-1][choseX-1].v == 10){
            System.out.println("YOU LOSE");
            play = false;
        }
        else if(board[choseY-1][choseX-1].v == 0){
            findZero(choseY-1, choseX-1);
        }
        board[choseY-1][choseX-1].hidden = false;
    }
    private static void findZero(int y, int x){
       if (x < 0 || x > 8 || y < 0 || y > 8){ 
       }
        else{
            if (board[y][x].v == 0 && board[y][x].hidden){
                board[y][x].hidden = false;
                board[y][x].flagged = false;
            findZero(y+1,x);
            findZero(y+1,x+1);
            findZero(y+1,x-1);
            findZero(y,x+1);
            findZero(y,x-1);
            findZero(y-1,x);
            findZero(y-1,x+1);
            findZero(y-1,x-1);
            if (board[y][x].u != null) {
            board[y][x].u.hidden = false;
            }
            if (board[y][x].ul != null) {
            board[y][x].ul.hidden = false;
            }
            if (board[y][x].ur != null) {
            board[y][x].ur.hidden = false;
            }
            if (board[y][x].d != null) {
            board[y][x].d.hidden = false;
            }
            if (board[y][x].dl != null) {
            board[y][x].dl.hidden = false;
            }
            if (board[y][x].dr != null) {
            board[y][x].dr.hidden = false;
            }
            if (board[y][x].r != null) {
            board[y][x].r.hidden = false;
            }
            if (board[y][x].l != null) {
            board[y][x].l.hidden = false;
            }
            }
            
        }
        
        
    }
    public static void mines() {
        Random rand = new Random();
        for (int i = 0; i < numMines;) {
            int tempX = rand.nextInt(w - 1) + 1;
            int tempY = rand.nextInt(h - 1) + 1;
            if(board[tempY][tempX].v != 10){
            board[tempY][tempX].v = 10;
            numbers(tempX, tempY);
            i++;
            }
        }
    }
    public static void winCheck(){
       if(foundMines >= numMines){
           play = false;
           System.out.println("YOU WIN");
       }
    }
    public static void numbers(int x, int y) {
        if (board[y][x].u != null && board[y][x].u.v != 10) {
            board[y][x].u.v++;
        }
        if (board[y][x].d != null && board[y][x].d.v != 10) {
            board[y][x].d.v++;
        }
        if (board[y][x].l != null && board[y][x].l.v != 10) {
            board[y][x].l.v++;
        }
        if (board[y][x].r != null && board[y][x].r.v != 10) {
            board[y][x].r.v++;
        }
        if (board[y][x].ul != null && board[y][x].ul.v != 10) {
            board[y][x].ul.v++;
        }
        if (board[y][x].ur != null && board[y][x].ur.v != 10) {
            board[y][x].ur.v++;
        }
        if (board[y][x].dr != null && board[y][x].dr.v != 10) {
            board[y][x].dr.v++;
        }
        if (board[y][x].dl != null && board[y][x].dl.v != 10) {
            board[y][x].dl.v++;
        }

    }

    public static void print() {
        System.out.print("   1 2 3 4 5 6 7 8 9");
        System.out.println("");
        for (int i = 0; i < h; i++) {
            System.out.print(i+1 +" |"); 
            for (int j = 0; j < w; j++) {
                if(board[i][j].flagged==true){
                     
                    System.out.print("▷ ");
                    }else{
                if(!board[i][j].hidden){
                if (board[i][j].v == 10) {
                    System.out.print("m ");
                }
                else if (board[i][j].v == 0){
                    System.out.print("  ");
                }else {
                    System.out.print(board[i][j].v + " ");
                }
                
                }
                else{
                    
                    System.out.print("◼ ");
                }
                }
                 
            }
            System.out.println();
        }
        System.out.println();
    }
}
