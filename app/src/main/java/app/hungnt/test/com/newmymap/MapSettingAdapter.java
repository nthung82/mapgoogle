package app.hungnt.test.com.newmymap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by hungnt on 1/10/17.
 */

public class MapSettingAdapter extends BaseAdapter {
    Context applicationContext;
    String[] listItemName;
    String[] listItemValue;
    LayoutInflater inflter;

    public MapSettingAdapter(Context applicationContext, String[] listItemName, String[] listItemValue) {
        this.applicationContext = applicationContext;
        this.listItemName = listItemName;
        this.listItemValue=listItemValue;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return listItemName.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.settings_item, null);
        if(i<2) {
            String s = listItemName[i];
            TextView txtSettingItemName = (TextView) view.findViewById(R.id.settingName);
            txtSettingItemName.setText(listItemName[i]);
            TextView txtSettingItemValue = (TextView) view.findViewById(R.id.settingValue);
            txtSettingItemValue.setText(listItemValue[i]);
            return view;
        }
        else
        {
            view = inflter.inflate(R.layout.my_location_row, null);
            CheckBox chkMylocaltion = (CheckBox) view.findViewById(R.id.checkBox);
            chkMylocaltion.setText(listItemName[i]);

           return view;
        }
    }
}
