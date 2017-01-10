package app.hungnt.test.com.newmymap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hungnt on 1/10/17.
 */

public class SettingsAdapter  extends BaseAdapter {
    Context applicationContext;
    String[] listItemName;
    String[] listItemValue;
    LayoutInflater inflter;

    public SettingsAdapter(Context applicationContext, String[] listItemName,String[] listItemValue) {
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
        String s=listItemName[i];
        TextView txtSettingItemName=(TextView)view.findViewById(R.id.settingName);
        txtSettingItemName.setText(listItemName[i]);
        TextView txtSettingItemValue=(TextView)view.findViewById(R.id.settingValue);
        txtSettingItemValue.setText(listItemValue[i]);
        return view;
    }
}
