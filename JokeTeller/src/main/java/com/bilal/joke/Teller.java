package com.bilal.joke;

import java.util.Random;

public class Teller {
    private static String[] jokes = {"Don\'t interrupt someone working intently on a puzzle.\nChances are, you\'ll hear some crosswords.",
    "Q. What\'s the difference between ignorance and apathy?\nA. I don\'t know and I don\'t care.",
    "I\'m a big fan of whiteboards.\nI find them quite re-markable.",
    "Did you see the movie about the hot dog?\nIt was an Oscar wiener.",
    "The shovel was a ground-breaking invention."};
    public static String getJoke(){
        Random rand = new Random();
        int  n = rand.nextInt(jokes.length -1 );
        return jokes[n];
    }
}
