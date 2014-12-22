package com.wangyang.swipelistdemo.app;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.fortysevendeg.swipelistview.SwipeListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private SwipeListView swipeListView;

    private List<String> datas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeListView = (SwipeListView) findViewById(R.id.example_lv_list);

        for (int i = 0; i < 20; i++) {
            datas.add(i + "");
        }
        DataAdapter simpleAdapter = new DataAdapter(this, datas, swipeListView);
        swipeListView.setAdapter(simpleAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class DataAdapter extends BaseAdapter {

        private List<String> mDatas;
        private LayoutInflater mInflater;
        private SwipeListView mSwipeListView;

        public DataAdapter(Context context, List<String> datas, SwipeListView swipeListView) {
            this.mDatas = datas;
            mInflater = LayoutInflater.from(context);
            mSwipeListView = swipeListView;
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = mInflater.inflate(R.layout.item_layout, null);

            TextView tv = (TextView) convertView.findViewById(R.id.id_text);
            Button del = (Button) convertView.findViewById(R.id.id_remove);
            tv.setText(mDatas.get(position));
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDatas.remove(position);
                    notifyDataSetChanged();
                    /**
                     * 关闭SwipeListView
                     * 不关闭的话，刚删除位置的item存在问题
                     * 在监听事件中onListChange中关闭，会出现问题
                     */
                    mSwipeListView.closeOpenedItems();
                }
            });

            return convertView;
        }
    }

}
