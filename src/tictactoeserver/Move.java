/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeserver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author mystore
 */
public class Move {
    private int player;
    private int row;
    private int col;
    public Move(int player, int row, int col) {
        this.player = player;
        this.row = row;
        this.col = col;
    }
    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}


class GameRecorder {
    private List<Move> moves;

    public GameRecorder() {
        moves = new ArrayList<>();
    }

    public void recordMove(int player, int row, int col) {
        moves.add(new Move(player, row, col));
    }

    public void saveToFile(String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(moves, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class GameReplayer {
    private List<Move> moves;
    private int currentMoveIndex;

    public void loadFromFile(String filename) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filename)) {
            moves = gson.fromJson(reader, new TypeToken<List<Move>>(){}.getType());
            currentMoveIndex = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Move getNextMove() {
        if (moves != null && currentMoveIndex < moves.size()) {
            return moves.get(currentMoveIndex++);
        }
        return null;
    }

    public boolean hasMoreMoves() {
        return moves != null && currentMoveIndex < moves.size();
    }
}

//in Server
class Main {
    public static void main(String[] args) {
        GameRecorder recorder = new GameRecorder();

        // Simulate some moves
        recorder.recordMove(1, 0, 0);
        recorder.recordMove(2, 0, 1);
        recorder.recordMove(1, 1, 1);
        recorder.recordMove(2, 1, 0);
        recorder.recordMove(1, 2, 2);

        // Save the recorded moves to a file
        recorder.saveToFile("game_recording.json");

        // Load and replay the moves
        GameReplayer replayer = new GameReplayer();
        replayer.loadFromFile("game_recording.json");

        while (replayer.hasMoreMoves()) {
            Move move = replayer.getNextMove();
            System.out.println("Player " + move.getPlayer() + " moved to (" + move.getRow() + ", " + move.getCol() + ")");
            // Here, you would update the game board with the move
        }
    }
}       