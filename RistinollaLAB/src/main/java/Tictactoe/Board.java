/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tictactoe;

public class Board {
    private String board;
    private Board previous;
    private Board next;
    
    public Board(String board, Board previous, Board next) {
        this.board = board;
        this.previous = previous;
        this.next = next;
    }
}

