package app.hungnt.test.com.newmymap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.provider.*;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    //**********
    DrawerLayout drawerLayout;
    //AutoCompleteTextView searchEditText;
    View drawerView;
    TextView txtInfor;
    PlacesTask placesTask;
    ParserTask parserTask;
    private ListView mDrawerList;
    List<MapInfo> list;
    List<MarkerOptions> markers = new ArrayList<>();
    //**********
    private GoogleMap mMap;
    String[] languages={"Android ","java","IOS","SQL","JDBC","JDBC2","JDBC3","Web services"};

    private AutoCompleteTextView searchEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        searchEditText=(AutoCompleteTextView)findViewById(R.id.searchEditText);
       // String[] countries = getResources().getStringArray(R.array.list_of_countries);
      /*  ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,languages);
        searchEditText.setAdapter(adapter);*/
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
      //  searchEditText=(AutoCompleteTextView)findViewById(R.id.searchEditText);
        //--------------------
        searchEditText.setThreshold(5);

        searchEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                placesTask = new PlacesTask();
                placesTask.execute(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
        //--------------------

        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        DataModel[] drawerItem = new DataModel[3];

        drawerItem[0] = new DataModel(R.drawable.common_signin_btn_icon_normal_light, "Map");
        drawerItem[1] = new DataModel(R.drawable.common_signin_btn_icon_disabled_dark, "Settings");
        drawerItem[2] = new DataModel(R.drawable.common_signin_btn_icon_normal_dark, "Talk to friends");

        DrawerItemCustomAdapter adapter1 = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter1);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        ImageButton buttonOpenDrawer = (ImageButton)findViewById(R.id.imageButton);
        buttonOpenDrawer.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                drawerLayout.openDrawer(drawerView);
            }});
        drawerLayout   = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);
        txtInfor=(TextView)findViewById(R.id.info);
        View mapView = mapFragment.getView();
        if (mapView != null && mapView.findViewById(Integer.parseInt("1")) != null) {
            // Get the button view
            View locationButton = ((View) mapView.findViewById(1).getParent()).findViewById(2);
            // and next place it, on bottom right (as Google Maps app)
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
                    locationButton.getLayoutParams();
            // position on right bottom
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            //layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            layoutParams.setMargins(10, 0, 0, 0);
        }
        //***********
        drawerLayout.setDrawerListener(myDrawerListener);

  /*
   * In my trial experiment:
   * Without dummy OnTouchListener for the drawView to
   * consume the onTouch event, touching/clicking on
   * un-handled view on drawView will pass to the view
   * under it!
   * - Touching on the Android icon will
   * trigger the TextView("http://android-er.blogspot.com/")
   * to open the web.
   */
        drawerView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                return true;
            }
        });
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(1==position)
            {
                Intent i = new Intent(MapsActivity.this, ListItemSettingActivity.class);
                startActivity(i);

            }
            else if(2==position)
            {
Ultil.share(Constant.URL,"Share infor",MapsActivity.this);
            }
            drawerLayout.closeDrawer(drawerView);
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng NewYork= new LatLng( 21.028511, 105.804817);
        CameraPosition camPos = new CameraPosition.Builder().target(NewYork).zoom(14).build();
        CameraUpdate cam = CameraUpdateFactory.newCameraPosition(camPos);
        mMap.animateCamera(cam);
        mMap.setMyLocationEnabled(true);
        //------
        int height = 50;
        int width = 50;
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.mipmap.ic_launcher);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        //------
        MarkerOptions marker2;
        list=Callwebservice.getListInfo();
        for(int i=0;i<list.size();i++){
            marker2 = new MarkerOptions().position(new LatLng(list.get(i).getLatitude(), list.get(i).getLongitude())).title(list.get(i).getInfo()).icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
            googleMap.addMarker(marker2);
            markers.add(marker2);
        }
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {

            @Override
            public boolean onMarkerClick(Marker arg0) {


                    txtInfor.setText(arg0.getTitle());
                    int height = 50;
                    int width = 50;
                for(int i=0;i<list.size();i++)
                if(list.get(i).getInfo().equals(arg0.getTitle())){
                    BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher1);
                    Bitmap b = bitmapdraw.getBitmap();
                    Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                    markers.get(i) .icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                    mMap.addMarker(markers.get(i));
                  //  Ultil.hiddenKeyboard(getParent());
                    break;
                }
                return true;
            }

        });

    }
    @Override
    public void onResume(){
        super.onResume();
        if(null!=mMap) {
            String mapType=new Ultil(this).getValueByKey(Constant.MAP_TYPE_KEY);
            //public static final String []LIST_MAP_TYPE={"Normal","Terrain","Satellite","Hybrid"};
            if("".equals(mapType)||Constant.LIST_MAP_TYPE[0].equals(mapType))
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            else if(Constant.LIST_MAP_TYPE[1].equals(mapType))
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

            else if(Constant.LIST_MAP_TYPE[2].equals(mapType))
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

            else if(Constant.LIST_MAP_TYPE[3].equals(mapType))
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
        //Toast.makeText(this,"Resume",Toast.LENGTH_SHORT).show();
    }
//*****

    public void share(View v){
        Ultil.share(Constant.URL,"Share infor",MapsActivity.this);

    }
    //**************
    DrawerLayout.DrawerListener myDrawerListener = new DrawerLayout.DrawerListener(){

        @Override
        public void onDrawerClosed(View drawerView) {
          //  textPrompt.setText("onDrawerClosed");
        }

        @Override
        public void onDrawerOpened(View drawerView) {
           // textPrompt.setText("onDrawerOpened");
        }

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
       //     textPrompt.setText("onDrawerSlide: " + String.format("%.2f", slideOffset));
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            String state;
            switch(newState){
                case DrawerLayout.STATE_IDLE:
                    state = "STATE_IDLE";
                    break;
                case DrawerLayout.STATE_DRAGGING:
                    state = "STATE_DRAGGING";
                    break;
                case DrawerLayout.STATE_SETTLING:
                    state = "STATE_SETTLING";
                    break;
                default:
                    state = "unknown!";
            }
        }};
    //-------
    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>>> {

        JSONObject jObject;

        @Override
        protected List<HashMap<String, String>> doInBackground(String... jsonData) {

            List<HashMap<String, String>> places = null;

            PlaceJSONParser placeJsonParser = new PlaceJSONParser();

            try{
                jObject = new JSONObject(jsonData[0]);

                // Getting the parsed data as a List construct
                places = placeJsonParser.parse(jObject);

            }catch(Exception e){
                //Log.d("Exception",e.toString());
            }
            return places;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> result) {

            String[] from = new String[] { "description"};
            int[] to = new int[] { android.R.id.text1 };

            // Creating a SimpleAdapter for the AutoCompleteTextView
            SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), result, android.R.layout.simple_list_item_1, from, to);

            // Setting the adapter
            searchEditText.setAdapter(adapter);
        }
    }
    //------------------
    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while( ( line = br.readLine()) != null){
                sb.append(line);
            }

            data = sb.toString();
Log.i("test",data);
            br.close();

        }catch(Exception e){
           // Log.d("Exception while downloading url", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
    //------------------
    private class PlacesTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... place) {
            // For storing data from web service
            String data = "";

            // Obtain browser key from https://code.google.com/apis/console
            String key = "key=XXX";

            String input="";

            try {
                input = "input=" + URLEncoder.encode(place[0], "utf-8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

            // place type to be searched
            String types = "types=geocode";

            // Sensor enabled
            String sensor = "sensor=false";

            // Building the parameters to the web service
            String parameters = input+"&"+types+"&"+sensor+"&"+key;

            // Output format
            String output = "json";

            // Building the url to the web service
            String url = "https://maps.googleapis.com/maps/api/place/autocomplete/"+output+"?"+parameters;

            try{
                // Fetching the data from we service
                data = downloadUrl(url);
            }catch(Exception e){
              //  Log.d("Background Task",e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Creating ParserTask
            parserTask = new ParserTask();

            // Starting Parsing the JSON string returned by Web Service
            parserTask.execute(result);
        }
    }
}
