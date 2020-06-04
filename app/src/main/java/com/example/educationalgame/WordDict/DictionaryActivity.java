package com.example.educationalgame.WordDict;

import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.educationalgame.R;


public class DictionaryActivity extends AppCompatActivity {

    private String url;
    private TextView showDef;
    private EditText enterWord;
    private Button clearbtn;
    private Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

    }

    @Override
    protected void onStart() {
        super.onStart();
        enterWord = findViewById(R.id.search_dict);
        search = findViewById(R.id.find_btn);
        showDef = findViewById(R.id.showMeaning);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestOnClick(v);
            }
        });
    }

    private String dictionaryEntries() {
        final String language = "en-gb";
        final String word = enterWord.getText().toString();
        final String fields = "definitions";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }

    public void sendRequestOnClick(View v) {
        DictionaryRequest dR = new DictionaryRequest(this, showDef);
        url = dictionaryEntries();
        dR.execute(url);
    }

}