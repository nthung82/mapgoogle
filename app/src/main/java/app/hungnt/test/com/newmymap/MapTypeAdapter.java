package app.hungnt.test.com.newmymap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by hungnt on 1/10/17.
 */
public class MapTypeAdapter extends BaseAdapter{
    Context applicationContext;
    String[] questionsList;
    LayoutInflater inflter;
    public  String []maptyes;

    public MapTypeAdapter(Context applicationContext, String[] questionsList) {
        this.applicationContext = applicationContext;
        this.questionsList = questionsList;
        this.maptyes=questionsList;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return maptyes.length;
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
        view = inflter.inflate(R.layout.map_type_item, null);
        String s=maptyes[i];
        RadioButton radioMapType = (RadioButton) view.findViewById(R.id.radioMapType);
        radioMapType.setText(s);
        radioMapType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(applicationContext,buttonView.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
