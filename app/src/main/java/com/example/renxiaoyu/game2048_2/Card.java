package com.example.renxiaoyu.game2048_2;

import android.view.Gravity;
import android.widget.FrameLayout;
import android.content.Context;
import android.widget.TextView;


public class Card extends FrameLayout {
    /*用来显示游戏中的小方块以及其上的数字*/
    private int number = 0;
    private TextView tvNumber;
    public Card(Context context)
    {
        super(context);
        //初始化tvNumber
        tvNumber = new TextView(getContext());
        /*设置数字大小*/
        tvNumber.setTextSize(32);
        /*设置数字位置居中*/
        tvNumber.setGravity(Gravity.CENTER);
        /*设置数字背景颜色*/
        tvNumber.setBackgroundColor(0x33ffffff);
        //添加tvNumber
        LayoutParams lp = new LayoutParams(-1,-1);
        /*设置偏移量*/
        lp.setMargins(10,10,0,0);
        addView(tvNumber,lp);

        setNumber(0);//初始化数字为0
    }
    /*获取卡片数字*/
    public int getNumber()
    {
        return number;
    }
    /*设置卡片数字*/
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
        /*根据数字的不同显示不同的背景颜色*/
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
