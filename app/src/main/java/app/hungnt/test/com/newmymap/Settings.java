package app.hungnt.test.com.newmymap;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by hungnt on 1/9/17.
 */

public class Settings extends AppCompatActivity {
    ListView listView ;

    String[] values = new String[] { "Map",
            "Data consumption"
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        listView = (ListView) findViewById(R.id.listSettings);
        //-------------
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
     /*   SettingsAdapter customAdapter = new SettingsAdapter(getApplicationContext(), values);
        // Assign adapter to ListView*/
        listView.setAdapter(adapter);
        //-------------

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                int itemPosition     = position;
                String  itemValue    = (String) listView.getItemAtPosition(position);



                //for(int i=0;i<values.length;i++)
if(itemValue.equals(values[0])){
    Intent intent = new Intent(Settings.this, MapActivity.class);
    startActivity(intent);
}
            }

        });
    }
    public void show1(View v){
        Toast.makeText(Settings.this,"asd",Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_search:
                // search action
                return true;
            case R.id.action_location_found:
                // location found
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
