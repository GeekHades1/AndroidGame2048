package hades.org.androidgame2048;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private MyGridLayout myGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myGridLayout = (MyGridLayout) findViewById(R.id.gameview);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.restart_bt:
                myGridLayout.restartGame();
                break;
        }
    }
}
