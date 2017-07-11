package com.ovationtourism.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.ovationtourism.R;
import com.ovationtourism.adapter.GVContentAdapter;
import com.ovationtourism.domain.DaPeopleBeanM;
import com.ovationtourism.ui.DaPeopleActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shkstart on 2017/6/8.
 */


public class ContentFragment extends Fragment {

    @BindView(R.id.tex)
    TextView tex;
    @BindView(R.id.gdtext)
    GridView gdtext;
    Unbinder unbinder;

    private Context context;
    private List<DaPeopleBeanM.QueryDaRenListBean> beanList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout, null);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    //*****************主要代码********************
    //接收数据
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String title = (String) getArguments().get("key");
        context = getContext();
        tex.setText(title);
        beanList = (List<DaPeopleBeanM.QueryDaRenListBean>) getArguments().get("key0");
        GVContentAdapter adapter = new GVContentAdapter(context, beanList ,getArguments().getInt("key3"));
        gdtext.setAdapter(adapter);

//        达人频道  里面的 达人页面的 GridView的点击事件
        gdtext.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent  = new Intent(context , DaPeopleActivity.class);
                intent.putExtra("iddaren", beanList .get(position + 6*getArguments().getInt("key3")).getDaRenId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
