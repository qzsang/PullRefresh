package com.qzsang.pullrefreshdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.qzsang.pullrefreshlib.PullRefreshLayout;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PullRefreshLayout refresh;
    private RecycleViewAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        refresh = (PullRefreshLayout) findViewById(R.id.prl_refresh);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new RecycleViewAdapter(this);
        mRecyclerView.setAdapter(listAdapter);

        //模拟刷新
        refresh.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void refresh() {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);//模拟延迟
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();
                                refresh.finishRefresh();//刷新完成
                                listAdapter.size += 2;
                                listAdapter.notifyDataSetChanged();
                            }
                        });

                    }
                }).start();

            }
        });
    }
}
