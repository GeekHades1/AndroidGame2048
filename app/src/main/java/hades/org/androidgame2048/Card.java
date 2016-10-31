package hades.org.androidgame2048;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Hades on 16/10/31.
 */
public class Card extends FrameLayout{

    //保存当前数字
    private int num;
    private TextView content;

    public Card(Context context) {
        super(context);
        content = new TextView(getContext());
        content.setTextSize(32);
        LayoutParams lp = new LayoutParams(-1, -1);
        lp.setMargins(10,10,0,0);
        content.setGravity(Gravity.CENTER);
        content.setBackgroundColor(0x33ffffff);
        addView(content,lp);
        setNum(0);
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        if (num == 0) {
            content.setText(" ");
        } else {
            content.setText(getNum() + "");
        }
    }


    public boolean equals(Card card) {
        return card.getNum() == getNum() ? true : false;
    }

    public void merge(Card card) {
        if (this.equals(card)) {
            setNum(card.getNum() + getNum());
        } else {
            System.out.println("not equals can't merge");
        }
    }
}
