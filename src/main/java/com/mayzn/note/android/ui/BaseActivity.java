package com.mayzn.note.android.ui;

import android.app.Activity;
import android.os.Bundle;
import com.mayzn.note.android.application.NoteApplication;
import com.mayzn.note.android.net.NoteRestfulService;

/**
 * Created with IntelliJ IDEA.
 * User: maydeen
 * Date: 12. 10. 11.
 * Time: 오전 1:03
 * To change this template use File | Settings | File Templates.
 */
public class BaseActivity extends Activity {

    protected NoteRestfulService restfulService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NoteApplication application = (NoteApplication)getApplication();
        this.restfulService = application.getRestfulService();

    }
}
