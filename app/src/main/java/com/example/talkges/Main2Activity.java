package com.example.talkges;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Main2Activity extends Activity {

    public TextView txvResult;
    public ArrayList<String> vp = new ArrayList<>();
    public int videocounter=0;
    public VideoView video;
    public ImageButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setIcon(R.drawable.quarks);
        getActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_main2);

        txvResult = findViewById(R.id.textView1);
        video = findViewById(R.id.image);
        btn=findViewById(R.id.btnSpeak);
    }
    public void getSpeechInput(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    btn.setEnabled(false);
                    txvResult.setText(result.get(0));
                    Log.e("text",result.get(0));

                    String txt=result.get(0);
                    textProcessing(txt);
                }
                break;
        }
    }

    public void textProcessing(String txt){

        List<String> words= Arrays.asList(txt.split(" "));
        Log.e("size", String.valueOf(words.size()));

        videocounter=words.size();

        for (int i=0;i<words.size();i++) {
            Log.e("video", words.get(i));
        }

        for(int i=0;i<words.size();i++)
        {
            matching(words.get(i));
            if(vp.size()!=0)
                Log.e("path",vp.get(0));
        }

        if(vp.size()==1){
            video.setVideoURI(Uri.parse(vp.get(0)));
            video.requestFocus();
            video.start();
            videocounter--;
        }
        else if(vp.size()!=0 && videocounter!=0){
            video.setVideoURI(Uri.parse(vp.get(0)));
            video.requestFocus();
            video.start();
            vp.remove(vp.get(0));
            videocounter--;
        }


        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(vp.size()==1 && videocounter==0){
                    vp.remove(vp.get(0));
                    btn.setEnabled(true);
                }
                else if (vp.size()!=0 && videocounter!=0) {
                    video.setVideoURI(Uri.parse(vp.get(0)));
                    video.requestFocus();
                    video.start();
                    vp.remove(vp.get(0));
                    videocounter--;
                }
                else{

                    vp.clear();
                    videocounter=0;
                    video.stopPlayback();
                    btn.setEnabled(true);
                }
            }
        });

        if(vp.size()==0) {
            videocounter=0;
            video.stopPlayback();
            btn.setEnabled(true);
        }

    }

    @SuppressLint("ResourceType")
    public void matching(String word) {
        switch (word) {
            case "afternoon": {
                String videopath = "android.resource://"+getPackageName() + "/raw/afternoon";
                vp.add(videopath);

                break;
            }
            case "again": {
                String videopath = "android.resource://"+getPackageName() + "/raw/again";
                vp.add(videopath);

                break;
            }
            case "also": {
                String videopath = "android.resource://"+getPackageName() + "/raw/also";
                vp.add(videopath);

                break;
            }
            case "because": {
                String videopath = "android.resource://"+getPackageName() + "/raw/because";
                vp.add(videopath);

                break;
            }
            case "boy": {
                String videopath = "android.resource://"+getPackageName() + "/raw/boy";
                vp.add(videopath);

                break;
            }
            case "but": {
                String videopath = "android.resource://"+getPackageName() + "/raw/but";
                vp.add(videopath);

                break;
            }
            case "can": {
                String videopath = "android.resource://"+getPackageName() + "/raw/can";
                vp.add(videopath);

                break;
            }
            case "come": {
                String videopath = "android.resource://"+getPackageName() + "/raw/come";
                vp.add(videopath);

                break;
            }
            case "deaf": {
                String videopath = "android.resource://"+getPackageName() + "/raw/deaf";
                vp.add(videopath);

                break;
            }
            case "done": {
                String videopath = "android.resource://"+getPackageName() + "/raw/done";
                vp.add(videopath);

                break;
            }
            case "drink": {
                String videopath = "android.resource://"+getPackageName() + "/raw/drink";
                vp.add(videopath);

                break;
            }
            case "drinking": {
                String videopath = "android.resource://"+getPackageName() + "/raw/drink";
                vp.add(videopath);

                break;
            }
            case "drive": {
                String videopath = "android.resource://"+getPackageName() + "/raw/drive";
                vp.add(videopath);

                break;
            }
            case "driving": {
                String videopath = "android.resource://"+getPackageName() + "/raw/driving";
                vp.add(videopath);

                break;
            }
            case "eat": {
                String videopath = "android.resource://"+getPackageName() + "/raw/eat";
                vp.add(videopath);

                break;
            }
            case "excuse": {
                String videopath = "android.resource://"+getPackageName() + "/raw/excuse";
                vp.add(videopath);

                break;
            }
            case "family": {
                String videopath = "android.resource://"+getPackageName() + "/raw/family";
                vp.add(videopath);

                break;
            }
            case "father": {
                String videopath = "android.resource://"+getPackageName() + "/raw/father";
                vp.add(videopath);

                break;
            }
            case "fine": {
                String videopath = "android.resource://"+getPackageName() + "/raw/fine";
                vp.add(videopath);

                break;
            }
            case "food": {
                String videopath = "android.resource://"+getPackageName() + "/raw/food";
                vp.add(videopath);

                break;
            }
            case "friend": {
                String videopath = "android.resource://"+getPackageName() + "/raw/friend";
                vp.add(videopath);

                break;
            }
            case "get": {
                String videopath = "android.resource://"+getPackageName() + "/raw/get";
                vp.add(videopath);

                break;
            }
            case "girl": {
                String videopath = "android.resource://"+getPackageName() + "/raw/girl";
                vp.add(videopath);

                break;
            }
            case "give": {
                String videopath = "android.resource://"+getPackageName() + "/raw/give";
                vp.add(videopath);

                break;
            }
            case "good": {
                String videopath = "android.resource://"+getPackageName() + "/raw/good";
                vp.add(videopath);

                break;
            }
            case "have": {
                String videopath = "android.resource://"+getPackageName() + "/raw/have";
                vp.add(videopath);

                break;
            }
            case "having": {
                String videopath = "android.resource://"+getPackageName() + "/raw/having";
                vp.add(videopath);

                break;
            }
            case "has": {
                String videopath = "android.resource://"+getPackageName() + "/raw/have";
                vp.add(videopath);

                break;
            }
            case "he": {
                String videopath = "android.resource://"+getPackageName() + "/raw/he";
                vp.add(videopath);

                break;
            }
            case "hear": {
                String videopath = "android.resource://"+getPackageName() + "/raw/hear";
                vp.add(videopath);

                break;
            }
            case "hearing": {
                String videopath = "android.resource://"+getPackageName() + "/raw/hearing";
                vp.add(videopath);

                break;
            }
            case "hello": {
                String videopath = "android.resource://"+getPackageName() + "/raw/hello";
                vp.add(videopath);

                break;
            }
            case "help": {
                String videopath = "android.resource://"+getPackageName() + "/raw/help";
                vp.add(videopath);

                break;
            }
            case "hi": {
                String videopath = "android.resource://"+getPackageName() + "/raw/hi";
                vp.add(videopath);

                break;
            }
            case "home": {
                String videopath = "android.resource://"+getPackageName() + "/raw/home";
                vp.add(videopath);

                break;
            }
            case "know": {
                String videopath = "android.resource://"+getPackageName() + "/raw/know";
                vp.add(videopath);

                break;
            }
            case "knows": {
                String videopath = "android.resource://"+getPackageName() + "/raw/knows";
                vp.add(videopath);

                break;
            }
            case "live": {
                String videopath = "android.resource://"+getPackageName() + "/raw/live";
                vp.add(videopath);

                break;
            }
            case "man": {
                String videopath = "android.resource://"+getPackageName() + "/raw/man";
                vp.add(videopath);

                break;
            }
            case "many": {
                String videopath = "android.resource://"+getPackageName() + "/raw/many";
                vp.add(videopath);

                break;
            }
            case "me": {
                String videopath = "android.resource://"+getPackageName() + "/raw/me";
                vp.add(videopath);

                break;
            }
            case "more": {
                String videopath = "android.resource://"+getPackageName() + "/raw/more";
                vp.add(videopath);

                break;
            }
            case "morning": {
                String videopath = "android.resource://"+getPackageName() + "/raw/morning";
                vp.add(videopath);

                break;
            }
            case "my": {
                String videopath = "android.resource://"+getPackageName() + "/raw/my";
                vp.add(videopath);

                break;
            }
            case "name": {
                String videopath = "android.resource://"+getPackageName() + "/raw/name";
                vp.add(videopath);

                break;
            }
            case "need": {
                String videopath = "android.resource://"+getPackageName() + "/raw/need";
                vp.add(videopath);

                break;
            }
            case "needs": {
                String videopath = "android.resource://"+getPackageName() + "/raw/needs";
                vp.add(videopath);

                break;
            }
            case "night": {
                String videopath = "android.resource://"+getPackageName() + "/raw/night";
                vp.add(videopath);

                break;
            }
            case "no": {
                String videopath = "android.resource://"+getPackageName() + "/raw/no";
                vp.add(videopath);

                break;
            }
            case "ok": {
                String videopath = "android.resource://"+getPackageName() + "/raw/ok";
                vp.add(videopath);

                break;
            }
            case "right": {
                String videopath = "android.resource://"+getPackageName() + "/raw/right";
                vp.add(videopath);

                break;
            }
            case "she": {
                String videopath = "android.resource://"+getPackageName() + "/raw/she";
                vp.add(videopath);

                break;
            }
            default:
                String videopath = "android.resource://"+getPackageName() + "/raw/wordnotfound";
                vp.add(videopath);
                //Toast.makeText(Main2Activity.this, "Not Found", Toast.LENGTH_SHORT).show();

                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
