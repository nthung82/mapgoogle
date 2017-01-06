package app.hungnt.test.com.newmymap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    //**********
    DrawerLayout drawerLayout;
    AutoCompleteTextView searchEditText;
    View drawerView;
    private ListView mDrawerList;
    //**********
    private GoogleMap mMap;
    String[] languages={"Android ","java","IOS","SQL","JDBC","JDBC2","JDBC3","Web services"};

    private AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.searchEditText);
       // String[] countries = getResources().getStringArray(R.array.list_of_countries);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,languages);
        autoCompleteTextView.setAdapter(adapter);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        searchEditText=(AutoCompleteTextView)findViewById(R.id.searchEditText);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        DataModel[] drawerItem = new DataModel[3];

        drawerItem[0] = new DataModel(R.drawable.common_signin_btn_icon_normal_light, "Map");
        drawerItem[1] = new DataModel(R.drawable.common_signin_btn_icon_disabled_dark, "Settings");
        drawerItem[2] = new DataModel(R.drawable.common_signin_btn_icon_normal_dark, "Talk to friends");


        DrawerItemCustomAdapter adapter1 = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter1);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
     //   mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
       // drawerLayout.setDrawerListener(mDrawerToggle);
      //  setupDrawerToggle();
        ImageButton buttonOpenDrawer = (ImageButton)findViewById(R.id.imageButton);
        buttonOpenDrawer.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                drawerLayout.openDrawer(drawerView);
            }});
        drawerLayout   = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);
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
          //  selectItem(position);
            Toast.makeText(MapsActivity.this,"asdsad",Toast.LENGTH_LONG).show();
        }

    }
//TextView mySetting=(TextView)findViewById(R.id.setting);
   // mySetting.OnClickListener

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
        double latitude = 21.036770;
        double longitude = 105.801144;


        //------
        int height = 50;
        int width = 50;
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.mipmap.ic_launcher);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        //------
// create marker
        final MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("1").icon(BitmapDescriptorFactory.fromBitmap(smallMarker));

// adding marker
        googleMap.addMarker(marker);

        double latitude1 = 21.035280;
        double longitude1 = 105.809216;

// create marker
      final  MarkerOptions marker1= new MarkerOptions().position(new LatLng(latitude1, longitude1)).title("2").icon(BitmapDescriptorFactory.fromBitmap(smallMarker));

// adding marker
        googleMap.addMarker(marker1);
        double latitude2 = 21.035388;
        double longitude2 = 105.819272;

// create marker
      final  MarkerOptions marker2 = new MarkerOptions().position(new LatLng(latitude2, longitude2)).title("3").icon(BitmapDescriptorFactory.fromBitmap(smallMarker));

// adding marker
        googleMap.addMarker(marker2);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {

            @Override
            public boolean onMarkerClick(Marker arg0) {
                if(arg0.getTitle().equals("1")) // if marker source is clicked
                {

                    Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();
                    int height = 50;
                    int width = 50;
                    BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.mipmap.ic_launcher1);
                    Bitmap b=bitmapdraw.getBitmap();
                    Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                    marker.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                    mMap.addMarker(marker);
                    ;//.makeText(MainActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                }
                if(arg0.getTitle().equals("2")) // if marker source is clicked
                {
                    Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();
                    int height = 50;
                    int width = 50;
                    BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.mipmap.ic_launcher1);
                    Bitmap b=bitmapdraw.getBitmap();
                    Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                    marker1.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                    mMap.addMarker(marker1);

                    ;//.makeText(MainActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                }
                if (arg0.getTitle().equals("3")) // if marker source is clicked
                {
                    Toast.makeText(MapsActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();
                    int height = 50;
                    int width = 50;
                    BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.mipmap.ic_launcher1);
                    Bitmap b=bitmapdraw.getBitmap();
                    Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                    marker2.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                    mMap.addMarker(marker2);
                    //marker1.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                    ;//.makeText(MainActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                }
                return true;
            }

        });

    }
//*****
public void settings(View v){
Toast.makeText(this,"Settings",Toast.LENGTH_LONG).show();
}
    public void share(View v){
        Toast.makeText(this,"Share",Toast.LENGTH_LONG).show();

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

            //textPrompt2.setText(state);
        }};
}
