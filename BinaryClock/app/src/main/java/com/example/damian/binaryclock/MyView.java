package com.example.damian.binaryclock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Handler;
import android.text.TextPaint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Damian on 19/04/2017.
 */
public class MyView extends View {

    private Paint paint = new Paint();

    private RectF hours_first_1;
    private RectF hours_first_2;
    private RectF hours_second_1;
    private RectF hours_second_2;
    private RectF hours_second_4;
    private RectF hours_second_8;
    private RectF minutes_first_1;
    private RectF minutes_first_2;
    private RectF minutes_first_4;
    private RectF minutes_second_1;
    private RectF minutes_second_2;
    private RectF minutes_second_4;
    private RectF minutes_second_8;
    private RectF seconds_first_1;
    private RectF seconds_first_2;
    private RectF seconds_first_4;
    private RectF seconds_second_1;
    private RectF seconds_second_2;
    private RectF seconds_second_4;
    private RectF seconds_second_8;

    private int[] colours = {Color.BLUE,Color.GREEN,Color.YELLOW,Color.CYAN, Color.MAGENTA,Color.RED,Color.WHITE};
    private int hour_color = 0; //blue
    private int minute_color = 1; // green
    private int second_color = 5; //red

    private boolean showinfo = false;
    private boolean block_pressed = false;

    private TextPaint textPaint = new TextPaint();

    private int round_rect;

    public MyView(Context context) {
        super(context);

        //timer
        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                invalidate(); //invalidate the graphics every 1 second
                //-------------------
                h.postDelayed(this, 1000);
            }
        }, 1000); // 1 second delay (takes millis)

    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {


        float x = e.getX();
        float y = e.getY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:

                block_pressed = false; //restart the flag

                //check if hours block was pressed
                hoursPressed((int) x, (int) y);
                //check if minutes block was pressed
                minutesPressed((int) x, (int) y);
                //check if seconds block was pressed
                secondsPressed((int) x, (int) y);

                //display labels flags
                if(!showinfo) {
                    if (!block_pressed) {
                        showinfo = true;
                    }
                }
                else{
                    if (!block_pressed) {
                        showinfo = false;
                    }
                }
        }

        return true;

    }

    private void showInfo(Canvas canvas, int x, int y, int block_size, int space_between_blocks){


        textPaint.setTextSize((int)(y / 7.5));
        textPaint.setTypeface(Typeface.create("Arial", Typeface.BOLD));
        textPaint.setColor(colours[hour_color]);

        if(showinfo){
            //Hours
            Rect bounds = new Rect();
            textPaint.getTextBounds("H", 0, "H".length(), bounds);
            canvas.drawText("H",block_size + ((block_size - bounds.width()) / 2),y - (block_size * 4) - (space_between_blocks * 10),textPaint);
            canvas.drawText("H", ((block_size * 2) + space_between_blocks) + ((block_size - bounds.width()) / 2), y - (block_size * 4) - (space_between_blocks * 10), textPaint);

            //Minutes
            textPaint.setColor(colours[minute_color]);
            bounds = new Rect();
            textPaint.getTextBounds("M", 0, "M".length(), bounds);
            canvas.drawText("M", ((block_size * 4) + space_between_blocks) + ((block_size - bounds.width()) / 2), y - (block_size * 4) - (space_between_blocks * 10), textPaint);
            canvas.drawText("M", ((block_size * 5) + (space_between_blocks * 2)) + ((block_size - bounds.width()) / 2), y - (block_size * 4) - (space_between_blocks * 10),textPaint);

            //Seconds
            textPaint.setColor(colours[second_color]);
            bounds = new Rect();
            textPaint.getTextBounds("S", 0, "S".length(), bounds);
            canvas.drawText("S", ((block_size * 7) + (space_between_blocks * 2)) + ((block_size - bounds.width()) / 2), y - (block_size * 4) - (space_between_blocks * 10), textPaint);
            canvas.drawText("S", ((block_size * 8) + (space_between_blocks * 3)) + ((block_size - bounds.width()) / 2), y - (block_size * 4) - (space_between_blocks * 10),textPaint);

        }

    }

    private void hoursPressed(int x, int y){

        if(hours_first_1.contains(x, y))
            updateHourColor();
        else if(hours_first_2.contains(x,y))
            updateHourColor();
        else if(hours_second_1.contains(x,y))
            updateHourColor();
        else if(hours_second_2.contains(x,y))
            updateHourColor();
        else if(hours_second_4.contains(x,y))
            updateHourColor();
        else if(hours_second_8.contains(x,y))
            updateHourColor();


    }
    private void updateHourColor(){
        if(hour_color < colours.length - 1)
            hour_color++;
        else
            hour_color = 0;

        block_pressed = true;
    }
    private void minutesPressed(int x, int y){

        if(minutes_first_1.contains(x, y))
            updateMinuteColor();
        else if(minutes_first_2.contains(x,y))
            updateMinuteColor();
        else if(minutes_first_4.contains(x,y))
            updateMinuteColor();
        else if(minutes_second_1.contains(x,y))
            updateMinuteColor();
        else if(minutes_second_2.contains(x,y))
            updateMinuteColor();
        else if(minutes_second_4.contains(x,y))
            updateMinuteColor();
        else if(minutes_second_8.contains(x,y))
            updateMinuteColor();

    }
    private void updateMinuteColor(){
        if(minute_color < colours.length - 1)
            minute_color++;
        else
            minute_color = 0;

        block_pressed = true;
    }
    private void secondsPressed(int x, int y){

        if(seconds_first_1.contains(x, y))
            updateSecondsColor();
        else if(seconds_first_2.contains(x,y))
            updateSecondsColor();
        else if(seconds_first_4.contains(x,y))
            updateSecondsColor();
        else if(seconds_second_1.contains(x,y))
            updateSecondsColor();
        else if(seconds_second_2.contains(x,y))
            updateSecondsColor();
        else if(seconds_second_4.contains(x,y))
            updateSecondsColor();
        else if(seconds_second_8.contains(x,y))
            updateSecondsColor();

    }
    private void updateSecondsColor(){
        if(second_color < colours.length - 1)
            second_color++;
        else
            second_color = 0;

        block_pressed = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        //anti aliasing
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);

        //draw background
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);

        //get screen size
        int x = getWidth();
        int y = getHeight();

        //calculate round_rect
        round_rect = (int)(y / 21.6);



        //get block width
        int block_size = x / 10; // 10 blocks fits in width

        //get space between blocks
        int space_between_blocks = y / 80; //6

        createBlocks(canvas,x,y,block_size,space_between_blocks);

        //get the time
        int[] time = getTime();

        //break the time apart
        int[] timeApart = getTimeApart(time);

        displayTime(canvas, timeApart);
        showInfo(canvas,x,y,block_size,space_between_blocks);


    }

    private void createBlocks(Canvas canvas,int x, int y, int block_size, int space_between_blocks) {


        //draw hours blocks
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.argb(255, 128, 128, 128));
        hours_first_1 = new RectF(block_size,y - block_size,block_size * 2,(y - block_size) + block_size);
        canvas.drawRoundRect(hours_first_1,round_rect,round_rect,paint);
        hours_first_2 = new RectF(block_size,(y - (block_size * 2)) - space_between_blocks,block_size * 2,((y - (block_size * 2)) - space_between_blocks) + block_size);
        canvas.drawRoundRect(hours_first_2,round_rect,round_rect, paint);

        hours_second_1 = new RectF((block_size * 2) + space_between_blocks ,y - block_size,((block_size * 2) + space_between_blocks) + block_size ,(y - block_size) + block_size);
        canvas.drawRoundRect(hours_second_1, round_rect,round_rect, paint);
        hours_second_2 = new RectF((block_size * 2) + space_between_blocks ,(y - (block_size * 2)) - space_between_blocks,((block_size * 2) + space_between_blocks) + block_size ,((y - (block_size * 2)) - space_between_blocks) + block_size);
        canvas.drawRoundRect(hours_second_2, round_rect,round_rect, paint);
        hours_second_4 = new RectF((block_size * 2) + space_between_blocks , y - (block_size * 3) - (space_between_blocks * 2),((block_size * 2) + space_between_blocks) + block_size,(y - (block_size * 3) - (space_between_blocks * 2)) +block_size);
        canvas.drawRoundRect(hours_second_4, round_rect,round_rect, paint);
        hours_second_8 = new RectF((block_size * 2) + space_between_blocks , y - (block_size * 4) - (space_between_blocks * 3),((block_size * 2) + space_between_blocks) + block_size,(y - (block_size * 4) - (space_between_blocks * 3)) +block_size);
        canvas.drawRoundRect(hours_second_8, round_rect,round_rect, paint);

        //draw minutes
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.argb(255, 128, 128, 128));
        minutes_first_1 = new RectF( (block_size * 4) + space_between_blocks,y - block_size, ((block_size * 4) + space_between_blocks) + block_size,(y - block_size) + block_size);
        canvas.drawRoundRect(minutes_first_1, round_rect,round_rect, paint);
       minutes_first_2 = new RectF((block_size * 4) + space_between_blocks,(y - (block_size * 2)) - space_between_blocks,((block_size * 4) + space_between_blocks) + block_size,((y - (block_size * 2)) - space_between_blocks) + block_size);
        canvas.drawRoundRect(minutes_first_2, round_rect,round_rect, paint);
        minutes_first_4 = new RectF((block_size * 4) + space_between_blocks, y - (block_size * 3) - (space_between_blocks * 2),((block_size * 4) + space_between_blocks) + block_size,(y - (block_size * 3) - (space_between_blocks * 2)) +block_size);
        canvas.drawRoundRect(minutes_first_4, round_rect,round_rect, paint);

        minutes_second_1 = new RectF( (block_size * 5) + (space_between_blocks * 2),y - block_size, ((block_size * 5) + (space_between_blocks * 2)) + block_size,(y - block_size) + block_size);
        canvas.drawRoundRect(minutes_second_1, round_rect,round_rect, paint);
        minutes_second_2 = new RectF((block_size * 5) + (space_between_blocks * 2),(y - (block_size * 2)) - space_between_blocks,((block_size * 5) + (space_between_blocks * 2)) + block_size,((y - (block_size * 2)) - space_between_blocks) + block_size);
        canvas.drawRoundRect(minutes_second_2, round_rect,round_rect, paint);
        minutes_second_4 = new RectF((block_size * 5) + (space_between_blocks * 2), y - (block_size * 3) - (space_between_blocks * 2),((block_size * 5) + (space_between_blocks * 2)) + block_size,(y - (block_size * 3) - (space_between_blocks * 2)) +block_size);
        canvas.drawRoundRect(minutes_second_4, round_rect,round_rect, paint);
        minutes_second_8 = new RectF((block_size * 5) + (space_between_blocks * 2) , y - (block_size * 4) - (space_between_blocks * 3),((block_size * 5) + (space_between_blocks * 2)) + block_size,(y - (block_size * 4) - (space_between_blocks * 3)) +block_size);
        canvas.drawRoundRect(minutes_second_8, round_rect,round_rect, paint);

        //draw seconds
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.argb(255, 128, 128, 128));
        seconds_first_1 = new RectF( (block_size * 7) + (space_between_blocks * 2),y - block_size, ((block_size * 7) + (space_between_blocks * 2)) + block_size,(y - block_size) + block_size);
        canvas.drawRoundRect(seconds_first_1, round_rect,round_rect, paint);
        seconds_first_2 = new RectF((block_size * 7) + (space_between_blocks * 2),(y - (block_size * 2)) - space_between_blocks,((block_size * 7) + (space_between_blocks * 2)) + block_size,((y - (block_size * 2)) - space_between_blocks) + block_size);
        canvas.drawRoundRect(seconds_first_2, round_rect,round_rect, paint);
        seconds_first_4 = new RectF((block_size * 7) + (space_between_blocks * 2), y - (block_size * 3) - (space_between_blocks * 2),((block_size * 7) + (space_between_blocks * 2)) + block_size,(y - (block_size * 3) - (space_between_blocks * 2)) +block_size);
        canvas.drawRoundRect(seconds_first_4, round_rect,round_rect, paint);

        seconds_second_1 = new RectF( (block_size * 8) + (space_between_blocks * 3),y - block_size, ((block_size * 8) + (space_between_blocks * 3)) + block_size,(y - block_size) + block_size);
        canvas.drawRoundRect(seconds_second_1,round_rect,round_rect,paint);
        seconds_second_2 = new RectF((block_size * 8) + (space_between_blocks * 3),(y - (block_size * 2)) - space_between_blocks,((block_size * 8) + (space_between_blocks * 3)) + block_size,((y - (block_size * 2)) - space_between_blocks) + block_size);
        canvas.drawRoundRect(seconds_second_2, round_rect,round_rect, paint);
        seconds_second_4 = new RectF((block_size * 8) + (space_between_blocks * 3), y - (block_size * 3) - (space_between_blocks * 2),((block_size * 8) + (space_between_blocks * 3)) + block_size,(y - (block_size * 3) - (space_between_blocks * 2)) +block_size);
        canvas.drawRoundRect(seconds_second_4, round_rect,round_rect, paint);
        seconds_second_8 = new RectF((block_size * 8) + (space_between_blocks * 3) , y - (block_size * 4) - (space_between_blocks * 3),((block_size * 8) + (space_between_blocks * 3)) + block_size,(y - (block_size * 4) - (space_between_blocks * 3)) +block_size);
        canvas.drawRoundRect(seconds_second_8, round_rect,round_rect, paint);



    }

    private void displayTime(Canvas canvas,int[] timeApart ){

        //get the time
        int first_h = timeApart[0];
        int second_h = timeApart[1];
        int first_m = timeApart[2];
        int second_m = timeApart[3];
        int first_s = timeApart[4];
        int second_s = timeApart[5];

        //display hours
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(colours[hour_color]);
        switch(first_h){
            case 1:
                canvas.drawRoundRect(hours_first_1,round_rect,round_rect, paint); break;
            case 2:
                canvas.drawRoundRect(hours_first_2,round_rect,round_rect, paint); break;
        }

        switch(second_h){
            case 1:
                canvas.drawRoundRect(hours_second_1,round_rect,round_rect, paint); break;
            case 2:
                canvas.drawRoundRect(hours_second_2,round_rect,round_rect, paint); break;
            case 3:
                canvas.drawRoundRect(hours_second_1,round_rect,round_rect, paint);
                canvas.drawRoundRect(hours_second_2,round_rect,round_rect, paint); break;
            case 4:
                canvas.drawRoundRect(hours_second_4,round_rect,round_rect, paint); break;
            case 5:
                canvas.drawRoundRect(hours_second_4,round_rect,round_rect, paint);
                canvas.drawRoundRect(hours_second_1,round_rect,round_rect, paint); break;
            case 6:
                canvas.drawRoundRect(hours_second_4,round_rect,round_rect, paint);
                canvas.drawRoundRect(hours_second_2,round_rect,round_rect, paint); break;
            case 7:
                canvas.drawRoundRect(hours_second_4,round_rect,round_rect, paint);
                canvas.drawRoundRect(hours_second_2,round_rect,round_rect, paint);
                canvas.drawRoundRect(hours_second_1,round_rect,round_rect, paint); break;
            case 8:
                canvas.drawRoundRect(hours_second_8,round_rect,round_rect, paint); break;
            case 9:
                canvas.drawRoundRect(hours_second_8,round_rect,round_rect, paint);
                canvas.drawRoundRect(hours_second_1,round_rect,round_rect, paint); break;

        }

        //display minutes
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(colours[minute_color]);
        switch(first_m){
            case 1:
                canvas.drawRoundRect(minutes_first_1,round_rect,round_rect, paint); break;
            case 2:
                canvas.drawRoundRect(minutes_first_2,round_rect,round_rect, paint); break;
            case 3:
                canvas.drawRoundRect(minutes_first_2,round_rect,round_rect, paint);
                canvas.drawRoundRect(minutes_first_1,round_rect,round_rect, paint); break;
            case 4:
                canvas.drawRoundRect(minutes_first_4,round_rect,round_rect, paint); break;
            case 5:
                canvas.drawRoundRect(minutes_first_4,round_rect,round_rect, paint);
                canvas.drawRoundRect(minutes_first_1,round_rect,round_rect, paint); break;

        }

        switch(second_m){
            case 1:
                canvas.drawRoundRect(minutes_second_1,round_rect,round_rect, paint); break;
            case 2:
                canvas.drawRoundRect(minutes_second_2,round_rect,round_rect, paint); break;
            case 3:
                canvas.drawRoundRect(minutes_second_2,round_rect,round_rect, paint);
                canvas.drawRoundRect(minutes_second_1,round_rect,round_rect, paint); break;
            case 4:
                canvas.drawRoundRect(minutes_second_4,round_rect,round_rect, paint); break;
            case 5:
                canvas.drawRoundRect(minutes_second_4,round_rect,round_rect, paint);
                canvas.drawRoundRect(minutes_second_1,round_rect,round_rect, paint); break;
            case 6:
                canvas.drawRoundRect(minutes_second_4,round_rect,round_rect, paint);
                canvas.drawRoundRect(minutes_second_2,round_rect,round_rect, paint); break;
            case 7:
                canvas.drawRoundRect(minutes_second_4,round_rect,round_rect, paint);
                canvas.drawRoundRect(minutes_second_2,round_rect,round_rect, paint);
                canvas.drawRoundRect(minutes_second_1,round_rect,round_rect, paint); break;
            case 8:
                canvas.drawRoundRect(minutes_second_8,round_rect,round_rect, paint); break;
            case 9:
                canvas.drawRoundRect(minutes_second_8,round_rect,round_rect, paint);
                canvas.drawRoundRect(minutes_second_1,round_rect,round_rect, paint); break;

        }

        //display seconds
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(colours[second_color]);
        switch(first_s){
            case 1:
                canvas.drawRoundRect(seconds_first_1,round_rect,round_rect, paint); break;
            case 2:
                canvas.drawRoundRect(seconds_first_2,round_rect,round_rect, paint); break;
            case 3:
                canvas.drawRoundRect(seconds_first_2,round_rect,round_rect, paint);
                canvas.drawRoundRect(seconds_first_1,round_rect,round_rect, paint); break;
            case 4:
                canvas.drawRoundRect(seconds_first_4,round_rect,round_rect, paint); break;
            case 5:
                canvas.drawRoundRect(seconds_first_4,round_rect,round_rect, paint);
                canvas.drawRoundRect(seconds_first_1,round_rect,round_rect, paint); break;

        }

        switch(second_s){
            case 1:
                canvas.drawRoundRect(seconds_second_1,round_rect,round_rect, paint); break;
            case 2:
                canvas.drawRoundRect(seconds_second_2,round_rect,round_rect, paint); break;
            case 3:
                canvas.drawRoundRect(seconds_second_2,round_rect,round_rect, paint);
                canvas.drawRoundRect(seconds_second_1,round_rect,round_rect, paint); break;
            case 4:
                canvas.drawRoundRect(seconds_second_4,round_rect,round_rect, paint); break;
            case 5:
                canvas.drawRoundRect(seconds_second_4,round_rect,round_rect, paint);
                canvas.drawRoundRect(seconds_second_1,round_rect,round_rect, paint); break;
            case 6:
                canvas.drawRoundRect(seconds_second_4,round_rect,round_rect, paint);
                canvas.drawRoundRect(seconds_second_2,round_rect,round_rect, paint); break;
            case 7:
                canvas.drawRoundRect(seconds_second_4,round_rect,round_rect, paint);
                canvas.drawRoundRect(seconds_second_2,round_rect,round_rect, paint);
                canvas.drawRoundRect(seconds_second_1,round_rect,round_rect, paint); break;
            case 8:
                canvas.drawRoundRect(seconds_second_8,round_rect,round_rect, paint); break;
            case 9:
                canvas.drawRoundRect(seconds_second_8,round_rect,round_rect, paint);
                canvas.drawRoundRect(seconds_second_1,round_rect,round_rect, paint); break;

        }

    }

    private int[] getTime(){

        //get time
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        int ss = calendar.get(Calendar.SECOND);
        int mm = calendar.get(Calendar.MINUTE);
        int hh = calendar.get(Calendar.HOUR_OF_DAY);

        int[] values = {hh,mm,ss};
        return values;

    }

    private int[] getTimeApart(int[] time){

        int hh = time[0];
        int mm = time[1];
        int ss = time[2];

        int first_h, second_h;
        int first_m, second_m;
        int first_s, second_s;

        if(hh <= 9) {
            first_h = 0;
            second_h = hh;
        }
        else{
            first_h = hh / 10;
            second_h = hh % 10;
        }

        if(mm <= 9) {
            first_m = 0;
            second_m = mm;
        }
        else{
            first_m = mm / 10;
            second_m = mm % 10;
        }

        if(ss <= 9) {
            first_s = 0;
            second_s = ss;
        }
        else{
            first_s = ss / 10;
            second_s = ss % 10;
        }

        int[] values = {first_h, second_h, first_m, second_m, first_s, second_s};
        return values;

    }
}
