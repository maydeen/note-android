package com.mayzn.note.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.mayzn.note.android.R;
import com.mayzn.note.android.model.Item;
import com.mayzn.note.android.net.NoteRestfulService;

public class ItemViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_view);

        long itemId = getIntent().getLongExtra("item.id" , -1);

        Item item = restfulService.get(Item.class , itemId);

        TextView title = (TextView)findViewById(R.id.item_view_title);
        title.setText(item.title);
        TextView content = (TextView)findViewById(R.id.item_view_content);
        content.setText(item.content);

    }


}
