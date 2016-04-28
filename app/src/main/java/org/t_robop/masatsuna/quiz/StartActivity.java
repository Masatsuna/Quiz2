package org.t_robop.masatsuna.quiz;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    int soundId;

    SoundPool sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        soundId = sp.load(this,R.raw.question1,1);

    }

    public void onClick(View view) {

        sp.play(soundId, 1, 1, 0, 0, 1.0F);

        Intent intent = new Intent(StartActivity.this, QuizActivity.class);
        startActivity(intent);
    }
}
