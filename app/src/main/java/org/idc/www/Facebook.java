package org.idc.www;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import com.facebook.FacebookSdk;


public class Facebook extends ActionBarActivity {
    WebView mainView;
    ImageButton facebookButton,twitterButton,youtubeButton,radioButton,podcastButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook);
        FacebookSdk.sdkInitialize(getApplicationContext());
        facebookButton = (ImageButton) findViewById(R.id.facebookBT);
        twitterButton = (ImageButton) findViewById(R.id.twitterBT);
        youtubeButton = (ImageButton) findViewById(R.id.youtubeBT);
        radioButton = (ImageButton) findViewById(R.id.radioBT);
        podcastButton =(ImageButton)findViewById(R.id.podcastBT);

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent facebookIntent = new Intent("android.intent.action.FACEBOOK");
                startActivity(facebookIntent);
            }
        });
        youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent youTubeIntent = new Intent("android.intent.action.YOUTUBE");
                startActivity(youTubeIntent);
            }
        });

        twitterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent twitterIntent = new Intent("android.intent.action.TWITTER");
                startActivity(twitterIntent);
            }
        });
        mainView = (WebView) findViewById(R.id.face_web_view);
        mainView.getSettings().setJavaScriptEnabled(true);
        mainView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                findViewById(R.id.loading_facebook).setVisibility(View.GONE);
                findViewById(R.id.face_web_view).setVisibility(View.VISIBLE);
            }
        });
        mainView.loadUrl("https://www.facebook.com/IslamicDiversityCentre");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
