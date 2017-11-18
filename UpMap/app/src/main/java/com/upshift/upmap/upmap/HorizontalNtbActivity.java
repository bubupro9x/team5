package com.upshift.upmap.upmap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.upshift.upmap.upmap.LocationUtil.LocationHelper;
import com.upshift.upmap.upmap.item.item_maker_sieuthi;
import com.upshift.upmap.upmap.ntb.NavigationTabBar;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by GIGAMOLE on 28.03.2016.
 */
public class HorizontalNtbActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ActivityCompat.OnRequestPermissionsResultCallback {
    private final int REQ_CODE_SPEECH_INPUT = 100;
    public static String key = null;
    LocationHelper locationHelper;
    private Location mLastLocation;

    public static ArrayList<item_maker_sieuthi> l_m_sieuthi = new ArrayList<>();


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        client = new AsyncHttpClient();
//        initImageLoader(this);
        setContentView(R.layout.menu);


        locationHelper=new LocationHelper(this);
        locationHelper.checkpermission();
        locationHelper.buildGoogleApiClient();
        loaddata();
        ButterKnife.bind(this);
   //     FirebaseDatabase database = FirebaseDatabase.getInstance();
 //       final DatabaseReference fb = database.getReference("chat").child(user_name).child("check");
     //   fb.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                 int i = dataSnapshot.getValue(Integer.class);
//                if (i==1){
//                    noti();
//
//                }
////                Toast.makeText(HorizontalNtbActivity.this,""+i,Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        initUI();
    }

    private void loaddata() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef_sieuthi = database.getReference("maker").child("sieuthi");
        myRef_sieuthi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                l_m_sieuthi.clear();
                            for (DataSnapshot post : dataSnapshot.getChildren()) {
                                item_maker_sieuthi l = post.getValue(item_maker_sieuthi.class);
//                                item_maker_sieuthi temp = new item_maker_sieuthi(l.getLat(),l.getLon(),l.getName(),l.getContent(),l.getL_cmt(),l.getImg(),l.getPhone(),l.getService());
                                l_m_sieuthi.add(l);
//                                Toast.makeText(HorizontalNtbActivity.this,""+post., Toast.LENGTH_SHORT).show();
                            }
//                l_m_sieuthi.get(0).getImg().get(0).setUrl("hello");
//                Toast.makeText(HorizontalNtbActivity.this,""+l_m_sieuthi.get(0).getImg().get(0).getUrl(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(HorizontalNtbActivity.this,""+dataSnapshot.child("1").child("img").child("1").getChildrenCount(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(HorizontalNtbActivity.this,""+dataSnapshot.child("1").getChildrenCount(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(HorizontalNtbActivity.this,""+l_m_sieuthi.size(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(HorizontalNtbActivity.this,""+l_m_sieuthi.get(0).getImg().get(0).getUrl(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(HorizontalNtbActivity.this,l_m_sieuthi.get(0).getImg().get(0).getUrl(), Toast.LENGTH_SHORT).show();
//                myRef_sieuthi.child("img").setValue("Hello, World!");
//                item_maker_sieuthi l_m_sieuthi = dataSnapshot.getValue(item_maker_sieuthi.class);
//                Toast.makeText(HorizontalNtbActivity.this,""+l_m_sieuthi.get(0).ge,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

    }


    private void initUI() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {
                final View view = LayoutInflater.from(
                        getBaseContext()).inflate(R.layout.item_vp, null, false);
                final View view1 = LayoutInflater.from(
                        getBaseContext()).inflate(R.layout.item_vp1, null, false);
                final View view2 = LayoutInflater.from(
                        getBaseContext()).inflate(R.layout.item_vp2, null, false);

//                FirebaseDatabase database = FirebaseDatabase.getInstance();


                if (position == 0) {
                    TextView btnsieuthi = (TextView)view.findViewById(R.id.btnsieuthi);
                    btnsieuthi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            key = "sieuthi";

                            Intent i = new Intent(HorizontalNtbActivity.this,test.class);
                            startActivity(i);
                            finish();

                        }
                    });

                    TextView btnnhahang = (TextView)view.findViewById(R.id.btnnhahang);
                    btnnhahang.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            key = "nhahang";
                            Intent i = new Intent(HorizontalNtbActivity.this,test.class);
                            startActivity(i);
                        }
                    });

                    TextView btncoquan = (TextView)view.findViewById(R.id.btncoquan);
                    btncoquan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            key = "coquan";
                            Intent i = new Intent(HorizontalNtbActivity.this,test.class);
                            startActivity(i);
                        }
                    });

                    TextView btnvuichoi = (TextView)view.findViewById(R.id.btnvuichoi);
                    btnvuichoi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            key = "khuvuichoi";
                            Intent i = new Intent(HorizontalNtbActivity.this,test.class);
                            startActivity(i);
                        }
                    });

                    container.addView(view);
                    return view;
                }
                if (position == 1) {

                    container.addView(view1);
                    return view1;
                }

                if (position == 2) {
                    container.addView(view2);
                    return view2;
                }



                return view;
            }
        });

        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.map),
                        Color.parseColor(colors[0]))
                        .selectedIcon(getResources().getDrawable(R.drawable.disable))
                        .title("Map")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.person),
                        Color.parseColor(colors[1]))
                        .selectedIcon(getResources().getDrawable(R.drawable.person))
                        .title("Update")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.info),
                        Color.parseColor(colors[2]))
                        .selectedIcon(getResources().getDrawable(R.drawable.info))
                        .title("Information")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }






    public boolean onKeyDown(int keyCode, KeyEvent event) {
        back();
        return true;
    }

    private void back() {
        finish();
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


    /**
     * SimsimiAPI extends AsyncTask because we can process background work
     * easily.
     */

}
