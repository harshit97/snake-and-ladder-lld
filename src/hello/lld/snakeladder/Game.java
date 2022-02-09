package hello.lld.snakeladder;

import hello.lld.snakeladder.model.Board;
import hello.lld.snakeladder.model.Dice;
import hello.lld.snakeladder.model.Player;

import java.util.*;

public class Game {

    private final Dice dice;
    private final Board board;
    private final Queue<Player> playerQueue;
    private Map<Player, Integer> playerCurrentPositionMap;

    private Game(Dice dice, Board board, List<Player> playerList) {
        this.dice = dice;
        this.board = board;
        this.playerQueue = new ArrayDeque<>();
        this.playerCurrentPositionMap = new HashMap<>();
        for (Player player : playerList) {
            playerQueue.offer(player);
            playerCurrentPositionMap.putIfAbsent(player, board.getStart());
        }
    }

    public Game getNewInstance(Dice dice, Board board, List<Player> playerList) {
        if (playerList.size() <= 2) {
            throw new IllegalArgumentException("Cannot create game with less than 2 players.");
        }
        return new Game(dice, board, playerList);
    }

    public void start() {
        int endPos = board.getEnd();
        Map<Integer, Integer> jumpStartToEndMap = board.getJumpStartToEndMap();
        while (playerQueue.size() >= 2) {
            Player currentPlayer = playerQueue.poll();
            int currentPosition = playerCurrentPositionMap.get(currentPlayer);
            int newPosition = currentPosition + dice.roll();

            if (jumpStartToEndMap.containsKey(newPosition)) {
                newPosition = jumpStartToEndMap.get(newPosition);
            }

            playerCurrentPositionMap.put(currentPlayer, newPosition);

            if (newPosition == endPos) {
                System.out.println(currentPlayer + " has reached the end!");
            } else {
                playerQueue.offer(currentPlayer);
            }
        }
    }
}
