package com.mrnadimi.timertextview;


/**
 * Developer: Mohamad Nadimi
 * Company: Saghe
 * Website: https://www.mrnadimi.com
 * Created on 06 April 2022
 * <p>
 * Description: ...
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimerTextView extends androidx.appcompat.widget.AppCompatTextView {

    private int timeInSec = 0;
    private String finishingText;
    private CountDownTimer timer;
    private String dateFormat;

    public TimerTextView(@NonNull Context context) {
        this(context , null);
    }

    public TimerTextView(@NonNull Context context, @Nullable  AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public TimerTextView(@NonNull  Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null){
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TimerTextView, defStyleAttr, 0);
            timeInSec = a.getInteger(R.styleable.TimerTextView_ttv_timeInSec, 0);
            finishingText = a.getString(R.styleable.TimerTextView_ttv_finishingText);
            dateFormat = a.getString(R.styleable.TimerTextView_ttv_dateFormat);
            a.recycle();
        }
        if (finishingText == null){
            finishingText = "try again";
        }
        if (dateFormat == null){
            dateFormat = "HH:mm:ss";
        }
        timer = createTimer();
    }

    private CountDownTimer createTimer(){
        return new CountDownTimer((timeInSec* 1000), 1000) {

            public void onTick(long millisUntilFinished) {
                setText(convertSecondToHHMMString(millisUntilFinished));
                // timeInSec--;
            }

            public void onFinish() {
                setText(finishingText);
            }
        };
    }

    public String getFinishingText() {
        return finishingText;
    }

    public void setFinishingText(String finishingText) {
        this.finishingText = finishingText;
    }


    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setTimeInSec(int timeInSec) {
        this.timeInSec = timeInSec;
        timer.cancel();
        timer = createTimer();
    }

    public int getTimeInSec() {
        return timeInSec;
    }

    public void startTimer(){
        timer.start();
    }

    public void resetTimer(){
        timer.cancel();
        timer.start();
    }

    public void finishTimer(){
        timer.cancel();
    }


    private String convertSecondToHHMMString(long mills)
    {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        df.setTimeZone(tz);
        return df.format(new Date(mills));

    }
}


