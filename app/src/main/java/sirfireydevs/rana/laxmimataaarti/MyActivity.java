package sirfireydevs.rana.laxmimataaarti;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;


public class MyActivity extends Activity {


    MediaPlayer player;
    Player play = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (savedInstanceState == null) {
//            getFragmentManager().beginTransaction()
//                    .add(R.id.container1, new PlaceholderFragment())
//                    .commit();
//        }

        play = new Player();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    public class Player extends AsyncTask<String, Integer, String> {

        float z;

        @Override
        protected String doInBackground(String... arg0) {
            // TODO Auto-generated method stub

            player.start();

            do {

                seek.setProgress(player.getCurrentPosition());

                // seek.setProgress((int)z);

            } while (player.getCurrentPosition() < (player.getDuration()));

            // publishProgress(50);
            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            // super.onPostExecute(result);

        }

        SeekBar seek;
        int dur;

        @Override
        public void onProgressUpdate(Integer... values) {

            // SeekBar seek = (SeekBar) findViewById(R.id.seekBar1);
            // seek.setProgress(values[0]);
        }

        protected void showDur() {
            int x = player.getCurrentPosition() * 100;
            int y = player.getDuration();

            float z = x / y;
            // float q=z*100;

            Toast.makeText(MyActivity.this, String.valueOf((int) z),
                    Toast.LENGTH_SHORT).show();
            // Toast.LENGTH_LONG).show();

            // seek.setProgress((int) z);

        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            // super.onPreExecute();
            player = MediaPlayer.create(MyActivity.this, R.raw.laxmi);

            seek = (SeekBar) findViewById(R.id.seekBar1);
            seek.setMax(player.getDuration());
            seek.setProgress(0);

            seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


                @Override
                public void onStopTrackingTouch(SeekBar arg0) {
                    // TODO Auto-generated method stub
                    int n = seek.getProgress();

                    if (!player.isPlaying()) {
                        player.start();
                    }
                    player.seekTo(n);
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0) {
                    // TODO Auto-generated method stub
                    // position=player.getCurrentPosition();
                    if (play == null) {
                        play = new Player();
                        play.execute("");
                    }
                    if (!player.isPlaying()) {
                        player.start();
                    }

                }

                @Override
                public void onProgressChanged(SeekBar arg0, int arg1,
                                              boolean arg2) {
                    // TODO Auto-generated method stub

                }
            });

        }

        public void stopPlayer() {
            if (player != null) {
                player.stop();
            }
        }

        @Override
        protected void onCancelled() {
            // TODO Auto-generated method stub
            // super.onCancelled();
            player.stop();
        }

    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if (player != null) {
            player.stop();
        }

        this.pauseSong(getActionBar().getCustomView());
        System.exit(0);
        // this.recreate();
    }


    public void playSong(View vi) {

        if (play != null) {
            play.execute("");
        }
        if (!player.isPlaying()) {
            player.start();
        }
        ImageView img_play_button = (ImageView) findViewById(R.id.img_but_play);
        ImageView img_pause_button = (ImageView) findViewById(R.id.img_but_pause);
        img_play_button.setVisibility(View.GONE);
        img_pause_button.setVisibility(View.VISIBLE);
        // play.getStatus();

    }

    int i;

    public void showDur(View vi) {
        play.showDur();

        // ProgressBar sb=(ProgressBar) findViewById(R.id.seekBar1);
        // sb.setProgress(i+=5);
    }

    public void pauseSong(View vi) {

        ImageView img_play_button = (ImageView) findViewById(R.id.img_but_play);
        ImageView img_pause_button = (ImageView) findViewById(R.id.img_but_pause);
        img_play_button.setVisibility(View.VISIBLE);
        img_pause_button.setVisibility(View.GONE);
        play.stopPlayer();
        play = new Player();

    }

    public static class AdFragment extends Fragment {

        private AdView mAdView;

        public AdFragment() {
        }

        @Override
        public void onActivityCreated(Bundle bundle) {
            super.onActivityCreated(bundle);

            // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
            // values/strings.xml.
            mAdView = (AdView) getView().findViewById(R.id.adView);
            mAdView.setVisibility(View.VISIBLE);


            // Create an ad request. Check logcat output for the hashed device ID to
            // get test ads on a physical device. e.g.
            // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
            AdRequest adRequest = new AdRequest.Builder().build();


            // Start loading the ad in the background.
            mAdView.loadAd(adRequest);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_ad, container, false);
        }

        /** Called when leaving the activity */
        @Override
        public void onPause() {
            if (mAdView != null) {
                mAdView.pause();
            }
            super.onPause();
        }

        /** Called when returning to the activity */
        @Override
        public void onResume() {
            super.onResume();
            if (mAdView != null) {
                mAdView.resume();
            }
        }

        /** Called before the activity is destroyed */
        @Override
        public void onDestroy() {
            if (mAdView != null) {
                mAdView.destroy();
            }
            super.onDestroy();
        }

    }








}
