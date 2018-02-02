package com.xwysun.ijkplayer;

import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TableLayout;

import com.xwysun.ijkplayer.media.AndroidMediaController;
import com.xwysun.ijkplayer.media.IjkVideoView;
import com.xwysun.ijkplayer.media.LiveController;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {

    IjkVideoView videoView;
    TableLayout hud_view;
    Toolbar toolbar;
    String url="rtsp://183.230.102.51:10002/stream/live/5920/10001?token=dXNlcl9uYW1lOmFkbWluDQpwYXNzd29yZDpDcXNwamtpcGMpNQ0KdXJsOnJ0c3A6Ly8xMDAuNjQuNjYuMTE6NTU0L1N0cmVhbWluZy9DaGFubmVscy8xMDE/dHJhbnNwb3J0bW9kZT11bmljYXN0JnByb2ZpbGU9UHJvZmlsZV8x";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView=(IjkVideoView) findViewById(R.id.video_view);
        hud_view=(TableLayout) findViewById(R.id.hud_view);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        videoView.setVideoURI(Uri.parse(url));
        videoView.setHudView(hud_view);
        final AndroidMediaController controller=new AndroidMediaController(this, false);
        videoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer mp) {
                controller.setAnchorView(videoView);
                videoView.setMediaController(controller);
                videoView.start();
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
        videoView.release(false);
    }


}
