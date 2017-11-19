package com.upshift.upmap.upmap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.upshift.upmap.upmap.item.item_maker_sieuthi;

import java.util.ArrayList;

/**
 * Created by PC on 11/4/2017.
 */

public class LogActivity extends Activity {
    Button login;
    private ProgressDialog progressDialog;
    public static ArrayList<item_maker_sieuthi> l_m_sieuthi = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
//        loaddata();
        login = (Button)findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                progressDialog = ProgressDialog.show(LogActivity.this, "Please wait.",
//                        "Loading data..!", true);
                loaddata();

//                progressDialog.dismiss();
                startActivity( new Intent(LogActivity.this, HorizontalNtbActivity.class));
             //  finish();
            }
        });
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

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

    }
}