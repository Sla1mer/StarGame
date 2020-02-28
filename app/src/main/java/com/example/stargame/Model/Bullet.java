package com.example.stargame.Model;

import java.util.Random;

public class Bullet {
    private int x;
    private int y;
    private int speed;
    private int maxX;
    private int maxY;
    private int minX;
    private int minY;

    public Bullet( int gX, int gY,int screenX) {
        maxX = screenX;
        minX = 0;
        minY = 0;
        speed = 20;
        x = gX+50;
        y = gY+50;
    }

    public void Update(int gX, int gY,int speedPlayer){
        x += speed;

        if (x>maxX||x<0){
            x = gX+50;
            y = gY+50;
        }
    }

    public float getBulletWith(){
        float minX = 20.0f;
        float maxX = 1.0f;
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