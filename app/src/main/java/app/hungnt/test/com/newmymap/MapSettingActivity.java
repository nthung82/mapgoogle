package app.hungnt.test.com.newmymap;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hungnt on 1/10/17.
 */

public class MapSettingActivity extends  AppCompatActivity implements  MapTypeDialogFragment.OnListener{
    ListView listView ;
TextView txtItemValue;
    String[] listItemName ;

    String[]listItemValue={"1","2","3"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_settings);
        listView = (ListView) findViewById(R.id.listSettingMap);
        //-------------
        listItemName=getResources().getStringArray(R.array.list_map_settings);
        listItemValue[0]=new Ultil(this).getValueByKey(Constant.MAP_TYPE_KEY);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tToolbar);
        setSupportActionBar(toolbar);
        ImageView imageView=(ImageView)findViewById(R.id.imageButton);
        imageView.setVisibility(View.GONE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        MapSettingAdapter settingAdapter = new MapSettingAdapter(getApplicationContext(), listItemName,listItemValue);
        listView.setAdapter(settingAdapter);
        //-------------
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                TextView txtItemName=(TextView)view.findViewById(R.id.settingName);
                String itemName=txtItemName.getText().toString();
                txtItemValue=(TextView)view.findViewById(R.id.settingValue);
                    if(itemName.equals(listItemName[0])){
                        android.app.FragmentManager fm = getFragmentManager();
                        MapTypeDialogFragment dialogFragment = new MapTypeDialogFragment();
                        dialogFragment.show(fm, "dialog_maptype");
                    }
                if(position==2){
                    Toast.makeText(MapSettingActivity.this,"2342342",Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onUpdateMapTypeValue(int textId, String value) {
        txtItemValue.setText(value);
    }

    @Override
    public void hidden() {

    }
}
