package org.t_robop.masatsuna.quiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class QuizActivity extends AppCompatActivity {

    private int point = 0;          //正解数

    private int number = 0;         //現在の問題数

    //問題文
    String question[] = {
            "int型は何型の変数か。",
            "無線LANの暗号化方式はどれか。",
            "技術を理解している者が企業経営を学び、技術革新をビジネスに結び付けようとする考え方はどれか。"
    };

    //選択肢
    String choise[] = {
            "整数型","小数型","文字型","真偽値型",
            "ESSID","HTTPS","POP3","WPA2",
            "JIT","MOT","OJT","TQM"
    };

    //問題の回答
    String answer[] = {
        "整数型","WPA2","MOT"
    };

    int soundId1, soundId2, soundId3, soundId4, soundId5;

    SoundPool sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        setQuestionNumber();

        setQuestion();

        setChoise();

        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

        soundId1 = sp.load(this,R.raw.correct1,1);

        soundId2 = sp.load(this,R.raw.incorrect1,1);

        soundId3 = sp.load(this,R.raw.trumpet1,1);

        soundId4 = sp.load(this,R.raw.stupid4,1);

        soundId5 = sp.load(this,R.raw.tin1,1);
    }

    //ボタンをクリックした時の処理
    public void onClick(View view) {

        if(((Button)view).getText().equals(answer[number])) {

            sp.play(soundId1, 1, 1, 0, 0, 1.0F);

            point++;

            dialog("正解です。");

        } else {

            sp.play(soundId2, 1, 1, 0, 0, 1.0F);


            dialog("不正解です。");

        }

    }

    //問題数を表示する
    public void setQuestionNumber() {

        TextView questionNumber = (TextView) findViewById(R.id.questionNumber);
        questionNumber.setText("第" + String.valueOf(number + 1) + "問");

    }

    //問題文を表示する
    public void setQuestion() {

        String setquestion = question[number];

        TextView question = (TextView) findViewById(R.id.question);
        question.setText(setquestion);

    }

    //選択肢を表示する
    public void setChoise()  {

        ArrayList list = new ArrayList();

        //配列に１〜４の数字を挿入
        for ( int i = 0; i < 4; i++ ) {
            list.add(i);
        }


        //配列の中身をシャッフル
        Collections.shuffle(list);

        for(int i = 0; i < 4; i++) {

            //配列の中身を格納
            int num = (int) list.get(i);

            String setchoise = choise[number * 4 + num];

            switch (i) {

                case 0:

                    Button button1 = (Button) findViewById(R.id.button1);
                    button1.setText(setchoise);
                    break;

                case 1:

                    Button button2 = (Button) findViewById(R.id.button2);
                    button2.setText(setchoise);
                    break;

                case 2:

                    Button button3 = (Button) findViewById(R.id.button3);
                    button3.setText(setchoise);
                    break;

                case 3:

                    Button button4 = (Button) findViewById(R.id.button4);
                    button4.setText(setchoise);
                    break;

            }


        }

    }



    public void dialog(String title) {

        //問題番号更新
        number++;

        // 確認ダイアログの生成
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);

        //ダイアログタイトル
        alertDlg.setTitle(title);

        if(number < question.length) {
            alertDlg.setPositiveButton(

                    //ボタンタイトル
                    "次の問題へ",

                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            // ボタンクリック処理
                            //問題数更新
                            setQuestionNumber();

                            //問題更新
                            setQuestion();

                            //選択肢更新
                            setChoise();

                        }
                    });
        } else {

            //最終問題でのダイアログ
            alertDlg.setPositiveButton(

                    //ボタンタイトル
                    "結果画面へ",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            // ボタンクリック処理
                            //最終ポイントによって音が変化
                            if (point > 2) {
                                sp.play(soundId3, 1, 1, 0, 0, 1.0F);
                            }
                            else if(point == 1) {
                                sp.play(soundId4, 1, 1, 0, 0, 1.0F);
                            }
                            else if(point == 0) {
                                sp.play(soundId5, 1, 1, 0, 0, 1.0F);
                            }

                            //pointを渡して画面遷移
                            Intent intent = new Intent(getApplication(), ResultActivity.class);
                            intent.putExtra("DATA", point);
                            startActivity(intent);

                        }
                    });
        }

        // 表示
        alertDlg.create().show();
    }
}
