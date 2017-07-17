package com.example.renxiaoyu.game2048_2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        /*加载音乐*/
        soundpool= new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        music = soundpool.load(this, R.raw.sound, 1);
        mainActivity = this;
        /*返回主目录的按键响应*/
        button =(Button) findViewById(R.id.rsbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, StartActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

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
    /*设置最高分*/
    public void setBestscore(int s)
    {
        bestscore = s;
        showScore();
    }
    /*获取当前分数*/
    public int getScore()
    {
        return score;
    }
    /*滑动音效*/
    public void sound()
    {

        soundpool.play(music, 1, 1, 0, 0, 1);
    }
    /*方便外界调用*/
    public static MainActivity getMainActivity() {
        return mainActivity;
    }
    /*当前分数视图*/
    private TextView tvScore;
    /*最高分视图*/
    private TextView tvBest;
    /*当前分数*/
    private int score = 0;
    /*最高分*/
    private int bestscore = 0;
    /*用于播放音效*/
    private SoundPool soundpool;
    /*返回目录按键*/
    private Button button;
    /*音乐*/
    private int music;
    private static MainActivity mainActivity = null;

}
