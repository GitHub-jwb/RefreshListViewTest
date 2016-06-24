package com.jwb.refreshlistviewtest.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/23.
 */
public class MyFragment extends ListFragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        String url = bundle.getString("url");
        List<String> data=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(url+"..."+i);
        }
        ArrayAdapter<String> adpt=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,data);
        setListAdapter(adpt);
    }
}
