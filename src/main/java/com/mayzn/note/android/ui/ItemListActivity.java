package com.mayzn.note.android.ui;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.mayzn.note.android.R;
import com.mayzn.note.android.model.Item;
import com.mayzn.note.android.net.NoteRestfulService;
import com.mayzn.note.android.ui.adapter.ItemAdapter;
import com.mayzn.note.android.utils.LogUtils;
import org.codehaus.jackson.type.TypeReference;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maydeen
 * Date: 12. 10. 8.
 * Time: 오후 11:43
 * To change this template use File | Settings | File Templates.
 */
public class ItemListActivity extends Activity {

    private static final String TAG = ItemListActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.item_list);

        List<Item> items = NoteRestfulService.list(Item.class , new TypeReference<List<Item>>() {});
        LogUtils.LOGI(LogUtils.makeLogTag(TAG), "items :" + items.toString());

        ListView itemsView = (ListView)findViewById(R.id.itemList_data);

        ItemAdapter itemAdapter = new ItemAdapter(this , R.layout.single_list_item , items);

        itemsView.setAdapter(itemAdapter);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this , ItemCreateActivity.class);
        startActivity(intent);
    }
}

