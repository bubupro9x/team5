package com.upshift.upmap.upmap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.upshift.upmap.upmap.LocationUtil.LocationHelper;
import com.upshift.upmap.upmap.Modules.DirectionFinder;
import com.upshift.upmap.upmap.Modules.DirectionFinderListener;
import com.upshift.upmap.upmap.Modules.Route;
import com.upshift.upmap.upmap.item.item_maker_sieuthi;
import com.upshift.upmap.upmap.reline.GoogleMapsBottomSheetBehavior;

import org.lucasr.twowayview.TwoWayView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import static com.upshift.upmap.upmap.HorizontalNtbActivity.l_m_sieuthi;

public class test extends FragmentActivity implements OnMapReadyCallback, OnStreetViewPanoramaReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ActivityCompat.OnRequestPermissionsResultCallback,DirectionFinderListener,StreetViewPanorama.OnStreetViewPanoramaChangeListener {

    private GoogleMap map;
    private GoogleMapsBottomSheetBehavior behavior;
    private View parallax;
    private static LatLng SYDNEY;
    LocationHelper locationHelper;
    TextView name_l;
    TextView add_l;
    private Location mLastLocation;
    View mapView;
    ArrayList<String> l_cmt = new ArrayList<>();
    View view_header;
    View view_content;
    AutoCompleteTextView auto_search;
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;
    ArrayList<item_maker_sieuthi> list_maker = new ArrayList<>();
    ArrayList<String> _l = new ArrayList<>();
    ArrayList<String> list_adpter_cmt = new ArrayList<>();
    RelativeLayout  relativeLayout_find;
    SupportStreetViewPanoramaFragment streetViewPanoramaFragment;
    private TwoWayView listItem;
    private ArrayList<String> list_url_img = new ArrayList<>();
    private StreetViewPanorama mStreetViewPanorama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_test);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mapView = mapFragment.getView();
        streetViewPanoramaFragment = (SupportStreetViewPanoramaFragment) getSupportFragmentManager()
                .findFragmentById(R.id.parallax);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);




        final View bottomsheet = findViewById(R.id.bottomsheet);

        behavior = GoogleMapsBottomSheetBehavior.from(bottomsheet);
        parallax = findViewById(R.id.parallax);
        behavior.setParallax(parallax);

        view_header = behavior.getHeaderLayout();
        view_content = behavior.getContentLayout();
        relativeLayout_find = (RelativeLayout)findViewById(R.id.relativeLayout_find);


//        behavior.anchorView(fab);

        locationHelper = new LocationHelper(this);
        locationHelper.checkpermission();
        locationHelper.buildGoogleApiClient();

        ButterKnife.bind(this);
        add();
        for (item_maker_sieuthi l: list_maker) {
            _l.add(l.getName());
        }
        ArrayAdapter<String> _adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, _l);
        auto_search = (AutoCompleteTextView) findViewById(R.id.auto_search);
        auto_search.setThreshold(1);
        auto_search.setAdapter(_adapter);
        auto_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String name = s.toString();
                for (item_maker_sieuthi l: list_maker) {
                    if (name.equalsIgnoreCase(l.getName())){
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(l.getLat(),l.getLon()), 16));
                        auto_search.clearFocus();

                        break;
                    }
                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        SYDNEY = new LatLng(16.031954,108.226478);


        // wait for the bottomsheet to be laid out
        bottomsheet.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // set the height of the parallax to fill the gap between the anchor and the top of the screen
                CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(parallax.getMeasuredWidth(), behavior.getAnchorOffset());
                parallax.setLayoutParams(layoutParams);
                bottomsheet.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            }
        });

        behavior.setBottomSheetCallback(new GoogleMapsBottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, @GoogleMapsBottomSheetBehavior.State int newState) {
                // each time the bottomsheet changes position, animate the camera to keep the pin in view
                // normally this would be a little more complex (getting the pin location and such),
                // but for the purpose of an example this is enough to show how to stay centered on a pin

                relativeLayout_find.setVisibility(view_header.GONE);
//                mLastLocation = locationHelper.getLocation();
////                Toast.makeText(test.this,""+mLastLocation.getLatitude(),Toast.LENGTH_LONG).show();
//                map.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude())));
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    private void add() {

        Address locationAddress;
        locationAddress=locationHelper.getAddress(16.034852, 108.229214);
        String address = locationAddress.getAddressLine(0);

//        for (item_maker_sieuthi i : l_m_sieuthi) {
//            list_url_img.add(i.getImg());
//        }
//        int j=0;
//        for (item_maker_sieuthi i : l_m_sieuthi) {
//            l_cmt.add(i.getL_cmt().get(j).getName()+" : "+ i.getL_cmt().get(j).getCmt());
//            j++;
//        }

//        for (item_maker_sieuthi i : l_m_sieuthi) {
//            for(int j = 0; j<i.getImg().size();j++) {
//                list_url_img.add(i.getImg().get(j).getUrl());
//            }
//        }
      //  Toast.makeText(test.this,l_m_sieuthi.get(0).getImg().get(0).getUrl(),Toast.LENGTH_SHORT).show();
//        for (item_cmt z: l_cmt) {
//            list_adpter_cmt.add(z.getName()+" : "+z.getCmt());
//        }
//        list_maker.add(new item_maker_sieuthi(16.034852,108.229214,"Lotte",address,l_cmt));
//        locationAddress=locationHelper.getAddress(16.031954, 108.226478);
//         address = locationAddress.getAddressLine(0);
//        list_maker.add(new item_maker_sieuthi(16.031954,108.226478,"Vinmart",address));
    }

    private void init() {
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        behavior.anchorMap(map);
        // Add a marker in Sydney and move the camera
            for (item_maker_sieuthi l:l_m_sieuthi) {
                map.addMarker(new MarkerOptions().position(new LatLng(l.getLat(), l.getLon())).title(l.getName())).showInfoWindow();
            }
//        LatLng l = new LatLng(l_m_sieuthi.get(0).getLat(),l_m_sieuthi.get(0).getLon());
//        Toast.makeText(test.this,l.latitude+" - "+l.longitude,Toast.LENGTH_SHORT).show();
//        map.addMarker(new MarkerOptions().position(l).title(l_m_sieuthi.get(0).getName())).showInfoWindow();
     //       map.addMarker(new MarkerOptions().position(new LatLng(16.035132,108.229034))).showInfoWindow();
            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    final double lat = l_m_sieuthi.get(Integer.parseInt(marker.getId().substring(1))).getLat();
                    final double lon = l_m_sieuthi.get(Integer.parseInt(marker.getId().substring(1))).getLon();
                    behavior.setState(GoogleMapsBottomSheetBehavior.STATE_COLLAPSED);
                    behavior.setHideable(false);
                    map.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
                    Address locationAddress;
                    locationAddress=locationHelper.getAddress(lat, lon);
                    final String address = locationAddress.getAddressLine(0);
                    final String address1 = locationAddress.getAddressLine(1);
                    String city = locationAddress.getLocality();
                    String state = locationAddress.getAdminArea();
                    String country = locationAddress.getCountryName();
                    String postalCode = locationAddress.getPostalCode();
                    add_l = (TextView)view_header.findViewById(R.id.add_location);
                    add_l.setText(address);
                    name_l = (TextView)view_header.findViewById(R.id.name_location);
                    name_l.setText(l_m_sieuthi.get(Integer.parseInt(marker.getId().substring(1))).getName());
                    ImageButton chiduong = (ImageButton)view_content.findViewById(R.id.btnchiduong);
                    NonScrollListView lv = (NonScrollListView)view_content.findViewById(R.id.lv_cmt);
                    ArrayList<String> temp = new ArrayList<String>();
                    temp.add(l_m_sieuthi.get(Integer.parseInt(marker.getId().substring(1))).getUrl());
                    ArrayAdapter<String>adapter=new ArrayAdapter<String>
                            (test.this, android.R.layout.simple_list_item_1,temp );
                    lv.setAdapter(adapter);
                    listItem = (TwoWayView)view_content.findViewById(R.id.listview_img);
//                    ArrayList<String> temp = new ArrayList<String>();
//                    temp.add(l_m_sieuthi.get(0).getImg().get(1).getUrl());
////                    temp.add(l_m_sieuthi.get(0).getImg().get(0).getUrl());
//                    Toast.makeText(test.this,""+temp.size(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(test.this,temp.get(0).toString(),Toast.LENGTH_SHORT).show();
                    ListViewAdapter mAdapter = new ListViewAdapter(test.this,temp );
                    listItem.setAdapter(mAdapter);
                    streetViewPanoramaFragment.getStreetViewPanoramaAsync(
                            new OnStreetViewPanoramaReadyCallback() {
                                @Override
                                public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
                                    mStreetViewPanorama = panorama;
                                    mStreetViewPanorama.setOnStreetViewPanoramaChangeListener(test.this);
                                    // Only need to set the position once as the streetview fragment will maintain
                                    // its state.
                                    mStreetViewPanorama.setPosition(new LatLng(lat,lon));

                                }
                            });
                    chiduong.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            behavior.setHideable(true);
                            behavior.setState(GoogleMapsBottomSheetBehavior.STATE_HIDDEN);
                            mLastLocation = locationHelper.getLocation();
                            Address locationAddress1;
                            locationAddress1=locationHelper.getAddress(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                            String address1 = locationAddress1.getAddressLine(0);
                            sendRequest(address1,address);
                        }
                    });
                    return true;
                }
            });

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                relativeLayout_find.setVisibility(view_header.VISIBLE);
                behavior.setHideable(true);
                behavior.setState(GoogleMapsBottomSheetBehavior.STATE_HIDDEN);
            }
        });
        map.moveCamera(CameraUpdateFactory.newLatLng(SYDNEY));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);
        if (mapView != null &&
                mapView.findViewById(Integer.parseInt("1")) != null) {
            // Get the button view
            View locationButton = ((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
            // and next place it, on bottom right (as Google Maps app)
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
                    locationButton.getLayoutParams();
            // position on right bottom
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            layoutParams.setMargins(100, 0, 100, 30);
        }
    }
    private void sendRequest(String origin, String destination) {
//        String origin = etOrigin.getText().toString();
//        String destination = etDestination.getText().toString();
//        if (origin.isEmpty()) {
//            Toast.makeText(this, "Please enter origin address!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (destination.isEmpty()) {
//            Toast.makeText(this, "Please enter destination address!", Toast.LENGTH_SHORT).show();
//            return;
//        }

        try {
            new DirectionFinder(this, origin, destination).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        streetViewPanorama.setPosition(SYDNEY);
        streetViewPanorama.setUserNavigationEnabled(false);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Finding direction..!", true);

//        if (originMarkers != null) {
//            for (Marker marker : originMarkers) {
//                marker.remove();
//            }
//        }
//
//        if (destinationMarkers != null) {
//            for (Marker marker : destinationMarkers) {
//                marker.remove();
//            }
//        }

        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
//        originMarkers = new ArrayList<>();
//        destinationMarkers = new ArrayList<>();

        for (Route route : routes) {
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));
//            ((TextView) findViewById(R.id.tvDuration)).setText(route.duration.text);
//            ((TextView) findViewById(R.id.tvDistance)).setText(route.distance.text);

//            originMarkers.add(mMap.addMarker(new MarkerOptions()
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue))
//                    .title(route.startAddress)
//                    .position(route.startLocation)));
//            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green))
//                    .title(route.endAddress)
//                    .position(route.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions()
                    .geodesic(true)
                    .color(Color.BLUE)
                    .width(10);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(map.addPolyline(polylineOptions));
        }
    }
//    public void getAddress(double latitude,double longitude )
//    {
//        Address locationAddress;
//
//        locationAddress=locationHelper.getAddress(latitude,longitude);
//
//        if(locationAddress!=null)
//        {
//
//            String address = locationAddress.getAddressLine(0);
//            String address1 = locationAddress.getAddressLine(1);
//            String city = locationAddress.getLocality();
//            String state = locationAddress.getAdminArea();
//            String country = locationAddress.getCountryName();
//            String postalCode = locationAddress.getPostalCode();
//
//
//            String currentLocation;
//
//            if(!TextUtils.isEmpty(address))
//            {
//                currentLocation=address;
//
//                if (!TextUtils.isEmpty(address1))
//                    currentLocation+="\n"+address1;
//
//                if (!TextUtils.isEmpty(city))
//                {
//                    currentLocation+="\n"+city;
//
//                    if (!TextUtils.isEmpty(postalCode))
//                        currentLocation+=" - "+postalCode;
//                }
//                else
//                {
//                    if (!TextUtils.isEmpty(postalCode))
//                        currentLocation+="\n"+postalCode;
//                }
//
//                if (!TextUtils.isEmpty(state))
//                    currentLocation+="\n"+state;
//
//                if (!TextUtils.isEmpty(country))
//                    currentLocation+="\n"+country;
//
////                tvEmpty.setVisibility(View.GONE);
////                tvAddress.setText(currentLocation);
////                tvAddress.setVisibility(View.VISIBLE);
//
////                if(!btnProceed.isEnabled())
////                    btnProceed.setEnabled(true);
//            }
//
//        }
////        else
////            showToast("Something went wrong");
//    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onStreetViewPanoramaChange(StreetViewPanoramaLocation streetViewPanoramaLocation) {

    }
}
