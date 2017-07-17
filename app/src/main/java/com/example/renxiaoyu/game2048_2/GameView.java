package com.example.renxiaoyu.game2048_2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import java.util.ArrayList;
import java.util.List;




/*游戏界面实现*/
public class GameView extends GridLayout {
    public GameView(Context context) {

        super(context);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGameView();
    }
    /*初始化游戏界面*/
    private void initGameView(){
        /*设置列数*/
        setColumnCount(4);
        /*设置背景颜色*/
        setBackgroundColor(0xffbbada0);
        /*判断用户手势*/
        setOnTouchListener(new OnTouchListener() {
            private float startX,startY;//起始位置
            private float endX,endY;//终点位置
            private float offsetX,offsetY;//偏移量

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        endX = event.getX();
                        endY = event.getY();
                        offsetX = endX - startX;
                        offsetY = endY - startY;
                        if(Math.abs(offsetX)>Math.abs(offsetY))//水平
                        {
                            if(offsetX<-5)
                            {

                                MoveLeft();
                            }
                            else if(offsetY>5)
                            {
                                MoveRight();
                            }
                        }
                        else//竖直
                        {
                            if(offsetY<-5)
                            {
                               MoveUp();
                            }
                            else if(offsetY>5)
                            {
                                MoveDown();
                            }
                        }
                        break;
                }
                return true;
            }
        });

    }
    /*生成随机数字卡片*/
    private void addRandomNum()
    {
        emptyPoints.clear();
         for(int y = 0;y<4;y++)
         {
             for(int x = 0;x<4;x++)
             {
                 if(cardsMap[x][y].getNumber()<=0)
                 {
                     emptyPoints.add(new Point(x,y));
                 }
             }
         }
         /*随机获取空点*/
         Point p = emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
        /*按9:1的概率生成2和4*/
        cardsMap[p.x][p.y].setNumber(Math.random()>0.1?2:4);
    }
    private void MoveLeft()
    {
        /*观察者，用于观察是否发生合并*/
        boolean merge = false;
        /*检测到动作后发出播放音效*/
        MainActivity.getMainActivity().sound();
        for(int y = 0;y<4;y++)
        {
            for(int x = 0;x<4;x++)
            {
                /*从当前位置向右扫描*/
                for(int x1 = x+1;x1<4;x1++)
                {
                    if(cardsMap[x1][y].getNumber()>0)
                    {
                        /*当前值为0*/
                        if(cardsMap[x][y].getNumber()<=0)
                        {
                            /*空位左移*/
                            cardsMap[x][y].setNumber(cardsMap[x1][y].getNumber());
                            /*清空*/
                            cardsMap[x1][y].setNumber(0);
                            /*避免202不合并*/
                            x--;
                            merge = true;
                        }
                        /*相同，可合并*/
                        else if(cardsMap[x][y].equals(cardsMap[x1][y]))
                        {
                            /*左移，合并，数字显示为原先的2倍*/
                            cardsMap[x][y].setNumber(cardsMap[x][y].getNumber()*2);
                            cardsMap[x1][y].setNumber(0);
                            /*发生合并，增加相应的分数*/
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNumber());
                            /*更新最高分*/
                            updateBest();
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if(merge)
        {
            /*每一步之后都要添加新的卡片*/
            addRandomNum();
            /*检查游戏是否结束*/
            checkComplete();
        }
    }
    /*右，上，下，操作类似，在此不做赘述*/
    private void MoveRight()
    {
        boolean merge = false;
        MainActivity.getMainActivity().sound();

        for(int y = 0;y<4;y++)
        {
            for(int x = 3;x>=0;x--)
            {
                for(int x1 = x-1;x1>=0;x1--)
                {
                    if(cardsMap[x1][y].getNumber()>0)
                    {
                        if(cardsMap[x][y].getNumber()<=0)
                        {
                            cardsMap[x][y].setNumber(cardsMap[x1][y].getNumber());
                            cardsMap[x1][y].setNumber(0);
                            x++;
                            merge = true;
                        }
                        else if(cardsMap[x][y].equals(cardsMap[x1][y]))
                        {
                            cardsMap[x][y].setNumber(cardsMap[x][y].getNumber()*2);
                            cardsMap[x1][y].setNumber(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNumber());
                            updateBest();
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if(merge)
        {
            addRandomNum();
            checkComplete();
        }
    }
    private void MoveUp()
    {
        boolean merge  = false;
        MainActivity.getMainActivity().sound();
        for(int x = 0;x<4;x++)
        {
            for(int y = 0;y<4;y++)
            {
                for(int y1 = y+1;y1<4;y1++)
                {
                    if(cardsMap[x][y1].getNumber()>0)
                    {
                        if(cardsMap[x][y].getNumber()<=0)
                        {
                            cardsMap[x][y].setNumber(cardsMap[x][y1].getNumber());
                            cardsMap[x][y1].setNumber(0);
                            y--;
                            merge = true;
                        }
                        else if(cardsMap[x][y].equals(cardsMap[x][y1]))
                        {
                            cardsMap[x][y].setNumber(cardsMap[x][y].getNumber()*2);
                            cardsMap[x][y1].setNumber(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNumber());
                            updateBest();
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if(merge)
        {
            addRandomNum();
            checkComplete();
        }
    }
    private void MoveDown()
    {
        boolean merge  = false;
        MainActivity.getMainActivity().sound();

        for(int x = 0;x<4;x++)
        {
            for(int y = 3;y>=0;y--)
            {
                for(int y1 = y-1;y1>=0;y1--)
                {
                    if(cardsMap[x][y1].getNumber()>0)
                    {
                        if(cardsMap[x][y].getNumber()<=0)
                        {
                            cardsMap[x][y].setNumber(cardsMap[x][y1].getNumber());
                            cardsMap[x][y1].setNumber(0);
                            y++;
                            merge = true;
                        }
                        else if(cardsMap[x][y].equals(cardsMap[x][y1]))
                        {
                            cardsMap[x][y].setNumber(cardsMap[x][y].getNumber()*2);
                            cardsMap[x][y1].setNumber(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNumber());
                            updateBest();
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if(merge)
        {
            addRandomNum();
            checkComplete();
        }
    }
    /*获取卡片的宽高*/
    @Override
    protected void onSizeChanged(int w,int h,int oldw,int oldh)
    {
        super.onSizeChanged(w,h,oldw,oldh);
        /*计算卡牌尺寸*/
        /*为了适配各类型的手机*/
        int cardSize = (Math.min(w,h)-10)/4;
        addCards(cardSize);
        startGame();
    }
    /*添加卡片*/
    private void addCards(int cardSize){
        Card card;
        for(int y = 0;y<4;y++)
        {
            for(int x = 0;x<4;x++)
            {
                card = new Card(getContext());
                card.setNumber(2);
                addView(card,cardSize,cardSize);
                cardsMap[x][y] = card;
            }
        }
    }
    /*开始游戏*/
    public void startGame(){
        //初始化
        MainActivity.getMainActivity().clearScore();
        for(int y = 0;y<4;y++)
        {
            for(int x = 0;x<4;x++)
            {
                cardsMap[x][y].setNumber(0);
            }
        }
        //添加随机卡片
        addRandomNum();
        addRandomNum();
    }
    /*检查游戏是否结束*/
    private void checkComplete(){
        /*设置观察者*/
        boolean complete = true;

        ALL:
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (cardsMap[x][y].getNumber()==0||
                        (x>0&&cardsMap[x][y].equals(cardsMap[x-1][y]))||
                        (x<3&&cardsMap[x][y].equals(cardsMap[x+1][y]))||
                        (y>0&&cardsMap[x][y].equals(cardsMap[x][y-1]))||
                        (y<3&&cardsMap[x][y].equals(cardsMap[x][y+1]))) {

                    complete = false;
                    break ALL;
                }
            }
        }
        /*根据观察者的记录做出相应的处理*/
        if (complete) {
            new AlertDialog.Builder(getContext()).setTitle("你好").setMessage("游戏结束").setPositiveButton("重来", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startGame();
                }
            }).show();
        }

    }
    private void updateBest()
    {
        int bestscore,score;
        /*由于最高分只是一个数字存储简单，因此不需创建数据库*/
        /*只需使用Android平台上一个轻量级的存储类，用来保存应用的一些常用配置*/
        SharedPreferences sp = getContext().getSharedPreferences("game2048",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        score = MainActivity.getMainActivity().getScore();
        bestscore =     sp.getInt("best",0);
        /*如果当前分数高于以往成绩，则将新的最高分存储*/
        if(bestscore<score)
        {
            editor.putInt("best",score);
            MainActivity.getMainActivity().setBestscore(score);
            editor.commit();
        }
    }
    /*二维数组存储卡片信息*/
    private Card[][] cardsMap = new Card[4][4];
    private List<Point> emptyPoints = new ArrayList<Point>();


}
