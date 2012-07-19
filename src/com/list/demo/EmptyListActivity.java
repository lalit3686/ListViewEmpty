package com.list.demo;

import java.util.ArrayList;

import com.cyrilmottier.android.listviewtipsandtricks.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class EmptyListActivity extends Activity {

    private static ArrayList<String> EMPTY = new ArrayList<String>();
    private ArrayList<String> CHEESES = new ArrayList<String>();
    private CheeseAdapter mAdapter;
    private ListView mListView;
    private ViewStub inflate_stub;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_list);

        for (int i = 0; i < 10; i++) {
        	CHEESES.add(i+""); 			
		}
       
        inflate_stub = (ViewStub) findViewById(R.id.inflate_stub);
        inflate_stub.inflate();
    	inflate_stub.setVisibility(View.GONE);
        
        mListView = (ListView) findViewById(R.id.listView);
        mAdapter = new CheeseAdapter(CHEESES);
        mListView.setAdapter(mAdapter);
    }

    public void onSetEmpty(View v) {
        mAdapter.changeData(EMPTY);
    }

    public void onSetData(View v) {
        mAdapter.changeData(CHEESES);
    }

    private class CheeseAdapter extends BaseAdapter {

        private ArrayList<String> mData;

        public CheeseAdapter(ArrayList<String> data) {
            mData = data;
        }

        public void changeData(ArrayList<String> data) {
            mData = data;
            if(mData.size() > 0){
            	inflate_stub.setVisibility(View.GONE);
            }
            else{
            	inflate_stub.setVisibility(View.VISIBLE);
            }
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public String getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.text_item, parent, false);
            }

            ((TextView) convertView).setText(getItem(position));

            return convertView;
        }
    }
}
