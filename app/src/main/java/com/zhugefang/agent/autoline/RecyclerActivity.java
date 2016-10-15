package com.zhugefang.agent.autoline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengxiakuan on 2016/10/14.
 */
public class RecyclerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RelativeLayout recycler_view_container;

    private RecyclerAdapter adapter;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_view_container = (RelativeLayout) findViewById(R.id.recycler_view_container);

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add("" + System.currentTimeMillis());
                adapter.notifyItemInserted(list.size() - 1);
            }
        });


        list.add("1111111111");
        list.add("2222222222");
        list.add("3333333333333333333333333333333333333333333333333333");
        list.add("444444444");

        MyLayoutManager layout = new MyLayoutManager();
        //必须，防止recyclerview高度为wrap时测量item高度0
        layout.setAutoMeasureEnabled(true);

        recyclerView.setLayoutManager(layout);
        adapter = new RecyclerAdapter(this, list);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new ItemClickListener(recyclerView, new ItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                list.remove(position);
                adapter.notifyItemRemoved(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }
}
