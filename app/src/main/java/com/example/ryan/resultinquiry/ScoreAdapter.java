package com.example.ryan.resultinquiry;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import app.mrquan.pojo.Score;

/**
 * Created by ryan on 2018/11/22.
 */

public class ScoreAdapter extends ArrayAdapter<Score> {

    private Context context;
    private int resource;
    private List<Score> scores;
    public ScoreAdapter(@NonNull Context context, int resource, List<Score> scores) {
        super(context, resource,scores);
        this.context=context;
        this.resource=resource;
        this.scores=scores;
    }

    @Override
    public int getCount() {
        return scores.size();
    }

    @Nullable
    @Override
    public Score getItem(int position) {
        return scores.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(this.getContext()).inflate(this.resource,parent,false);
        }
        TextView className,name,grade;
        className=convertView.findViewById(R.id.className);
        name=convertView.findViewById(R.id.name);
        grade=convertView.findViewById(R.id.grade);

        className.setText(scores.get(position).getClassName());
        name.setText(scores.get(position).getName());
        grade.setText(scores.get(position).getGrade()+"");

        return convertView;

    }
}
