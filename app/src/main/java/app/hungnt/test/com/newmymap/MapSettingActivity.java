package app.hungnt.test.com.newmymap;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by hungnt on 1/10/17.
 */

public class MapSettingActivity extends  AppCompatActivity implements  MaptDialogTypeFragment.OnListener{
    ListView listView ;
TextView txtItemValue;
    String[] listItemName ;//= new String[] { "Map type",
            //"Maximum visible aircraft","My location"
  //  };
    String[]listItemValue={"1","2","3"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        listView = (ListView) findViewById(R.id.listSettings);
        //-------------
        listItemName=getResources().getStringArray(R.array.list_map_settings);
        //-------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.tToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        SettingsAdapter settingAdapter = new SettingsAdapter(getApplicationContext(), listItemName,listItemValue);
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
                        MaptDialogTypeFragment dialogFragment = new MaptDialogTypeFragment ();
                        dialogFragment.show(fm, "dialog_maptype");
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
