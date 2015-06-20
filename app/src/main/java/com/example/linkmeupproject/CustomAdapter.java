package com.example.linkmeupproject;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sean on 6/18/2015.
 */
public class CustomAdapter  extends BaseAdapter implements View.OnClickListener {
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;
    ListModel tempValues = null;
    int i =0;

    public CustomAdapter(Activity a, ArrayList d, Resources resLocal){
        activity = a;
        data=d;
        res = resLocal;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        if(data.size() <= 0) return 0;
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public static class ViewHolder{
        public TextView text;
        public TextView text1;
        public TextView textWide;
        public ImageView image;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;
        if(convertView ==null){
            vi = inflater.inflate(R.layout.tabitem, null);

            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.text);
            holder.text1 = (TextView) vi.findViewById(R.id.text1);
            vi.setTag(holder);
        }else
            holder = (ViewHolder)vi.getTag();
        if(data.size()<=0){
            holder.text.setText("No Data");
        }else{
            tempValues = null;
            tempValues = (ListModel) data.get(position);
            holder.text.setText(tempValues.getVideoName());
            holder.text1.setText(tempValues.getVideoID());

            vi.setOnClickListener(new OnItemClickListener(position));
        }
        return vi;
    }

    @Override
    public void onClick(View v) {
        Log.v("CustomAdapter", "=====Row button clicked=====");
    }
    private class OnItemClickListener implements View.OnClickListener{
        private int mPosition;
        public OnItemClickListener(int position){
            mPosition = position;
        }
        @Override
        public void onClick(View v) {
            MainActivity sct = (MainActivity)activity;
            sct.onItemClick(mPosition);
        }
    }
}
