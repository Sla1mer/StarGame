package com.example.stargame.Model;

import java.util.Random;

public class Star {
    private int x;
    private int y;
    private int speed;
    private int maxX;
    private int maxY;
    private int minX;
    private int minY;

    public Star(int screenX, int screenY) {
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;

        Random gen = new Random();
        speed = gen.nextInt(10);
        x = gen.nextInt(maxX);
        y = gen.nextInt(maxY);
    }

    public void Update(int speedPlayer){
        x -= speedPlayer;
        x -= speed;
        if (x<0){
            x = maxX;
            Random gen = new Random();
            y = gen.nextInt(maxY);
            speed = gen.nextInt(15);
        }
    }

    public float getStarWith(){
        float minX = 1.0f;
        float maxX = 4.0f;
        Random random = new Random();
        float finalX = random.nextFloat()*(maxX-minX)+minX;
        return finalX;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
