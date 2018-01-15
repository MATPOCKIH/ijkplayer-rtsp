package com.xwysun.ijkplayer;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import com.xwysun.ijkplayer.media.IjkVideoView;
import com.xwysun.ijkplayer.media.TableLayoutBinder;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {

    IjkVideoView videoView;
    TableLayout hud_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView=(IjkVideoView) findViewById(R.id.video_view);
        hud_view=(TableLayout) findViewById(R.id.hud_view);
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        videoView.setVideoURI(Uri.parse(""));
        videoView.setHudView(hud_view);
        videoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer mp) {
                videoView.start();
            }
        });

    }
}
