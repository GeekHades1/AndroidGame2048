package hades.org.androidgame2048;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.GridLayout;

/**
 * Created by Hades on 16/10/31.
 * 自定义游戏界面布局
 */
public class MyGridLayout extends GridLayout {

    private int column;//定义好的列数
    private Card cards[][] = new Card[4][4];//存放卡片的矩阵
    public static final String TAG = "MyGridLayout";
    private int cardWidth;//卡片的长度
    private float startX,startY,offsetX, offsetY;//屏幕滑动坐标
    private boolean isFail = false;//游戏失败标志

    public MyGridLayout(Context context) {
        super(context);
    }

    public MyGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //初始化
    private void initView() {
        column = getColumnCount();
        setBackgroundColor(0xffbbada0);
        Log.i(TAG, column + " column ");
        //随机获取第一个位置坐标
        int oneX = Utils.getRandomInt(column), oneY = Utils.getRandomInt(column),
                twoX = Utils.getRandomInt(column),twoY = Utils.getRandomInt(column);
        //检测坐标是否相同
        if ((oneX == twoX) || (twoY == oneY)) {
            if (oneX == twoX) {
                if (twoX + 1 < column) {
                    twoX++;
                } else if (twoX - 1 > 0) {
                    twoX--;
                }
            } else {
                if (twoY + 1 < column) {
                    twoY++;
                } else if (twoY - 1 > 0) {
                    twoY--;
                }
            }

        }
        Log.i(TAG, "oneX = ," + oneX + " oneY = " + oneY);
        for (int y = 0; y < column;y++) {
            for (int x =0 ; x < column;x++) {
                Card card = new Card(getContext());
                addView(card, cardWidth, cardWidth);
                cards[y][x] = card;
                if ((oneX == x && oneY == y) || (twoX == x && twoY == y)) {
                    //找到初始化生成的坐标
                    card.setNum(2);
                    continue;
                }
                card.setNum(0);
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cardWidth = (Math.min(w,h) - 10 )/4;
        initView();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                offsetX = event.getX() - startX;
                offsetY = event.getY() - startY;
                if (Math.abs(offsetX) > Math.abs(offsetY)) {
                    //说明是在水平轴滑动
                    if (offsetX < -5) {
                        //左滑动
                        moveLeft();
                    } else if (offsetX > 5) {
                        //右滑动
                        moveRight();
                    }
                } else {
                    //说明是在垂直方向滑动
                    if (offsetY < -5) {
                        //上滑动
                        moveUp();
                    } else if (offsetY > 5) {
                        //下滑动
                        moveDown();
                    }
                }
        }
        return true;
    }

    private void moveDown() {
        Log.i(TAG, "down");

    }

    private void moveUp() {
        Log.i(TAG, "up");
    }

    private void moveRight() {
        Log.i(TAG, "right");

    }

    private void moveLeft() {
        Log.i(TAG, "left");
    }

    public void restartGame() {
        removeAllViews();
        initView();
    }

}
