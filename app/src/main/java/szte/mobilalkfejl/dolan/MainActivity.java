package szte.mobilalkfejl.dolan;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String goob = "Gooby please ";
    String inputT;
    TextToSpeech textTSpeech;

    EditText inputText;

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        submitButton = findViewById(R.id.speak);




        textTSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textTSpeech.setLanguage(Locale.US) ;

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        submitButton.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        inputText = findViewById(R.id.textInput);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View V) {
                speak();
            }
        });

    }
    private void speak() {
        inputT = goob.concat(inputText.getText().toString());
        float speed = (float) 0.5;
        float pitch = (float) 0.1;
        textTSpeech.setPitch(pitch);
        textTSpeech.setSpeechRate(speed);
        textTSpeech.speak(inputT, TextToSpeech.QUEUE_FLUSH, null);
    }


    @Override
    protected void onDestroy() {
        if (textTSpeech != null) {
            textTSpeech.stop();
            textTSpeech.shutdown();
        }

        super.onDestroy();
    }
}
