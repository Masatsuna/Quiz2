package org.t_robop.masatsuna.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    int data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        data = intent.getIntExtra("DATA", 0);

        TextView textView = (TextView) findViewById(R.id.result);
        textView.setText("あなたの最終得点は" + data + "点でした。");
    }

    public void onClick(View view) {

        Intent intent = new Intent(ResultActivity.this, StartActivity.class);
        startActivity(intent);

    }

}
