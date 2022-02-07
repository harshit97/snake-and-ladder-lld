package hello.lld.snakeladder.model;

import lombok.Value;

@Value
public class Board {

    int start;
    int end;

    private Board(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public Board getNewInstance(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Invalida size value");
        }
        return new Board(1, size);
    }
}
