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
import  android.widget.LinearLayout;

import java.util.Arrays;
import java.util.List;

public class TwitterFeed extends ListActivity {
    private static final String TWITTER_KEY = "pfZLvczZByQCTmyXdnYXoZhDj";
    private static final String TWITTER_SECRET = "j0M9TBND03cUP9YhOs0OjSxXPMzucUjF6Hywe0ixdfc2a9Wj0Z";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twitter);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new TweetUi());
        UserTimeline userTimeline = new UserTimeline.Builder().screenName("islam_diversity").build();

        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter(this, userTimeline);
        setListAdapter(adapter);
    }
}
