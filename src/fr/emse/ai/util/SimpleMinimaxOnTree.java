package fr.emse.ai.util;

public class SimpleMinimaxOnTree {

    public static int minimax(SimpleTwoPlyGameTree<Integer> tree) {
        if ((tree.isLeaf()))
            return tree.getValue();
        else {
            int minimax = tree.getValue();
            if (tree.isMax()) {
                for (SimpleTwoPlyGameTree<Integer> child : tree.getChildren()) {
                    minimax = Math.max(minimax(child), minimax);
                }
                tree.setValue((Integer) minimax);
                return minimax;
            } else {
                for (SimpleTwoPlyGameTree<Integer> child : tree.getChildren()) {
                    minimax = Math.min(minimax(child), minimax);
                }
                tree.setValue((Integer) minimax);
                return minimax;
            }
        }

    }
}
