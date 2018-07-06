package com.testmvp;

import android.os.Handler;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Model implements Contract.ModelCallBacks {
    private List<String> listOfFruits = Arrays.asList("APPLE", "MANGO", "BANANA", "GRAPES");

    @Override
    public void getNextFruit(final OnFinishedListener onFinishedListener) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onFinishedListener.onFinished(getRandomWord());
            }
        }, 2000);
    }

    private String getRandomWord() {
        Random random = new Random();
        return listOfFruits.get(random.nextInt(listOfFruits.size()));
    }
}
