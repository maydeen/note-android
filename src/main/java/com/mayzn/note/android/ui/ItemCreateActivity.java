package com.mayzn.note.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.mayzn.note.android.R;
import com.mayzn.note.android.model.Item;
import com.mayzn.note.android.net.NoteRestfulService;

public class ItemCreateActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_create);

    }

    public void onClick(View view) {
        Item item = new Item();
        item.title = ((EditText)findViewById(R.id.item_create_title)).getText().toString();
        item.content = ((EditText)findViewById(R.id.item_create_content)).getText().toString();
        item.priority = Item.Priority.high;
        item.coupleId = 1l;


        restfulService.put(Item.class , item);

        Intent intent = new Intent(this , ItemListActivity.class);
        startActivity(intent);
    }
}
