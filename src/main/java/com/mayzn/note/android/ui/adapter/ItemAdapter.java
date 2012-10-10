package com.mayzn.note.android.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mayzn.note.android.R;
import com.mayzn.note.android.model.Item;
import com.mayzn.note.android.ui.ItemViewActivity;
import com.mayzn.note.android.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    private static final String TAG = ItemAdapter.class.getSimpleName();

    private int textViewResourceId;
    private List<Item> items;
    private final Context context;


    public ItemAdapter(Context context, int textViewResourceId, List<Item> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.textViewResourceId = textViewResourceId;
        this.items = objects;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LogUtils.LOGI(LogUtils.makeLogTag(TAG), "items size " + items.size());

        final Item item = items.get(position);

        if(view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.single_list_item, parent , false);
        }

        TextView content = (TextView)view.findViewById(R.id.item_content);
        content.setText(item.content);
        TextView title = (TextView)view.findViewById(R.id.item_title);
        title.setText(item.title);
        ImageView priority = (ImageView)view.findViewById(R.id.item_proiroity);

        switch (item.priority) {
            case high:
                priority.setBackgroundColor(Color.RED);
                break;
            case medium:
                priority.setBackgroundColor(Color.BLUE);
                break;
            case low:
                priority.setBackgroundColor(Color.GRAY);
                break;
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , ItemViewActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("item.id" , item.id);
                context.startActivity(intent);
            }
        });

        return view;
    }
}
