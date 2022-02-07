package hello.lld.snakeladder.model;

import lombok.NonNull;
import lombok.Value;

@Value
public class SnakeOrLadder {

    int start;
    int end;

    private SnakeOrLadder(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public SnakeOrLadder getNewInstance(int startPosition, int endPosition, @NonNull Board board) {
        if (startPosition == endPosition) {
            throw new IllegalArgumentException("startPosition and endPosition cannot be same");
        }
        int min = Math.min(startPosition, endPosition);
        int max = Math.max(startPosition, endPosition);

        if (min <= board.getStart() || max >= board.getEnd()) {
            throw new IllegalArgumentException("Invalid position for snake or ladder on board");
        }

        return new SnakeOrLadder(startPosition, endPosition);
    }
}
