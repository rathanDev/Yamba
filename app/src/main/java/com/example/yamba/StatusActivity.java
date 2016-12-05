package com.example.yamba;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;

public class StatusActivity extends Activity implements View.OnClickListener{

    private static final String TAG = "StatusActivity";
    private EditText editTextStatus;
    private Button buttonTweet;
    private TextView textViewCount;
    private int defaultTextColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        editTextStatus = (EditText)findViewById(R.id.editTextStatus);
        buttonTweet = (Button) findViewById(R.id.buttonTweet);
        textViewCount = (TextView) findViewById(R.id.textViewCount);

        buttonTweet.setOnClickListener(this);

        defaultTextColor = textViewCount.getTextColors().getDefaultColor();
        editTextStatus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int count = 140 - editTextStatus.length();
                textViewCount.setText(Integer.toString(count));
                textViewCount.setTextColor(Color.GREEN);
                if(count<10) {
                    textViewCount.setTextColor(Color.RED);
                } else {
                    textViewCount.setTextColor(defaultTextColor);
                }
            }
        });

//        if (savedInstanceState == null) {
//            StatusFragment fragment = new StatusFragment();
//            getFragmentManager()
//                    .beginTransaction()
//                    .add(android.R.id.content, fragment, fragment.getClass()
//                            .getSimpleName())
//                    .commit();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.men);
        return true;
    }

    @Override
    public void onClick(View view) {
        String status = editTextStatus.getText().toString();
        Log.d("onClicked status: ", status);
        new PostTask().execute(status);
    }

    private final class PostTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            YambaClient yambaClient = new YambaClient("student", "password");
            try {
                yambaClient.postStatus(strings[0]);
                return "successfully posted";
            } catch (YambaClientException e) {
                e.printStackTrace();
                return "posting fail";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(StatusActivity.this, result, Toast.LENGTH_LONG).show();
        }
    }
}