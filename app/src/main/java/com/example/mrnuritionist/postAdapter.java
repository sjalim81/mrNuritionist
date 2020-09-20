package com.example.mrnuritionist;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.security.AccessControlContext;
import java.util.List;

public class postAdapter extends RecyclerView.Adapter<postAdapter.ViewHolder> {


    List<BlogModel> mData;
    Context mContext;
FirebaseAuth mAuth = FirebaseAuth.getInstance();



    public postAdapter(List<BlogModel> mData) {
        this.mData = mData;

    }



    @NonNull
    @Override
    public postAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_post_item,parent,false);

mContext = parent.getContext();
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull final postAdapter.ViewHolder holder, int position) {

        BlogModel data = mData.get(position);

        holder.postTextView.setText(data.getPost());
        holder.dateTextView.setText(data.getDate());
        holder.userNameTextView.setText(data.getName());
        Log.d("checked uid",mAuth.getUid());
        StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://mr-nuitritionist-chat.appspot.com/").child("user/"+data.getUserImage()+".jpg");

        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
                // Pass it to Picasso to download, show in ImageView and caching
                Picasso.get().load(uri.toString()).into(holder.userImageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.d("checked","loader failed");
            }
        });

    }




    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView userImageView;
        TextView userNameTextView,dateTextView,postTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userImageView = itemView.findViewById(R.id.post_profile_image);
            userNameTextView = itemView.findViewById(R.id.post_user_name);
            dateTextView = itemView.findViewById(R.id.post_date);
            postTextView = itemView.findViewById(R.id.post_description);

        }
    }
}
