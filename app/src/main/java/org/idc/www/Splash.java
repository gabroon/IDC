package org.idc.www;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by gabroon on 26/04/2015.
 */
public class Splash extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = this.getActionBar();
        actionBar.hide();
        setContentView(R.layout.spalsh);
        Thread timer = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent splash_intent = new Intent("android.intent.action.MAIN_PAGE");
                    startActivity(splash_intent);
                }
            }
        };
        timer.start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
