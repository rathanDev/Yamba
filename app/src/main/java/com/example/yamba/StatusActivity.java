package com.example.yamba;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class StatusActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_status);

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
}