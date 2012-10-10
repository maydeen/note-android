package com.mayzn.note.android.net;

import android.test.AndroidTestRunner;
import android.test.InstrumentationTestCase;
import com.mayzn.note.android.model.Item;
import com.mayzn.note.android.model.User;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.apache.http.HttpResponse;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(RobolectricTestRunner.class)
public class NoteRestfulServiceTest extends AndroidTestRunner {

    @Test
    public void testGet() throws Exception {
        Robolectric.addPendingHttpResponse((HttpResponse) null);
//        NoteRestfulService.list(Item.class);
    }

//    @Test
//    public void testList() throws Exception {
//
//    }
//
//    @Test
//    public void testPut() throws Exception {
//
//    }
//
//    @Test
//    public void testPost() throws Exception {
//
//    }
//
//    @Test
//    public void testDelete() throws Exception {
//
//    }
}
