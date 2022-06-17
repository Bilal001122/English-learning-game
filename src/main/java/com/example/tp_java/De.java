package com.example.tp_java;

import java.util.Random;

public class De {
    private int RandomNumber;

    public int throwDice() {
        Random random = new Random();
        int n = random.nextInt(6) + 1;
        return n;
    }

    public int getRandomNumber() {
        return RandomNumber;
    }
}
