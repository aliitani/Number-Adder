package com.aliitani.numberadder;

import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RelativeLayout title,Layout,settingsLayout;
    GridLayout gameSettings;
    TextView timerScreen, scoreScreen, mathScreen, Square1,Square2,Square3,Square4, NumberView, titleText;
    Button startButton, diffmode, settingsButton;
    String diff = "";
    int win = 0, lose = 0, total = 0, num1 = 0, num2 = 0, timerValue = 0;
    Random NumberGen = new Random();
    Random posGen = new Random();
    CountDownTimer timer;


    public void setSettings(View view) {
        settingsLayout = (RelativeLayout) findViewById(R.id.SettingsLayout);
        title = (RelativeLayout) findViewById(R.id.TitleLayout);
        gameSettings = (GridLayout) findViewById(R.id.gameSettings);
        settingsButton = (Button) view;

        if (settingsButton.getText().toString().equals("Settings")) {
            title.setVisibility(View.GONE);
            settingsLayout.setVisibility(View.VISIBLE);
        }
        if(settingsButton.getText().toString().equals("Set difficulty")) {
            settingsButton = (Button) findViewById(R.id.difficultyGridLayout);
            settingsButton.setVisibility(View.GONE);
            if (gameSettings.getVisibility() == View.VISIBLE) {
                gameSettings.setVisibility(View.GONE);
            }else {
                gameSettings.setVisibility(View.VISIBLE);
            }
        }
        if (settingsButton.getText().toString().equals("Go Back")) {
            gameSettings.setVisibility(View.GONE);
            settingsButton = (Button) findViewById(R.id.difficultyGridLayout);
            settingsButton.setVisibility(View.VISIBLE);
            settingsLayout.setVisibility(View.GONE);
            title.setVisibility(View.VISIBLE);
        }
    }
    public void GameRules(View view){
        scoreScreen = (TextView) findViewById(R.id.scoreBoard);
        NumberView = (TextView) view;


        if (NumberView.getText().toString().equals(Integer.toString(total))) {
            win++;
            scoreScreen.setText(win + "/" + lose);
            OrderNumbers();
        } else {
            lose++;
            scoreScreen.setText(win + "/" + lose);
            OrderNumbers();
        }
    }

    public void checkRepeated() {
        Square1 = (TextView) findViewById(R.id.Square1);
        Square2 = (TextView) findViewById(R.id.Square2);
        Square3 = (TextView) findViewById(R.id.Square3);
        Square4 = (TextView) findViewById(R.id.Square4);
        String value = "";
        int intValue = 0;

        if (Square1.getText().toString().equals(Square2.getText().toString())) {
            value = Square2.getText().toString();
            intValue = 1+Integer.parseInt(value);
            Square2.setText(String.valueOf(intValue));
        } else if (Square1.getText().toString().equals(Square3.getText().toString())) {
            value = Square3.getText().toString();
            intValue = 1+Integer.parseInt(value);
            Square3.setText(String.valueOf(intValue));
        } else if (Square1.getText().toString().equals(Square4.getText().toString())) {
            value = Square4.getText().toString();
            intValue = 1+Integer.parseInt(value);
            Square4.setText(String.valueOf(intValue));
        }else if (Square2.getText().toString().equals(Square3.getText().toString())) {
            value = Square3.getText().toString();
            intValue = 1+Integer.parseInt(value);
            Square3.setText(String.valueOf(intValue));
        } else if (Square2.getText().toString().equals(Square4.getText().toString())) {
            value = Square4.getText().toString();
            intValue = 1 + Integer.parseInt(value);
            Square4.setText(String.valueOf(intValue));
        }else if (Square3.getText().toString().equals(Square4.getText().toString())) {
            value = Square4.getText().toString();
            intValue = 1+ Integer.parseInt(value);
            Square4.setText(String.valueOf(intValue));
        }

    }

    public void checkDiff(View view) {
        diffmode = (Button) view;
        System.out.println(diffmode.getText().toString());
        Layout = (RelativeLayout) findViewById(R.id.WholeLayout);

        if (diffmode.getText().toString().equals("Easy")) {
            diff = "Easy";
            System.out.println(diff);
            timerValue = 30100;
        } else if(diffmode.getText().toString().equals("Medium")) {
            diff = "Medium";
            timerValue = 40100;
        } else if(diffmode.getText().toString().equals("Hard")) {
            diff = "Hard";
            timerValue = 50100;
        }

        Snackbar snackbar = Snackbar.make(Layout, "Difficulty is set to: " + diff, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void timerFinish() {
        title = (RelativeLayout) findViewById(R.id.TitleLayout);
        startButton = (Button) findViewById(R.id.startButton);
        titleText = (TextView) findViewById(R.id.Title);
        timerScreen = (TextView) findViewById(R.id.timerBoard);
        scoreScreen = (TextView) findViewById(R.id.scoreBoard);
        mathScreen = (TextView) findViewById(R.id.mathBoard);

        title.setVisibility(View.VISIBLE);
        startButton.setText("Play Again?");
        if (win > lose) {
            titleText.setText("Great job!\n" + win + " right\n" + lose + " wrong!");
        }
        if (win == lose) {
            titleText.setText("It's tie!\n" + win + " right\n" + lose + " wrong!");
        }
        if (lose > win) {
            titleText.setText("You can do better!\n" + win + " right\n" + lose + " wrong!");
        }
        timerScreen.setText("");
        scoreScreen.setText("");
        mathScreen.setText("");
        win = 0;
        lose = 0;
        total = 0;
        num1 = 0;
        num2 = 0;

    }

    public void CountDownTimerMethod() {
        timerScreen = (TextView) findViewById(R.id.timerBoard);
        timer = new CountDownTimer(timerValue, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timerScreen.setText(Integer.toString((int)millisUntilFinished/1000) + "s");

            }
            @Override
            public void onFinish() {
                timerFinish();
            }
        }.start();
    }

    public void OrderNumbers() {
        mathScreen = (TextView) findViewById(R.id.mathBoard);
        Square1 = (TextView) findViewById(R.id.Square1);
        Square2 = (TextView) findViewById(R.id.Square2);
        Square3 = (TextView) findViewById(R.id.Square3);
        Square4 = (TextView) findViewById(R.id.Square4);

        System.out.println(diff);

        if (diff.equals("Easy")) {
            num1 = NumberGen.nextInt(10) + 1;
            num2 = NumberGen.nextInt(10) + 1;
            total = num1+num2;
        }else if (diff.equals("Medium")) {
            num1 = NumberGen.nextInt(20)+1;
            num2 = NumberGen.nextInt(20)+1;
            total = num1+num2;
        } else if (diff.equals("Hard")) {
            num1 = NumberGen.nextInt(30)+1;
            num2 = NumberGen.nextInt(30)+1;
            total = num1+num2;
        }
        System.out.println(total);
        mathScreen.setText(num1 + "+" + num2);
        int position = posGen.nextInt(4)+1;

        if(Square1.getTag().toString().equals(Integer.toString(position))) {
            Square1.setText(Integer.toString(total));
            Square2.setText(Integer.toString(NumberGen.nextInt(30)+1));
            Square3.setText(Integer.toString(NumberGen.nextInt(30)+1));
            Square4.setText(Integer.toString(NumberGen.nextInt(30)+1));
            checkRepeated();
        }else if(Square2.getTag().toString().equals(Integer.toString(position))) {
            Square2.setText(Integer.toString(total));
            Square1.setText(Integer.toString(NumberGen.nextInt(30)+1));
            Square3.setText(Integer.toString(NumberGen.nextInt(30)+1));
            Square4.setText(Integer.toString(NumberGen.nextInt(30)+1));
            checkRepeated();
        } else if(Square3.getTag().toString().equals(Integer.toString(position))) {
            Square3.setText(Integer.toString(total));
            Square1.setText(Integer.toString(NumberGen.nextInt(30)+1));
            Square2.setText(Integer.toString(NumberGen.nextInt(30)+1));
            Square4.setText(Integer.toString(NumberGen.nextInt(30)+1));
            checkRepeated();
        } else if(Square4.getTag().toString().equals(Integer.toString(position))) {
            Square4.setText(Integer.toString(total));
            Square1.setText(Integer.toString(NumberGen.nextInt(30)+1));
            Square2.setText(Integer.toString(NumberGen.nextInt(30)+1));
            Square3.setText(Integer.toString(NumberGen.nextInt(30)+1));
            checkRepeated();
        }

    }

    public void startGame(View view) {
        title = (RelativeLayout) findViewById(R.id.TitleLayout);
        timerScreen = (TextView) findViewById(R.id.timerBoard);
        scoreScreen = (TextView) findViewById(R.id.scoreBoard);
        mathScreen = (TextView) findViewById(R.id.mathBoard);
        startButton = (Button) findViewById(R.id.startButton);
        Layout = (RelativeLayout) findViewById(R.id.WholeLayout);


        if (diff.equals("")) {
            Snackbar snackbar = Snackbar.make(Layout,"Select your difficulty first!",Snackbar.LENGTH_LONG);
            snackbar.show();
        }else {
            title.setVisibility(View.GONE);
            scoreScreen.setText("0/0");
            CountDownTimerMethod();
            OrderNumbers();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
