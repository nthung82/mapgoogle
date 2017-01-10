package app.hungnt.test.com.newmymap;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by hungnt on 1/10/17.
 */

public class MapActivity extends  AppCompatActivity{
    ListView listView ;

    String[] listItemName = new String[] { "Map type",
            "Maximum visible aircraft","My location"
    };
    String[]listItemValue={"1","2","3"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        listView = (ListView) findViewById(R.id.listSettings);
        //-------------
        SettingsAdapter settingAdapter = new SettingsAdapter(getApplicationContext(), listItemName,listItemValue);
        // Assign adapter to ListView
        listView.setAdapter(settingAdapter);
        //-------------

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                /// Show Alert
                TextView txtItemName=(TextView)view.findViewById(R.id.settingName);
                String itemName=txtItemName.getText().toString();

                    if(itemName.equals(listItemName[0])){
                        android.app.FragmentManager fm = getFragmentManager();
                        MaptDialogTypeFragment dialogFragment = new MaptDialogTypeFragment ();

                        dialogFragment.show(fm, "Sample Fragment");
                    }
            }

        });
    }
}
