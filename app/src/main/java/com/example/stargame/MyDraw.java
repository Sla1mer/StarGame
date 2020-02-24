package com.example.stargame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.stargame.Model.Enemy;
import com.example.stargame.Model.Player;
import com.example.stargame.Model.Star;

import java.util.ArrayList;

public class MyDraw extends SurfaceView implements Runnable{

    volatile boolean playing;
    private Thread gameThread = null;
    private Player player;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private ArrayList<Star> stars = new ArrayList<>();
    private ArrayList<Enemy> enemy = new ArrayList<Enemy>();


    public MyDraw(Context context, int screenX, int screenY) {
        super(context);
        player = new Player(context, screenX, screenY);
        surfaceHolder = getHolder();
        paint = new Paint();
        int starNums = 100;
        int enemyNums = 10;

        for (int i = 0; i<starNums; i++){
            Star s = new Star(screenX, screenY);
            stars.add(s);
        }

        for (int i = 0; i<enemyNums; i++){
            Enemy e = new Enemy(screenX, screenY);
            enemy.add(e);
        }
    }


    @Override
    public void run() {
        while (playing){
            update();
            draw();
            control();
        }
    }
    public void update(){
        player.Update();
        for (Star s : stars){
            s.Update(player.getSpeed());
        }

        for (Enemy e : enemy){
            e.Update(player.getSpeed());
            if (player.getX() == e.getX() && player.getY() == e.getY()){
                pause();
            }
        }

    }

    public void draw(){
        if (surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();
                canvas.drawColor(Color.BLACK);
                paint.setColor(Color.WHITE);
                for (Star s : stars)
                {
                    paint.setStrokeWidth(s.getStarWight());
                    canvas.drawPoint(s.getX(), s.getY(), paint);
                }

                paint.setColor(Color.BLUE);
                for (Enemy e : enemy)
                {
                    paint.setStrokeWidth(e.getEnemyWight());
                    canvas.drawPoint(e.getX(), e.getY(), paint);
                }

                canvas.drawBitmap(player.getBitmap(),
                        player.getX(),
                        player.getY(),
                        paint);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void control(){
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause(){
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume(){
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_UP:
                player.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                player.setBoosting();
                break;
        }
        return true;
    }
}
