package com.example.renxiaoyu.game2048_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
public class StartActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        /*开始游戏按键*/
        button1 = (Button) findViewById(R.id.button1);
        /*游戏开发者信息按键*/
        button2 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                /*页面跳转*/
                intent.setClass(StartActivity.this, MainActivity.class);
                StartActivity.this.startActivity(intent);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                /*页面跳转*/
                intent.setClass(StartActivity.this, Information.class);
                StartActivity.this.startActivity(intent);

            }
        });

    }
}
