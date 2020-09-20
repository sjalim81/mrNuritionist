package com.example.mrnuritionist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.security.AccessController.getContext;

public
class HomeActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private static ArrayList<Weight> _weights;
    private RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    CollectionReference collectionReferenceBlog = firebaseFirestore.collection("Blog");
    postAdapter adapter;
    List<BlogModel> mBlogData;
    private static final String TAG = "checked";


    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView =findViewById(R.id.recycleView_post);
        floatingActionButton = findViewById(R.id.addNewpost_floatingActionButton);
        mBlogData = new ArrayList<>();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this,AddNewPost.class);
                startActivity(intent);


            }
        });

        setUpRecyclerViewManual();
        getDataToArray();




    }

    public static Weight get_recent_weight(){
        if (_weights.size() > 0){ return _weights.get(_weights.size()-1); }
        else {return null;}
    }

    private void getDataToArray() {



        collectionReferenceBlog.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {


            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(queryDocumentSnapshots.isEmpty())
                {
                    Log.d(TAG,"there is nothing in blog database");
                }
                else
                {
                    List<BlogModel> data = queryDocumentSnapshots.toObjects(BlogModel.class);
                    adapter = new postAdapter(mBlogData );


                    recyclerView.setAdapter(adapter);
                    mBlogData.addAll(data);
                    Log.d("checked success:", "ok " + mBlogData);

                }



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "blog data load failed");
            }
        });
    }

    private void setUpRecyclerViewManual() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());

        Log.d("checked", "adapter called");
        recyclerView.setLayoutManager(layoutManager);
    }


}
