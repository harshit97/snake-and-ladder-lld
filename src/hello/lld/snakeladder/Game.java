package hello.lld.snakeladder;

import hello.lld.snakeladder.model.Board;
import hello.lld.snakeladder.model.Dice;
import hello.lld.snakeladder.model.Player;
import hello.lld.snakeladder.model.SnakeOrLadder;

import java.util.*;

public class Game {

    private final Dice dice;
    private final Board board;
    private final Queue<Player> playerQueue;
    private Map<Integer, Integer> jumpStartToEndMap;
    private Map<Player, Integer> playerCurrentPositionMap;

    private Game(Dice dice, Board board, List<Player> playerList, List<SnakeOrLadder> snakeOrLadderList) {
        this.dice = dice;
        this.board = board;
        this.playerQueue = new ArrayDeque<>();
        this.jumpStartToEndMap = new HashMap<>();
        this.playerCurrentPositionMap = new HashMap<>();
        for (Player player : playerList) {
            playerQueue.offer(player);
            playerCurrentPositionMap.putIfAbsent(player, board.getStart());
        }
        for (SnakeOrLadder snakeOrLadder : snakeOrLadderList) {
            jumpStartToEndMap.putIfAbsent(snakeOrLadder.getStart(), snakeOrLadder.getEnd());
        }
    }

    public Game getNewInstance(Dice dice, Board board, List<Player> playerList, List<SnakeOrLadder> snakeOrLadderList) {
        if (playerList.size() <= 2) {
            throw new IllegalArgumentException("Cannot create game with less than 2 players.");
        }
        return new Game(dice, board, playerList, snakeOrLadderList);
    }

    public void start() {
        int endPos = board.getEnd();
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
