package com.example.demofon.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.demofon.R;
import com.example.demofon.MainActivity;
import com.example.demofon.adapters.DomofonAdapter;
import com.example.demofon.models.Domofon;

import java.util.ArrayList;
import java.util.List;

public class ListOfDomofonsFragment extends Fragment {

    private MainActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.list_of_domofons_fragment,
                container, false);

        activity = (MainActivity) getActivity();

        ListView domofonsView = fragment.findViewById(R.id.list_of_domofons);

        List<Domofon> domofons = new ArrayList<>();
        domofons.add(new Domofon("Первый"));
        domofons.add(new Domofon("Второй"));
        domofons.add(new Domofon("Третий"));
        domofons.add(new Domofon("Четвертый"));
        domofons.add(new Domofon("Пятый"));

        DomofonAdapter adapter = new DomofonAdapter(getContext(), R.layout.list_of_domofons_item,
                domofons);
        domofonsView.setAdapter(adapter);

        return fragment;
    }
}
