package com.application.riccardo.robotwars;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private BluetoothCommunication blueComm;

    private JoystickTranslator trans = new JoystickTrigonometricTranslator();

    private final int SPEECH_RECOGNITION_CODE = 1;

    public String message = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Context context = getApplicationContext();
        final Activity activity = this;

        setContentView(R.layout.activity_main);

        final ImageButton bluetooth = (ImageButton) findViewById(R.id.bluetooth);
        final Switch weapon = (Switch) findViewById(R.id.weapon);
        final Switch turbo = (Switch) findViewById(R.id.turbo);
        final JoyStickView joy = (JoyStickView) findViewById(R.id.joy);

        blueComm = new BluetoothCommunication(context, activity);

        Toast.makeText(getApplicationContext(), ""+(char)(-10), Toast.LENGTH_SHORT).show();

        // Creazione dei pulsanti e elementi visivi

       turbo.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v) {

              if(trans.getTurbo() == 0)
              {
                  trans.setTurbo(1);
              }
              else
              {
                  trans.setTurbo(0);
              }

            }
        });

        weapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blueComm.weapon();
            }
        });

        bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blueComm.connection();
            }
        });

        joy.setOnJoystickMoveListener(new JoyStickView.OnJoystickMoveListener() {
            @Override
            public void onValueChanged(int angle, int power, int direction, int x, int y) {
                //blueComm.move(trans.getCharSpeed(power, angle), trans.getCharRotation(power, angle));
                //blueComm.move2(trans.getSpeed(power, angle), trans.getRotation(power, angle));
                blueComm.move(angle,power);
            }
        }, 300);

    }

}
