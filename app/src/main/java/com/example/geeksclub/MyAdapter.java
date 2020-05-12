package com.example.geeksclub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Geek> {
    Context context;
    List<Geek> arraylistgeek;


    public MyAdapter(Context context, List<Geek> arraylistgeek) {
        super(context, R.layout.custom_list_item,arraylistgeek);
        this.context=context;
        this.arraylistgeek=arraylistgeek;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,  @NonNull  ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,null
        ,true);
        TextView tvid=view.findViewById(R.id.txt_id);
        TextView tvname=view.findViewById(R.id.txt_name);
        tvid.setText(arraylistgeek.get(position).getId());
        tvname.setText(arraylistgeek.get(position).getName());
        return view;
    }
}
