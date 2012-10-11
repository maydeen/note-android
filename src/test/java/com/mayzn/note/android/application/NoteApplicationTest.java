package com.mayzn.note.android.application;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created with IntelliJ IDEA.
 * User: maydeen
 * Date: 12. 10. 11.
 * Time: 오전 1:32
 * To change this template use File | Settings | File Templates.
 */

@RunWith(RobolectricTestRunner.class)
public class NoteApplicationTest {

    @Test
    public void testOnCreate() throws Exception {
        NoteApplication application = new NoteApplication();
        application.onCreate();
    }
}
