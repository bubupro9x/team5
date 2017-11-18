//package com.upshift.upmap.upmap;
//
//import android.app.ProgressDialog;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.FragmentActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.RelativeLayout;
//import android.widget.Toast;
//
//
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.location.LocationListener;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.Polyline;
//import com.upshift.upmap.upmap.LocationUtil.LocationHelper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.ButterKnife;
//
//public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener,GoogleApiClient.ConnectionCallbacks,
//        GoogleApiClient.OnConnectionFailedListener,ActivityCompat.OnRequestPermissionsResultCallback {
//    View mapView;
//    LocationHelper locationHelper;
//
//    private Button btnFindPath;
//    private EditText etDestination;
//    private List<Marker> originMarkers = new ArrayList<>();
//    private List<Marker> destinationMarkers = new ArrayList<>();
//    private List<Polyline> polylinePaths = new ArrayList<>();
//    private ProgressDialog progressDialog;
//    private Location mLastLocation;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps);
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//        mapView = mapFragment.getView();
//        locationHelper=new LocationHelper(this);
//        locationHelper.checkpermission();
//        locationHelper.buildGoogleApiClient();
//
//        ButterKnife.bind(this);
//        btnFindPath = (Button) findViewById(R.id.btntest);
//        btnFindPath.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                sendRequest();
//                mLastLocation=locationHelper.getLocation();
////        latitude = mLastLocation.getLatitude();
////        longitude = mLastLocation.getLongitude();
//                Toast.makeText(MapsActivity.this,""+mLastLocation.getLatitude(),Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }
////    private void sendRequest() {
////        String origin = etOrigin.getText().toString();
////        String destination = etDestination.getText().toString();
////        if (origin.isEmpty()) {
////            Toast.makeText(this, "Please enter origin address!", Toast.LENGTH_SHORT).show();
////            return;
////        }
////        if (destination.isEmpty()) {
////            Toast.makeText(this, "Please enter destination address!", Toast.LENGTH_SHORT).show();
////            return;
////        }
////
////        try {
////            new DirectionFinder(this, origin, "").execute();
////        } catch (UnsupportedEncodingException e) {
////            e.printStackTrace();
////        }
////    }
//
//    @Override
//    public void onMapReady(final GoogleMap map) {
//
//        map.getUiSettings().setZoomControlsEnabled(true);
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            return;
//        }
//        map.setMyLocationEnabled(true);
//        if (mapView != null &&
//                mapView.findViewById(Integer.parseInt("1")) != null) {
//            // Get the button view
//            View locationButton = ((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
//            // and next place it, on bottom right (as Google Maps app)
//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
//                    locationButton.getLayoutParams();
//            // position on right bottom
//            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
//            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
//            layoutParams.setMargins(100, 0, 100, 30);
//        }
////        btnHCMUStoNguyenHue = (Button) findViewById(R.id.btnHCMUStoNguyenHue);
////
////        listStep = new ArrayList<LatLng>();
////        polyline = new PolylineOptions();
////
////
////        LatLng KHTN = new LatLng(10.762643, 106.682079);
////        LatLng PhoDiBoNguyenHue = new LatLng(10.774467, 106.703274);
////
////
////        MarkerOptions option = new MarkerOptions();
////        option.position(KHTN);
////        option.title("Đại Học Khoa Học Tự Nhiên TP.HCM").snippet("Số 227 Nguyễn Văn Cừ, Quận 5");
////        option.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
////        option.alpha(0.8f);
////        option.rotation(0);
////        Marker maker = map.addMarker(option);
////        maker.showInfoWindow();
////        map.moveCamera(CameraUpdateFactory.newLatLngZoom(KHTN,17));
////
////        MarkerOptions option2 = new MarkerOptions();
////        option2.position(PhoDiBoNguyenHue);
////        option2.title("Phố Đi Bộ Nguyễn Huệ").snippet("Quận 1 , TP.HCM");
////        option2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
////        option2.alpha(0.8f);
////        option2.rotation(0);
////        Marker maker2 = map.addMarker(option2);
////
////
////        final AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
////
////            @Override
////            protected Void doInBackground(Void... params) {
////
////                // 227 Nguyễn Văn Cừ : 10.762643, 106.682079
////                // Phố đi bộ Nguyễn Huệ : 10.774467, 106.703274
////
////                String request = makeURL("16.013748","108.253323","16.050999","108.234018");
////                GetDirectionsTask task = new GetDirectionsTask(request);
////                ArrayList<LatLng> list = task.testDirection();
////                for (LatLng latLng : list) {
////                    listStep.add(latLng);
////                }
////                return null;
////            }
////            @Override
////            protected void onPostExecute(Void result) {
////                // TODO Auto-generated method stub
////                super.onPostExecute(result);
////                polyline.addAll(listStep);
////                Polyline line = map.addPolyline(polyline);
////                line.setColor(Color.BLUE);
////                line.setWidth(5);
////            }
////        };
////
////
////        btnHCMUStoNguyenHue.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////
////                task.execute();
////            }
////        });
//    }
////    public String makeURL (String sourcelat, String sourcelng, String destlat, String destlng ){
////        StringBuilder urlString = new StringBuilder();
////        urlString.append("https://maps.googleapis.com/maps/api/directions/json");
////        urlString.append("?origin=");// from
////        urlString.append(sourcelat);
////        urlString.append(",");
////        urlString.append(sourcelng);
////        urlString.append("&destination=");// to
////        urlString.append(destlat);
////        urlString.append(",");
////        urlString.append(destlng);
////        urlString.append("&key="+getResources().getString(R.string.google_api_key));
////        return urlString.toString();
////    }
////
//
//
//    @Override
//    public void onLocationChanged(Location location) {
//        int lat = (int) (location.getLatitude());
//        int lng = (int) (location.getLongitude());
//        Toast.makeText(this,""+lat,Toast.LENGTH_LONG).show();
////        latituteField.setText(String.valueOf(lat));
////        longitudeField.setText(String.valueOf(lng));
//    }
//
//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
//
//
//}
