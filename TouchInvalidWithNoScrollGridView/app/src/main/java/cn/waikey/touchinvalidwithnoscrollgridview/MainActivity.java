package cn.waikey.touchinvalidwithnoscrollgridview;

import android.graphics.Canvas;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Space;
import android.widget.Spinner;

import cn.waikey.touchinvalidwithnoscrollgridview.components.DividerItemDecoration;
import cn.waikey.touchinvalidwithnoscrollgridview.components.ItemClickViewHolder;
import cn.waikey.touchinvalidwithnoscrollgridview.components.TouchInvalidGridView;

public class MainActivity extends AppCompatActivity {

    private TouchInvalidGridView mTouchInvalidGridView;
    private RecyclerView mRecyclerView;
    private ItemRecyclerAdapter adapter;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTouchInvalidGridView = (TouchInvalidGridView) findViewById(R.id.touchNoSrcollGridview);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mTouchInvalidGridView.setOnTouchInvalidPositionListener(new TouchInvalidGridView.OnTouchInvalidPositionListener() {
            @Override
            public boolean onTouchInvalidPosition(int motionEvent) {
                //item 以外的事件监听
                return false;
            }
        });
        setRecylerViewAdapter();

    }

    private void setRecylerViewAdapter() {
        adapter = new ItemRecyclerAdapter(new ItemClickViewHolder.OnItemClickListener<String>() {
            @Override
            public void onItemClick(ItemClickViewHolder<String> vh, View view, String data) {
                Snackbar.make(coordinatorLayout,"你点击了  " + data,Snackbar.LENGTH_LONG).show();
            }
        },getData());
        mRecyclerView.setAdapter(adapter);
    }

    private String[] getData(){
        String[] args = new String[40];
        for (int i = 0 ; i < 40 ; i++ ){
            args[i] = "Test \t" + i;
        }
        return args;
    }
}
