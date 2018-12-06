package com.example.demofon.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.demofon.R;
import com.example.demofon.models.Domofon;

import java.util.List;

public class DomofonAdapter extends ArrayAdapter<Domofon> {

    private LayoutInflater inflater;
    private List<Domofon> domofons;
    private int layout;

    public DomofonAdapter(@NonNull Context context, int resource, @NonNull List<Domofon> domofons) {
        super(context, resource, domofons);

        this.inflater = LayoutInflater.from(context);
        this.domofons = domofons;
        this.layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null)
            convertView = inflater.inflate(layout, parent, false);

        TextView title             = convertView.findViewById(R.id.title_domofon);
        TextView buttonOpenDomofon = convertView.findViewById(R.id.button_open_domofon);

        Domofon domofon = domofons.get(position);

        title.setText(domofon.getName());
        buttonOpenDomofon.setOnClickListener(v -> {

        });

        return convertView;
    }

    @Override
    public int getCount() {
        return domofons.size();
    }
}
