package fr.emse.ai.adversarial.tictactoe;

import fr.emse.ai.adversarial.Game;

import java.util.ArrayList;
import java.util.List;

public class TicGame implements Game<List<Integer>, Integer, Integer> {

    public final static Integer[] players = {1, 2};  // 1 for human and 2 for machine
    public final static List<Integer> initialState = new ArrayList<Integer>();

    public TicGame() {
        initialState.add(1);  // The first number in the List signifies which player's turn
        for (int i = 1; i <= 9; i++) {
            initialState.add(0);   // Initial all 9 spaces to be 0
        }
    }

    /* Naming the 9 spaces
        1 2 3
        4 5 6
        7 8 9
    */

    public void printGameResult(List<Integer> state) {
        for (int i = 1; i <= 3; i++) {
            System.out.print(state.get(i));
        }

        System.out.print("\n");

        for (int i = 4; i <= 6; i++) {
            System.out.print(state.get(i));
        }

        System.out.print("\n");

        for (int i = 7; i <= 9; i++) {
            System.out.print(state.get(i));
        }

        System.out.print("\n");

    }

    @Override
    public List<Integer> getInitialState() {
        return initialState;
    }

    @Override
    public Integer[] getPlayers() {
        return players;
    }

    @Override
    public Integer getPlayer(List<Integer> state) {
        return state.get(0);
    }

    @Override
    public List<Integer> getActions(List<Integer> state) {
        ArrayList<Integer> actions = new ArrayList<Integer>();
        for (int i = 1; i <= 9; i++)  {
            if (state.get(i) == 0) {
                actions.add(i);
            }
        }
        return actions;
    }

    @Override
    public List<Integer> getResult(List<Integer> state, Integer action) {
        ArrayList<Integer> newState = new ArrayList<Integer>(10);

        newState.add(state.get(0));  // The first number in the List signifies which player's turn
        for (int i = 1; i <= 9; i++) {
            newState.add(0);   // Initial all 9 spaces to be 0
        }
        for (int i = 1; i <= 9; i++) {              // Copy the state into a new state
            if (state.get(i) !=0) {
                newState.set(i, state.get(i));
            }
        }

        if (state.get(0) == 2) {                    // If it is machine's turn
            newState.set(0,1);                      // Mark next round as human's turn
            newState.set(action,2);
        }
        else if (state.get(0) == 1) {               // If it is human's turn
            newState.set(0,2);                      // Mark next round as machine's turn
            newState.set(action,1);
        }
        return newState;
    }

    @Override
    public boolean isTerminal(List<Integer> state) {
        if (!state.contains(0)) {                       // If all 9 spaces are filled
            return true;
        }                                               // Seems complicated, should look for a way to simplify
        else if (((state.get(1) * state.get(2) * state.get(3)) == 1) || ((state.get(1) * state.get(2) * state.get(3)) == 8)
                || ((state.get(4) * state.get(5) * state.get(6)) == 1) || ((state.get(4) * state.get(5) * state.get(6)) == 8)
                || ((state.get(7) * state.get(8) * state.get(9)) == 1) || ((state.get(7) * state.get(8) * state.get(9)) == 8)
                || ((state.get(1) * state.get(4) * state.get(7)) == 1) || ((state.get(1) * state.get(4) * state.get(7)) == 8)
                || ((state.get(2) * state.get(5) * state.get(8)) == 1) || ((state.get(2) * state.get(5) * state.get(8)) == 8)
                || ((state.get(3) * state.get(6) * state.get(9)) == 1) || ((state.get(3) * state.get(6) * state.get(9)) == 8)
                || ((state.get(1) * state.get(5) * state.get(9)) == 1) || ((state.get(1) * state.get(5) * state.get(9)) == 8)
                || ((state.get(3) * state.get(5) * state.get(7)) == 1) || ((state.get(3) * state.get(5) * state.get(7)) == 8))
        { return true; }
        else { return false; }
    }

    @Override
    public double getUtility(List<Integer> state, Integer player) {
        if (((state.get(1) * state.get(2) * state.get(3)) == 8)
                || ((state.get(4) * state.get(5) * state.get(6)) == 8)
                || ((state.get(7) * state.get(8) * state.get(9)) == 8)
                || ((state.get(1) * state.get(4) * state.get(7)) == 8)
                || ((state.get(2) * state.get(5) * state.get(8)) == 8)
                || ((state.get(3) * state.get(6) * state.get(9)) == 8)
                || ((state.get(1) * state.get(5) * state.get(9)) == 8)
                || ((state.get(3) * state.get(5) * state.get(7)) == 8)) {
            return 1;  // Machine wins
        }
        else if (((state.get(1) * state.get(2) * state.get(3)) == 1)
                || ((state.get(4) * state.get(5) * state.get(6)) == 1)
                || ((state.get(7) * state.get(8) * state.get(9)) == 1)
                || ((state.get(1) * state.get(4) * state.get(7)) == 1)
                || ((state.get(2) * state.get(5) * state.get(8)) == 1)
                || ((state.get(3) * state.get(6) * state.get(9)) == 1)
                || ((state.get(1) * state.get(5) * state.get(9)) == 1)
                || ((state.get(3) * state.get(5) * state.get(7)) == 1)) {
            return -1;  // Human wins
        }
        else return 0;  // A draw
    }

}
