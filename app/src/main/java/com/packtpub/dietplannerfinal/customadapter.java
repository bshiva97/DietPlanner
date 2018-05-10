package com.packtpub.dietplannerfinal;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by archi on 14-08-2017.
 */

public class customadapter extends ArrayAdapter {

    List myList =new ArrayList();
    public customadapter(Context context, int resource) {

        super(context,resource);
    }

    @Override
    public void add(Object object) {
        super.add(object);
        myList.add(object);
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    static class ViewHolderItem
    {
        //   ImageView i1;
        TextView t1;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolder;
        if(convertView==null){
            LayoutInflater myInflater=LayoutInflater.from(getContext());
            convertView=myInflater.inflate(R.layout.mylist,parent,false);
            viewHolder = new ViewHolderItem();
            //  viewHolder.i1=(ImageView)convertView.findViewById(R.id.imageview1);
            viewHolder.t1=(TextView)convertView.findViewById(R.id.textView1);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolderItem) convertView.getTag();
        }

        food foodInfo;
        foodInfo=(food)this.getItem(position);
        if(foodInfo!=null) {
            //  viewHolder.i1.setImageResource(foodInfo.getId());
            viewHolder.t1.setText(foodInfo.getFood_name());
            //     viewHolder.i1.setTag(position);
            viewHolder.t1.setTag(position);
        }
        return convertView;
    }
}
