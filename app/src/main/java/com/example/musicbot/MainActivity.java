package com.example.musicbot;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSION_REQUEST = 1;

    List<Music> musics;
    DataAdapter adapter;
    RecyclerView recyclerView;


/*
    private Button buttonPlayStop;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;

    private final Handler handler = new Handler();
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);
        //initViews();
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST);
            }else{
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST);

            }
        }else{
            doStuff();
        }


    }
    public void doStuff (){
        recyclerView = (RecyclerView)findViewById(R.id.music_list);
        musics = new ArrayList<>();
        getMusic();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DataAdapter(this,musics);
        recyclerView.setAdapter(adapter);


        }

    public void getMusic(){
        ContentResolver contentResolver = getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = contentResolver.query(songUri,null,null,null,null);

        if(songCursor != null && songCursor.moveToFirst()){
            int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songArtist= songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);

            do {
                String currentTitle = songCursor.getString(songTitle);
                String currentArtist = songCursor.getString(songArtist);
                musics.add(new Music(currentTitle,currentArtist));
            } while (songCursor.moveToNext());
            songCursor.close();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,  int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSION_REQUEST:{
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();

                        doStuff();
                    }
                }else {
                    Toast.makeText(this,"No permission granted!",Toast.LENGTH_SHORT).show();
                    finish();
                     }
                     return;
                }
            }
        }
    }
  /*
    private void initViews() {
        buttonPlayStop = (Button) findViewById(R.id.ButtonPlayStop);
        mediaPlayer = MediaPlayer.create(this, R.raw.eminem);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                seekChange(v);
                return false;
            }
        });
    }
    private void seekChange(View v){
        if(mediaPlayer.isPlaying()){
            SeekBar sb = (SeekBar)v;
            mediaPlayer.seekTo(sb.getProgress());
        }
    }

    public void playAndStop(View v){
        if(buttonPlayStop.getText()==getString(R.string.play_str)){
            buttonPlayStop.setText(getString(R.string.pause_str));
            try {
                mediaPlayer.start();
                startPlayProgressUpdater();
            }catch (IllegalStateException e){
                mediaPlayer.pause();
            }
        }else{
            buttonPlayStop.setText(getString(R.string.play_str));
            mediaPlayer.pause();
        }
    }
    public void startPlayProgressUpdater(){
        seekBar.setProgress(mediaPlayer.getCurrentPosition());

        if(mediaPlayer.isPlaying()){
            Runnable notification = new Runnable() {
                @Override
                public void run() {
                    startPlayProgressUpdater();
                }
            };
            handler.postDelayed(notification,1000);
        }else {
            mediaPlayer.pause();
            buttonPlayStop.setText(getString(R.string.play_str));
            seekBar.setProgress(0);
        }
    }
    */

