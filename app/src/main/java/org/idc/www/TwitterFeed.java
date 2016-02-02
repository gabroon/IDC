package org.idc.www;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.tweetui.*;
import com.twitter.sdk.android.tweetui.CompactTweetView;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.tweetui.LoadCallback;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

import android.widget.ImageButton;
import  android.widget.LinearLayout;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class TwitterFeed extends ActionBarActivity {
    private static final String TWITTER_KEY = "pfZLvczZByQCTmyXdnYXoZhDj";
    private static final String TWITTER_SECRET = "j0M9TBND03cUP9YhOs0OjSxXPMzucUjF6Hywe0ixdfc2a9Wj0Z";
    ListView twitterList;
    ImageButton facebookButton,twitterButton,youtubeButton,radioButton,podcastButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twitter);
        facebookButton = (ImageButton) findViewById(R.id.facebookBT);
        twitterButton = (ImageButton) findViewById(R.id.twitterBT);
        youtubeButton = (ImageButton) findViewById(R.id.youtubeBT);
        radioButton = (ImageButton) findViewById(R.id.radioBT);
        podcastButton =(ImageButton)findViewById(R.id.podcastBT);
        twitterList=(ListView) findViewById(R.id.listTwitter);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new TweetUi());
        UserTimeline userTimeline = new UserTimeline.Builder().screenName("islam_diversity").build();

        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter(this, userTimeline);
        twitterList.setAdapter(adapter);
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
