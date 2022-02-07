package hello.lld.snakeladder.model;

import lombok.Value;

import java.util.Random;

@Value
public class Dice {

    int minValue;
    int maxValue;
    Random random;

    public Dice(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        if (minValue <= 0 || minValue > maxValue) {
            throw new IllegalArgumentException("Invalid minValue-maxValue combination");
        }
        this.random = new Random();
    }

    public int roll() {
        return random.nextInt(maxValue - minValue) + minValue;
    }
}
