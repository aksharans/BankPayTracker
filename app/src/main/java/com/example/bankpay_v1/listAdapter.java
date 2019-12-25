package com.example.bankpay_v1;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * listAdapter class used to display accounts on the edit page
 */

public class listAdapter extends BaseAdapter implements ListAdapter {


    private ArrayList<String> list = new ArrayList<String>();
    private Context context;

    public listAdapter (ArrayList<String> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.scroll_list, null);
        }

        TextView listItemText = view.findViewById(R.id.list_text);
        listItemText.setText(list.get(position) + " [" + (position +1) + "/10]");

        return view;
    }


}

