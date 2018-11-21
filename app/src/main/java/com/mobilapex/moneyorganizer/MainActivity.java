package com.mobilapex.moneyorganizer;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by nt on 08.06.17.
 */

public class MainActivity extends AppCompatActivity {

    public class Item {
        String name;
        int price;

        Item(String name, int price) {
            this.name = name;
            this.price = price;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText price = (EditText) findViewById(R.id.price);
        final Button add = (Button) findViewById(R.id.add);
        final ListView items = (ListView) findViewById(R.id.items);
        final ItemsAdapter itemsAdapter = new ItemsAdapter();
        items.setAdapter(itemsAdapter);
        add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String nameString;
                String priceString;
                boolean emptyFields = false;
                // проверяем, заполнены ли поля ввода
                nameString = name.getText().toString();
                priceString = price.getText().toString();
                if (nameString.equals("") || priceString.equals("")) emptyFields = true;
                if (!emptyFields) {
                    Item item = new Item(nameString, Integer.valueOf(priceString));
                    itemsAdapter.add(item);
                }
            }
        });

    }

    private class ItemsAdapter extends ArrayAdapter<Item> {


        public ItemsAdapter() {
            super(MainActivity.this, R.layout.item);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final View view = getLayoutInflater().inflate(R.layout.item, null);
            final Item item = getItem(position);
            ((TextView) view.findViewById(R.id.name)).setText(item.name);
            ((TextView) view.findViewById(R.id.price)).setText(String.valueOf(item.price));
            return view;

        }
    }
    //public void onCreate()
}
