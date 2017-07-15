package com.example.renxiaoyu.game2048_2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvScore = (TextView) findViewById(R.id.tvScore);
        tvBest = (TextView) findViewById(R.id.tvBest) ;
        SharedPreferences sp =getSharedPreferences("game2048", Context.MODE_PRIVATE);
        bestscore = sp.getInt("best",0);
        tvBest.setText(score+"");
        soundpool= new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        music = soundpool.load(this, R.raw.sound, 1);

        mainActivity = this;
    }

    /*清空计分*/
    public void clearScore(){
        score = 0;
    }
    /*显示计分*/
    public void showScore(){
        tvScore.setText(score+"");
        tvBest.setText(bestscore+"");
    }
    /*计分原则*/
    public void addScore(int s)
    {
        score+=s;
       showScore();
    }
    public void setBestscore(int s)
    {
        bestscore = s;
        showScore();
    }
    public int getScore()
    {
        return score;
    }
    public void sound()
    {

        soundpool.play(music, 1, 1, 0, 0, 1);
    }

    public static MainActivity getMainActivity() {
        return mainActivity;
    }
    private TextView tvScore;
    private TextView tvBest;
    private int score = 0;
    private int bestscore = 0;
    private SoundPool soundpool;
    private int music;
    private static MainActivity mainActivity = null;
}
