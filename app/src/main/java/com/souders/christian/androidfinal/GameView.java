package com.souders.christian.androidfinal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by Christian on 3/6/2016.
 */
public class GameView extends View{
    Thread gameThread = null;
    volatile boolean playing;

    boolean paused = true;

    long fps;
    private long timeThisFrame;
    Paddle paddle;
    final int DIM = 10;
    //for blocks, 1 is a block, 0 is nothing
    int[][] blocks = new int[DIM][DIM];
    //this is the default for new games
    int numBlocks = 5;

    final float[] vertices = new float[]{0, 5, 20, 5, 20, 0, 0, 0};


    int global_width = -1;
    int global_height = -1;

    final int red = Color.rgb(255,0,0) ;

    public GameView(Context context) {
        super(context);
        initializeDataMembers();
    }



    public GameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initializeDataMembers();
    }

    public GameView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        initializeDataMembers();
    }


    //sets to the default of 5 blocks
    private void initializeDataMembers()
    {
        Random rand = new Random();
        for(int i  = 0; i < numBlocks; i ++)
        {
            int rowLoc = rand.nextInt(DIM);
            int colLoc = rand.nextInt(DIM);
            while(blocks[rowLoc][colLoc] == 1)
            {
                rowLoc = rand.nextInt(DIM);
                colLoc = rand.nextInt(DIM);
            }
            blocks[rowLoc][colLoc] = 1;
        }
        paddle = new Paddle(200,140);

       // System.out.println();

    }


    @Override
    public void onDraw(Canvas canvas)
    {
        if(global_width == -1)
            global_width = canvas.getWidth();
        if(global_height ==-1)
            global_height = canvas.getHeight();

        float width = (float)global_width/200;
        float height = (float)global_height/140;
        canvas.scale(width, height);
        canvas.drawColor(Color.rgb(0, 0, 0));

        Path path = new Path();
        path.moveTo(vertices[0], vertices[1]);
        path.lineTo(vertices[2], vertices[3]);
        path.lineTo(vertices[4], vertices[5]);
        path.lineTo(vertices[6], vertices[7]);
        path.close();
        Paint paint = new Paint();
        paint.setColor(red);
        paint.setStyle(Paint.Style.FILL);

        for(int i = 0; i < DIM; i ++)
        {
            for(int j = 0; j < DIM; j++)
            {
               if(blocks[i][j] == 1)
                {
                    drawBlock(canvas, i, j, paint, path);
                }
            }
        }


        canvas.drawRect(paddle.getRect(),paint);
    }


    public void drawBlock(Canvas canvas, int row, int col, Paint paint, Path path)
    {
        canvas.save();
       // paint.setColor(color);
        float y = (float)(5 * row);
        float x = (float)(20 * col);
        canvas.translate(x, y);

        canvas.drawPath(path, paint);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        // setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
        int allocatedWidth = MeasureSpec.getSize(widthMeasureSpec);
        int allocatedHeight = MeasureSpec.getSize(heightMeasureSpec);
        double potWidth = ((double)(allocatedHeight)) * (200f/140f);
        double potHeight = ((double)(allocatedWidth)) * (140f/200f);
        if(((int)potHeight) > allocatedHeight)
        {
            setMeasuredDimension((int)potWidth, allocatedHeight);
        }
        else
            setMeasuredDimension(allocatedWidth, (int)potHeight);
    }
    /*
    @Override
    public void run() {
            while (playing) {

                // Capture the current time in milliseconds in startFrameTime
                long startFrameTime = System.currentTimeMillis();

                // Update the frame
                // Update the frame
                if(!paused){
                    update();
                }

                // Draw the frame


                // Calculate the fps this frame
                // We can then use the result to
                // time animations and more.
                timeThisFrame = System.currentTimeMillis() - startFrameTime;
                if (timeThisFrame >= 1) {
                    fps = 1000 / timeThisFrame;
                }

            }
    }
    */

    public void update() {
        while(paddle.getMovementState() != paddle.STOPPED)
            invalidate();
    }

    public void resume() {
       // playing = true;
       // gameThread = new Thread(this);
      //  gameThread.start();
    }
}
