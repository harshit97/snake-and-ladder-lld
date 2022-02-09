package hello.lld.snakeladder.model;

import lombok.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Value
public class Board {

    int start;
    int end;
    Map<Integer, Integer> jumpStartToEndMap;

    private Board(int start, int end, Map<Integer, Integer> jumpStartToEndMap) {
        this.start = start;
        this.end = end;
        this.jumpStartToEndMap = jumpStartToEndMap;
    }

    public Board getNewInstance(int size, List<SnakeOrLadder> snakeOrLadderList) {
        if (size <= 0) {
            throw new IllegalArgumentException("Invalid size value");
        }
        Map<Integer, Integer> snakeOrLadderStartToEndMap = new HashMap<>();
        for (SnakeOrLadder snakeOrLadder : snakeOrLadderList) {
            int snakeOrLadderStart = snakeOrLadder.getStart();
            int snakeOrLadderEnd = snakeOrLadder.getEnd();
            if (snakeOrLadderStart <= 1 || snakeOrLadderEnd >= size) {
                throw new IllegalArgumentException("Snake or ladder range should be within board");
            }
            snakeOrLadderStartToEndMap.putIfAbsent(snakeOrLadderStart, snakeOrLadderEnd);
        }
        return new Board(1, size, snakeOrLadderStartToEndMap);
    }
}
