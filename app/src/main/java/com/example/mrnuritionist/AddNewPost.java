package com.example.mrnuritionist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


public class AddNewPost extends AppCompatActivity {

    private static final String TAG = "checked";
    TextView userNameTextView;
    EditText postEditText;
    ImageButton addPhotoImageButton;

    ImageView imageView;

    Button postButton;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    CollectionReference collectionReferenceBlog = firebaseFirestore.collection("Blog");
    CollectionReference collectionReferenceUsers = firebaseFirestore.collection("Users");
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String userName;

    private final int REQUEST_CODE_PERMISSIONS = 1;

    private final int REQUEST_CODE_READ_STORAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post);

        userNameTextView = findViewById(R.id.userName_textView);
        postEditText  = findViewById(R.id.post_editText);

        postButton = findViewById(R.id.post_button);
        imageView = findViewById(R.id.imageView_user);

        collectionReferenceUsers.document(mAuth.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                UserModel user = documentSnapshot.toObject(UserModel.class);

                userName = user.getName();
                userNameTextView.setText(userName);
            }
        });
        StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://mr-nuitritionist-chat.appspot.com/").child("user/"+mAuth.getUid()+".jpg");

        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
                // Pass it to Picasso to download, show in ImageView and caching
                Picasso.get().load(uri.toString())
                        .into(imageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.d("checked","loader failed");
            }
        });



        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(postEditText.getText()==null)
                {
                    postEditText.setError("Field is empty!!");
                }
                else
                {
                    BlogModel data = new BlogModel();
                    data.setName(userName);


                    DateFormat parseFormat = new SimpleDateFormat(
                            "EEE, d MMM yyyy HH:mm");
                    Date currentTime = Calendar.getInstance().getTime();
                    String date = parseFormat.format(currentTime);

                    data.setPost(postEditText.getText().toString());

                    Log.d("checked date",date);

                    data.setDate(date);

                String blogUUid = UUID.randomUUID().toString();
                    data.setBlogId(blogUUid);
                    data.setUserImage(mAuth.getUid());
                    collectionReferenceBlog.document(blogUUid).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {


                            Toast.makeText(postButton.getContext(),"Post is uploaded!",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddNewPost.this,HomeActivity.class);
                            startActivity(intent);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Log.d("checked","data store failed");

                        }
                    });

                }

            }
        });




    }

}