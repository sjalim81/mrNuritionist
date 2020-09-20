package com.example.mrnuritionist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.api.Distribution;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Objects;
import java.util.UUID;

import dmax.dialog.SpotsDialog;

public
class SetupActivity extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    CollectionReference collectionReferenceUsers = firebaseFirestore.collection("Users");

    ImageView userProfileImageView;
    EditText userNameEditText, fullNameEditText, countryEditText;
    Button saveButton;
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    private Uri  imageUri = null;
    String imageUUid ;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    SpotsDialog progressBar;


    private int IMAGE_PICKER_SELECT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        userProfileImageView = findViewById(R.id.imageView_userProfile);
        progressBar = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .build();

        userNameEditText = findViewById(R.id.setup_username);
        fullNameEditText = findViewById(R.id.setup_full_name);
        countryEditText = findViewById(R.id.setup_country_name);
        saveButton = findViewById(R.id.setup_information_button);
        userProfileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(saveButton.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(saveButton.getContext(), "Permission Denied", Toast.LENGTH_LONG).show();
                        ActivityCompat.requestPermissions(SetupActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                    } else {
                        choseImage();

                    }
                } else {
                    choseImage();


                }

                Log.d("checked","url at imageview"+imageUri);

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(userNameEditText.getText().toString()))
                {
                    userNameEditText.setError("Field is empty!");
                }
                else if(TextUtils.isEmpty(fullNameEditText.getText().toString()))
                {
                    fullNameEditText.setError("Field is empty!");
                }
                else if(TextUtils.isEmpty(countryEditText.getText().toString()))
                {
                    countryEditText.setError("Field is empty!");
                }
                else
                {
                    imageUUid =  mAuth.getUid();

                    StorageReference riversRef = storageReference.child("user/" + imageUUid + ".jpg");
                    UploadTask uploadTask = riversRef.putFile(imageUri);

                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            String imageUriString = Objects.requireNonNull(Objects.requireNonNull(taskSnapshot.getMetadata()).getReference()).getDownloadUrl().toString();
                            Log.d("checked 1", imageUriString);

                            Log.d("checked", "image1 upload task ok");

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("checked", "image upload failed 1");


                        }
                    });


                    UserModel user = new UserModel();
                    user.setCountry(countryEditText.getText().toString());
                    user.setFullName(fullNameEditText.getText().toString());
                    user.setImageId(imageUUid);
                    user.setName(userNameEditText.getText().toString());
                    collectionReferenceUsers.document(mAuth.getUid()).set(user).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("checked","user data save failed");
                        }
                    }).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            Toast.makeText(SetupActivity.this,"Data saved successfully",Toast.LENGTH_SHORT).show();
                        }
                    });

                    Intent intent = new Intent(SetupActivity.this,MainActivity.class);
                    startActivity(intent);


                }

            }
        });


    }

    private void choseImage() {

//        Intent i = new Intent(
//                Intent.ACTION_PICK,
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(i, IMAGE_PICKER_SELECT);
//
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                imageUri = result.getUri();
                Log.d("checkedUri", imageUri.toString());
                userProfileImageView.setImageURI(imageUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == IMAGE_PICKER_SELECT
//                && resultCode == Activity.RESULT_OK) {
//            String path = getPathFromCameraData(data, this);
//            Log.i("PICTURE", "Path: " + path);
//            if (path != null) {
//                imageUri = data.getData();
//                Log.d("checkedUri", Objects.requireNonNull(data.getData()).toString());
//                userProfileImageView.setImageURI(imageUri);
//
//            } else {
//                Log.d("checker", "onActivity result error");
//            }
//        }
//    }


    private static String getPathFromCameraData(Intent data, Context context) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        assert selectedImage != null;
        Cursor cursor = context.getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        assert cursor != null;
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }


}


