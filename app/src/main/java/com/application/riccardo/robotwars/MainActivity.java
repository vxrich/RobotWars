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

    private VocalTranslator translator;

    private JoystickTranslator trans = new JoystickTrigonometricTranslator();

    private final int SPEECH_RECOGNITION_CODE = 1;

    public String message = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Context context = getApplicationContext();
        final Activity activity = this;

        final ImageButton bluetooth = (ImageButton) findViewById(R.id.bluetooth);
        final ImageButton vocal = (ImageButton) findViewById(R.id.vocal);
        final Switch weapon = (Switch) findViewById(R.id.lama_dx);
        final Switch turbo = (Switch) findViewById(R.id.turbo);
        final JoyStickView joy = (JoyStickView) findViewById(R.id.joy);

        blueComm = new BluetoothCommunication(context, activity);
        translator  = new VocalTranslator(blueComm);

        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(), ""+(char)(-10), Toast.LENGTH_SHORT).show();

        // Creazione dei pulsanti e elementi visivi

       turbo.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v) {
                blueComm.turbo();
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

        vocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpeechToText();
            }
        });

        joy.setOnJoystickMoveListener(new JoyStickView.OnJoystickMoveListener() {
            @Override
            public void onValueChanged(int angle, int power, int direction) {
                blueComm.move(trans.getCharSpeed(power, angle), trans.getCharRotation(power, angle));
            }
        }, 100);

    }

    //Metodo per l'apertura degli intent dei comandi vocali
    private void startSpeechToText()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Sono in ascolto...");
        try {
            startActivityForResult(intent, SPEECH_RECOGNITION_CODE);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Mi dispiace! Il riconoscimento vocale non e' supportato dal tuo dispositivo.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo per il riconoscimento del parlato e conversione in stringa
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SPEECH_RECOGNITION_CODE: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    message = result.get(0);
                    translator.translate(message);
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }


}
