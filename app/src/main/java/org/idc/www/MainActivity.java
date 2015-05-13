package org.idc.www;



import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;


import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends ActionBarActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.

    ImageButton facebookButton,twitterButton,youtubeButton,radioButton,podcastButton;
    Button awarness,nms,social;
    WebView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        facebookButton = (ImageButton) findViewById(R.id.facebookBT);
        twitterButton = (ImageButton) findViewById(R.id.twitterBT);
        youtubeButton = (ImageButton) findViewById(R.id.youtubeBT);
        radioButton = (ImageButton) findViewById(R.id.radioBT);
        podcastButton =(ImageButton)findViewById(R.id.podcastBT);
        awarness =(Button) findViewById(R.id.awarness);
        nms = (Button) findViewById(R.id.nms);
        social = (Button)findViewById(R.id.social);
        mainView = (WebView) findViewById(R.id.main_webview);
        mainView.getSettings().setJavaScriptEnabled(true);
        mainView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                findViewById(R.id.loading).setVisibility(View.GONE);
                findViewById(R.id.main_webview).setVisibility(View.VISIBLE);
            }
        });
        mainView.loadUrl("http://idcnortheast.org/raising-awareness/");
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
        awarness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.main_webview).setVisibility(View.GONE);
                findViewById(R.id.loading).setVisibility(View.VISIBLE);
                mainView.loadUrl("http://idcnortheast.org/raising-awareness/");
                findViewById(R.id.loading).setVisibility(View.GONE);
                findViewById(R.id.main_webview).setVisibility(View.VISIBLE);
            }
        });
        nms.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                findViewById(R.id.main_webview).setVisibility(View.GONE);
                findViewById(R.id.loading).setVisibility(View.VISIBLE);
                mainView.loadUrl("http://idcnortheast.org/social-initiatives/");
                findViewById(R.id.loading).setVisibility(View.GONE);
                findViewById(R.id.main_webview).setVisibility(View.VISIBLE);

            }

        });
        social.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mainView.loadUrl("http://idcnortheast.org/social-initiatives/");
            }
        });

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
