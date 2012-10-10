package com.mayzn.note.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.mayzn.note.android.R;

public class DevHomeActivity extends Activity {

    private static String TAG = DevHomeActivity.class.getSimpleName();

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
        setContentView(R.layout.dev_home);
    }


    public void viewItemList(View view) {
        Intent intent = new Intent(this, ItemListActivity.class);
        startActivity(intent);
    }



}

