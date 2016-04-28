package cn.waikey.touchinvalidwithnoscrollgridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private TouchInvalidGridView mTouchInvalidGridView;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTouchInvalidGridView = (TouchInvalidGridView) findViewById(R.id.touchNoSrcollGridview);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mTouchInvalidGridView.setOnTouchInvalidPositionListener(new TouchInvalidGridView.OnTouchInvalidPositionListener() {
            @Override
            public boolean onTouchInvalidPosition(int motionEvent) {
                //item 以外的事件监听
                return false;
            }
        });

    }
}
