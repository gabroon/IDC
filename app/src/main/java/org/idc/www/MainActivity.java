package org.idc.www;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TweetUi;
import com.twitter.sdk.android.tweetui.UserTimeline;
import java.io.IOException;
import java.io.InputStream;

import io.fabric.sdk.android.Fabric;


//TODO create the web structure and orgnaize how the butotns will be loaded
public class MainActivity extends ActionBarActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.

    private static final String TWITTER_KEY = "pfZLvczZByQCTmyXdnYXoZhDj";
    private static final String TWITTER_SECRET = "j0M9TBND03cUP9YhOs0OjSxXPMzucUjF6Hywe0ixdfc2a9Wj0Z";
    ImageButton facebookButton,twitterButton,youtubeButton,radioButton,podcastButton,donateButton;
    ImageButton vid1Button,vid2Button,vid3Button,vid4Button;
    ImageButton raiseVid1Button,raiseVid2Button,raiseVid3Button,raiseVid4Button;
    Button awarness,nms,social;
    public static ListView twitterList;
    WebView mainView;
    VideoView video_view;
    RelativeLayout fullRelativeLayout;
    RelativeLayout layoutForMainVidButtons;
    RelativeLayout layoutForRasingAwarnessVidButtons;
    TextView textReplacement;
    //menu
    String[] menu;
    String urlForVidButton1,urlForVidButton2,urlForVidButton3,urlForVidButton4;
    String urlForVid1,urlForVid2,urlForVid3,urlForVid4;
    DrawerLayout dLayout;
    ListView dList;
    ArrayAdapter<String> menuAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    ActionBar actionBar;
    Bundle bundl;
    MediaController mediaController;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        /*intailize all fields */
        fullRelativeLayout = (RelativeLayout) findViewById(R.id.fullLayout);
        facebookButton = (ImageButton) findViewById(R.id.facebookBT);
        twitterButton = (ImageButton) findViewById(R.id.twitterBT);
        youtubeButton = (ImageButton) findViewById(R.id.youtubeBT);
//        radioButton = (ImageButton) findViewById(R.id.radioBT);
//        podcastButton =(ImageButton)findViewById(R.id.podcastBT);
        donateButton =(ImageButton) findViewById(R.id.donateBT);
        video_view = (VideoView) findViewById(R.id.video_view);

        layoutForMainVidButtons = (RelativeLayout) findViewById(R.id.layoutForMainVidsButton);
        vid1Button = (ImageButton) findViewById(R.id.video1);
        vid2Button = (ImageButton) findViewById(R.id.video2);
        vid3Button = (ImageButton) findViewById(R.id.video3);
        vid4Button = (ImageButton) findViewById(R.id.video4);

        layoutForRasingAwarnessVidButtons =(RelativeLayout) findViewById(R.id.layoutForRaisingAwarness) ;
        raiseVid1Button = (ImageButton) findViewById(R.id.raiseVideo1);
        raiseVid2Button = (ImageButton) findViewById(R.id.raiseVideo2);
        raiseVid3Button = (ImageButton) findViewById(R.id.raiseVideo3);
        raiseVid4Button = (ImageButton) findViewById(R.id.raiseVideo4);

        //get video urls
        getVideoUrls();
        twitterList=(ListView) findViewById(R.id.listTwitter);
        awarness =(Button) findViewById(R.id.awarness);
        nms = (Button) findViewById(R.id.nms);
        social = (Button)findViewById(R.id.social);
        textReplacement = (TextView) findViewById(R.id.textReplacement);
        mTitle = mDrawerTitle = getTitle();
        menu = new String[]{"Home", "About Us", "Contact Us", "Join Us", "Donate"};
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        dList = (ListView) findViewById(R.id.left_drawer);
        // set a custom shadow that overlays the main content when the drawer opens
        dLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        actionBar=getSupportActionBar();
        // enable ActionBar app icon to behave as action to toggle nav drawer
        //noinspection ConstantConditions
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setIcon(R.drawable.idc_logo_launcher);
        actionBar.setLogo(R.drawable.idc_logo_launcher);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                dLayout,
                R.string.drawer_open,
                R.string.drawer_close){
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
//                getSupportActionBar().setTitle(mTitle);

                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
//                getSupportActionBar().setTitle("IDC");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()

            }
        };
        dLayout.setDrawerListener(mDrawerToggle);
        // set up the drawer's list view with items and click listener
        menuAdapter = new ArrayAdapter<String>(this, R.layout.fragment_menu, menu);
        dList.setAdapter(menuAdapter);
        dList.setSelector(android.R.color.holo_blue_bright);
        //set up the webview
        mainView = (WebView) findViewById(R.id.main_webview);
        mainView.getSettings().setJavaScriptEnabled(true);
        mainView.canGoBackOrForward(4);



        //executed while the page is loading
//        try {
//            if(isNetworkAvailable()==true){
//                mainView.setWebViewClient(new WebViewClient(){
//                    @Override
//                    public void onPageFinished(WebView view, String url) {
//                        super.onPageFinished(view, url);
//                        findViewById(R.id.loading).setVisibility(View.GONE);
//                        findViewById(R.id.main_webview).setVisibility(View.VISIBLE);
//                    }
//                });
//                mainView.loadUrl("http://idcnortheast.org/raising-awareness/");
//            }else{
//                textReplace("raising");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // set all click listners for buttons
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    if(isNetworkAvailable()){
                        hideAndShowWebView("https://www.facebook.com/IslamicDiversityCentre",mainView);
                    }else{
                        textReplace("social");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

//                Intent slide = new Intent("android.intent.action.SLIDE");
//                startActivity(slide);
            }
        });
        youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(isNetworkAvailable()){
                        hideAndShowWebView("https://www.youtube.com/user/IslamicDiversity", mainView);
                    }else{
                        textReplace("social");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

//                Intent video = new Intent("android.intent.action.VideoListActivity");
//                startActivity(video);
            }
        });

        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 /*twitter config*/
                try {
                    if(isNetworkAvailable()){
                        new appTask().execute();
                    }else{
                        textReplace("social");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(isNetworkAvailable()){
                        hideAndShowWebView("https://mydonate.bt.com/charities/idcnortheast", mainView);
                    }else{
                        textReplace("social ");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
//        radioButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent radio = new Intent("android.intent.action.RADIO");
//                startActivity(radio);
//            }
//        });
        awarness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(isNetworkAvailable()==true) {
                        hideAndShowWebView("http://idcnortheast.org/raising-awareness/", mainView);
                    }else{
                        textReplace("raising");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        nms.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    if(isNetworkAvailable()==true) {
                        hideAndShowWebView("http://idcnortheast.org/new-muslim-support/", mainView);
                    }else{
                        //use text replacement if internet is not connected
                        textReplace("nms");
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        });
        social.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    if(isNetworkAvailable()==true) {
                        hideAndShowWebView("http://idcnortheast.org/social-initiatives/", mainView);
                    }else{
                        textReplace("social_in");
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        vid1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playvideo(urlForVid1);
            }
        });

        vid2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playvideo(urlForVid2);
            }
        });

        vid3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playvideo(urlForVid3);
            }
        });

        vid4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playvideo(urlForVid4);
            }
        });

        dList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {

                dLayout.closeDrawers();
                Bundle args = new Bundle();
                args.putString("Menu", menu[position]);
                if(args.getString("Menu")=="Home"){
                    Intent mainActivity = new Intent("android.intent.action.MAIN_PAGE");
                    startActivity(mainActivity);
                }else{
                    Fragment detail = new DetailFragment();
                    detail.setArguments(args);
                    Log.w("myApp", args.getString("Menu"));
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fullLayout, detail).commit();
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (mainView.canGoBack() || mainView.isShown()) {
            mainView.goBack();
        } else if(video_view.isPlaying() || video_view.isShown()) {
            video_view.stopPlayback();
            video_view.setVisibility(View.GONE);
            layoutForMainVidButtons.setVisibility(View.VISIBLE);

        }else{
            super.onBackPressed();
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
    public void hideAllElementsAndShowLoadingImage(){
        findViewById(R.id.loading).setVisibility(View.VISIBLE);
        findViewById(R.id.listTwitter).setVisibility(View.GONE);
        findViewById(R.id.main_webview).setVisibility(View.GONE);
        findViewById(R.id.textReplacement).setVisibility(View.GONE);
    }
    public void hideLoadingAndShowMainView(){
        findViewById(R.id.loading).setVisibility(View.GONE);
        findViewById(R.id.main_webview).setVisibility(View.VISIBLE);
    }


    /*load a webpage and showing loading images*/
    public void hideAndShowWebView(String webLink,WebView webView){
        hideAllElementsAndShowLoadingImage();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideLoadingAndShowMainView();
            }
        });
        webView.loadUrl(webLink);
    }
    /*load twitter feed*/


    public boolean isNetworkAvailable() throws IOException {
        int timeout = 3000;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnected()){
//            if (InetAddress.getByName("google.com").isReachable(timeout)) {
                return true;
//            } else {
//                return false;
//            }
        }else{
            return false;
        }
    }
    public void textReplace(String type){

        if(type=="raising"){
            findViewById(R.id.loading).setVisibility(View.GONE);
            findViewById(R.id.main_webview).setVisibility(View.GONE);
            findViewById(R.id.textReplacement).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.textReplacement)).setText(R.string.raising);
        }else if(type=="nms"){
            findViewById(R.id.loading).setVisibility(View.GONE);
            findViewById(R.id.main_webview).setVisibility(View.GONE);
            findViewById(R.id.textReplacement).setVisibility(View.VISIBLE);

            ((TextView) findViewById(R.id.textReplacement)).setText(R.string.NMS);
        }else if(type=="social"){
            findViewById(R.id.loading).setVisibility(View.GONE);
            findViewById(R.id.main_webview).setVisibility(View.GONE);
            findViewById(R.id.textReplacement).setVisibility(View.VISIBLE);

            ((TextView) findViewById(R.id.textReplacement)).setText(R.string.social_no_internet);
        }else{
            findViewById(R.id.loading).setVisibility(View.GONE);
            findViewById(R.id.main_webview).setVisibility(View.GONE);
            findViewById(R.id.textReplacement).setVisibility(View.VISIBLE);

            ((TextView) findViewById(R.id.textReplacement)).setText(R.string.socialInitiatives);
        }

    }

    private class appTask extends AsyncTask<Void, Void, Void> {
        public  boolean runningLoading;
        @Override
        protected Void doInBackground(Void... params) {
            loadTwitterFeed(runningLoading);
            return null;
        }
        protected void onPreExecute() {
            runningLoading=true;
            showLoading(runningLoading);
        }
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            runningLoading=false;
            showLoading(runningLoading);


        }
    }

public void updateTwitterList(final TweetTimelineListAdapter adapter){
    this.runOnUiThread(new Runnable() {
        @Override
        public void run() {
            twitterList.setAdapter(adapter);
            //stuff that updates ui

        }
    });
}
    public void showLoading(final Boolean runningLoading){
        this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if(runningLoading==true) {
                    findViewById(R.id.textReplacement).setVisibility(View.GONE);
                    findViewById(R.id.main_webview).setVisibility(View.GONE);
                    findViewById(R.id.listTwitter).setVisibility(View.GONE);
                    findViewById(R.id.loading).setVisibility(View.VISIBLE);
                    Log.v("Loading ", ""+runningLoading);
                }else{
                    findViewById(R.id.listTwitter).setVisibility(View.VISIBLE);
                    findViewById(R.id.loading).setVisibility(View.GONE);
                    Log.v("Loading", "done" + runningLoading);
                }

            }
        });
    }
    public void loadTwitterFeed(Boolean runningLoading){
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new TweetUi());
        UserTimeline userTimeline = new UserTimeline.Builder().screenName("islam_diversity").build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter(MainActivity.this, userTimeline);
        updateTwitterList(adapter);
    }

public void playVideo(String vidID){
//startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=cxLG2wtE7TM")));
//http://zolcodes.com/videos/humanity.mp4
    MediaPlayer mp = new MediaPlayer();
    try {
        mp.setDataSource("http://zolcodes.com/videos/social.3gp");
        mp.prepare();
    }catch(Exception exp){
        Log.d("video error",exp.toString());
    }
    mp.start();
}


    public void playvideo(String videopath) {
        Log.e("entered", "playvideo");
        Log.e("path is", "" + videopath);
        try {
            progressDialog = ProgressDialog.show(this, "", "Buffering video...", false);
            progressDialog.setCancelable(true);
            getWindow().setFormat(PixelFormat.TRANSLUCENT);
            mediaController = new MediaController(this);
            Uri video = Uri.parse(videopath);
            video_view.setMediaController(mediaController);
            video_view.setVideoURI(video);
            video_view.setVisibility(View.VISIBLE);
            layoutForMainVidButtons.setVisibility(View.GONE);
            video_view.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mp) {
                    progressDialog.dismiss();
                    video_view.start();
                }

            });


        } catch (Exception e) {
            progressDialog.dismiss();
            System.out.println("Video Play Error :" + e.getMessage());
        }

    }

//


    public void getVideoUrls(){
        urlForVid1 = "http://zolcodes.com/videos/social.3gp";
        urlForVid2 = "http://zolcodes.com/videos/social.3gp";
        urlForVid3 = "http://zolcodes.com/videos/social.3gp";
        urlForVid4 = "http://zolcodes.com/videos/social.3gp";

    }




//    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
//        ImageButton vid1Button,vid2Button,vid3Button,vid4Button;
//
//        public DownloadImageTask(ImageButton vid1Button,ImageButton vid2Button,ImageButton vid3Button,ImageButton vid4Button) {
//            this.vid1Button = vid1Button;
//            this.vid2Button= vid2Button;
//            this.vid3Button= vid3Button;
//            this.vid4Button= vid4Button;
//        }
//
//
//        @Override
//        protected void onPreExecute() {
//
//        }
//
//        protected Bitmap doInBackground(String... urls) {
//            String urldisplayForButton1 = urls[0];
//            String urldisplayForButton2 = urls[1];
//            String urldisplayForButton3 = urls[2];
//            String urldisplayForButton4 = urls[3];
//            Bitmap mIcon1 = null;
//            Bitmap mIcon2 = null;
//            Bitmap mIcon3 = null;
//            Bitmap mIcon4 = null;
//            for (int i= 0;i< urls.length;i++){
//                try {
//                    InputStream in = new java.net.URL(urls[0]).openStream();
//                    switch (i){
//                        case 0:
//                            mIcon1 = BitmapFactory.decodeStream(in);
//                        case 1:
//                            mIcon2 = BitmapFactory.decodeStream(in);
//                        case 2:
//                            mIcon3 = BitmapFactory.decodeStream(in);
//                        case 3:
//                            mIcon4 = BitmapFactory.decodeStream(in);
//                    }
//
//                } catch (Exception e) {
//                    Log.e("Error", e.getMessage());
//                    e.printStackTrace();
//                }
//            }
//            vid1Button.setImageBitmap(mIcon1);
//            vid2Button.setImageBitmap(mIcon2);
//            vid3Button.setImageBitmap(mIcon3);
//            vid4Button.setImageBitmap(mIcon4);
//
//            return null;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//
//        }
//    }
//public void getNMSButtons(){
//        vid1Button.setImageResource(R.drawable.red_button);
//        vid2Button.setImageResource(R.drawable.red_button);
//        vid3Button.setImageResource(R.drawable.red_button);
//        vid4Button.setImageResource(R.drawable.red_button);
//
//    }
//
//    public void getSocialButtons(){
//        vid1Button.setImageResource(R.drawable.green_button);
//        vid2Button.setImageResource(R.drawable.green_button);
//        vid3Button.setImageResource(R.drawable.green_button);
//        vid4Button.setImageResource(R.drawable.green_button);
//
//    }
//
//    public void getIconUrls(String type){
//        DisplayMetrics dm = this.getResources().getDisplayMetrics();
//        int densityDpi = dm.densityDpi;
//        urlForVidButton1  = "http://www.zolcodes.com/"+type+"/"+densityDpi+"/vid1.png";
//        urlForVidButton2= "http://www.zolcodes.com/"+type+"/"+densityDpi+"/vid2.png";
//        urlForVidButton3 = "http://www.zolcodes.com/"+type+"/"+densityDpi+"/vid3.png";
//        urlForVidButton4 = "http://www.zolcodes.com/"+type+"/"+densityDpi+"/vid4.png";
//
//    }

}
