package com.example.ungdungchogiaovientiengphap;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;
public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tts = new TextToSpeech(this,this);
        Button btnRed = findViewById(R.id.btnRed);
        Button btnBlue = findViewById(R.id.btnBlue);
        Button btnGreen = findViewById(R.id.btnGreen);
        Button btnYellow = findViewById(R.id.btnYellow);
        Button btnBlack = findViewById(R.id.btnBLack);


        btnRed.setOnClickListener(v->speakColor("rouge"));
        btnBlue.setOnClickListener(v->speakColor("bleu"));
        btnGreen.setOnClickListener(v -> speakColor("vert"));
        btnYellow.setOnClickListener(v -> speakColor("jaune"));
        btnBlack.setOnClickListener(v -> speakColor("noir"));
    }
    @Override
    public void onInit(int status){
        if(status==TextToSpeech.SUCCESS){
            int result = tts.setLanguage(Locale.FRENCH);
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this, "Language French not supported!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
        }
    }
    private void speakColor(String text)
    {
        if(tts !=null)
        {
            tts.speak(text,TextToSpeech.QUEUE_FLUSH,null,"colorUtteranceId");
        }
    }
    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}