package com.example.yamba;

import android.app.Fragment;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;

/**
 * Created by janarthan on 12/5/16.
 */
public class StatusFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "StatusFragment";
    private EditText editTextStatus;
    private Button buttonTweet;
    private TextView textViewCount;
    private int defaultTextColor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status, container, false);
        editTextStatus = (EditText)view.findViewById(R.id.editStatus);
        buttonTweet = (Button)view.findViewById(R.id.buttonTweet);
        textViewCount = (TextView)view.findViewById(R.id.textViewCount);
        buttonTweet.setOnClickListener(this);
        defaultTextColor = textViewCount.getTextColors().getDefaultColor();
        editTextStatus.addTextChangedListener(new TextWatcher() {
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
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
        });

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        String status = editTextStatus.getText().toString();
        Log.d(TAG, "onClicked with Status: " + status);
        new PostTask().execute(status);
    }

    private final class PostTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            YambaClient yambaClient = new YambaClient("student", "password");
            try {
                yambaClient.postStatus(strings[0]);
                return "Succesfully posted";
            } catch (YambaClientException e) {
                e.printStackTrace();
                return "Failed to post to yamba service";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(StatusFragment.this.getActivity(), result, Toast.LENGTH_LONG).show();
        }
    }
}
