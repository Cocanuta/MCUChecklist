package com.snatchybuckles.mcuchecklist.Utilities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.snatchybuckles.mcuchecklist.R;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Ben on 30/08/2017.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private LinkedHashMap<String, List<String>> expandableListDetail;

    public ExpandableListAdapter(Context context, List<String> expandableListTitle, LinkedHashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String)getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public List<String> getGroup(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition));
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition).get(0);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        if(getGroup(listPosition).get(1).equals("TV") || getGroup(listPosition).get(1).equals("Netflix") || getGroup(listPosition).get(1).equals("TVShort")) {
            listTitleTextView.setText(getGroup(listPosition).get(4) + " S0" + getGroup(listPosition).get(5) + "E" + getGroup(listPosition).get(6));
        } else {
            listTitleTextView.setText(listTitle);
        }
        LinearLayout background = (LinearLayout)convertView.findViewById(R.id.listBackground);
        if(getGroup(listPosition).get(1).equals("Movie")) {
            background.setBackgroundColor(Color.parseColor("#e5e8ff"));
        } else if(getGroup(listPosition).get(1).equals("TV")) {
            background.setBackgroundColor(Color.parseColor("#fff8e5"));
        } else if(getGroup(listPosition).get(1).equals("Netflix")) {
            background.setBackgroundColor(Color.parseColor("#ffe5e5"));
        } else if(getGroup(listPosition).get(1).equals("Short")) {
            background.setBackgroundColor(Color.parseColor("#e5fffc"));
        } else if(getGroup(listPosition).get(1).equals("TVShort")) {
            background.setBackgroundColor(Color.parseColor("#f7ffdb"));
        } else {
            background.setBackgroundColor(Color.TRANSPARENT);
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
