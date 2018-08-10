package com.example.lau.appnote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lau.appnote.R;
import com.example.lau.appnote.model.Note;

import java.util.List;

public class NoteAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Note> noteList;

    public NoteAdapter(Context context, int layout, List<Note> noteList) {
        this.context = context;
        this.layout = layout;
        this.noteList = noteList;
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class viewHolder{
        TextView itemTitle, itemContent, itemDateTime;
        ImageView itemIconOclok;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewHolder holder;
        if ( view == null){
            holder = new viewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.itemTitle = (TextView) view.findViewById(R.id.tv_item_title);
            holder.itemContent = (TextView) view.findViewById(R.id.tv_item_content);
            holder.itemDateTime = (TextView) view.findViewById(R.id.tv_item_date_time);
            holder.itemIconOclok = (ImageView) view.findViewById(R.id.iv_item_oclok);
            view.setTag(holder);
        } else {
           holder = (viewHolder) view.getTag();
        }

        holder.itemTitle.setText(noteList.get(i).getTitle());
        holder.itemContent.setText(noteList.get(i).getContent());
        holder.itemDateTime.setText(noteList.get(i).getDate()+" - "+noteList.get(i).getTime());
        if (noteList.get(i).getAlarmDate().equals("null")== false ||
                noteList.get(i).getAlarmTime().equals("null") == false)
        {
            holder.itemIconOclok.setVisibility(View.VISIBLE);
        }
        return view;
    }
}
