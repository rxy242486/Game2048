package com.example.renxiaoyu.game2048_2;

import android.view.Gravity;
import android.widget.FrameLayout;
import android.content.Context;
import android.widget.TextView;

/**
 * Created by RenXiaoyu on 2017/7/12.
 */

public class Card extends FrameLayout {
    private int number = 0;
    private TextView tvNumber;
    public Card(Context context)
    {
        super(context);
        //初始化tvNumber
        tvNumber = new TextView(getContext());
        tvNumber.setTextSize(32);
        tvNumber.setGravity(Gravity.CENTER);
        tvNumber.setBackgroundColor(0x33ffffff);
        //添加tvNumber
        LayoutParams lp = new LayoutParams(-1,-1);
        lp.setMargins(10,10,0,0);
        addView(tvNumber,lp);

        setNumber(0);//初始化数字为0
    }
    public int getNumber()
    {
        return number;
    }
    public void setNumber(int number)
    {
        this.number = number;
        if(number<=0)
        {
            tvNumber.setText("");
        }
        else
        {
            tvNumber.setText(number + "");
        }
        switch (number)
        {
            case 0:
                tvNumber.setBackgroundColor(0x33ffffff);
                break;
            case 2:
                tvNumber.setBackgroundColor(0xffeee4da);
                break;
            case 4:
                tvNumber.setBackgroundColor(0xffede0c8);
                break;
            case 8:
                tvNumber.setBackgroundColor(0xfff2b179);
                break;
            case 16:
                tvNumber.setBackgroundColor(0xfff59563);
                break;
            case 32:
                tvNumber.setBackgroundColor(0xfff67c5f);
                break;
            case 64:
                tvNumber.setBackgroundColor(0xfff65e3b);
                break;
            case 128:
                tvNumber.setBackgroundColor(0xffedcf72);
                break;
            case 256:
                tvNumber.setBackgroundColor(0xffedcc61);
                break;
            case 512:
                tvNumber.setBackgroundColor(0xffedc850);
                break;
            case 1024:
                tvNumber.setBackgroundColor(0xffedc53f);
                break;
            case 2048:
                tvNumber.setBackgroundColor(0xffedc22e);
                break;
            default:
                tvNumber.setBackgroundColor(0x33ffffff);
                break;
        }
    }


    //判断两个Card的值是否相等
    public boolean equals(Card o)
    {
        return getNumber()==o.getNumber();
    }

}
