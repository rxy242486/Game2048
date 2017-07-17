package com.example.renxiaoyu.game2048_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Information extends Activity {
    private Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*卡发着信息界面显示*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //界面跳转
                intent.setClass(Information.this, StartActivity.class);
                Information.this.startActivity(intent);
            }
        });

    }
}
