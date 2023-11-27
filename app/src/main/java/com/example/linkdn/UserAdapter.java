package com.example.linkdn;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {

    private List<User> userList;
    private Context context;

    public UserAdapter(Context context, List<User> userList) {
        super(context, R.layout.user_item, userList);
        this.context = context;
        this.userList = userList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        User user = userList.get(position);

        // Bind user data to the ViewHolder
        holder.usernameTextView.setText(user.username);
        holder.genderTextView.setText(user.gender);
        holder.phoneTextView.setText(user.phone);
        holder.bioTextView.setText(user.bio);
        holder.skillsTextView.setText(user.skills);

        // Set OnClickListener for each item
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle item click, e.g., open a new activity
                Intent intent = new Intent(context, contact.class);
                // Pass any data you need to the new activity
                // Pass user data to the ContactActivity
                intent.putExtra("username", user.username);
                intent.putExtra("email", user.email);
                intent.putExtra("bio", user.bio);
                intent.putExtra("skills", user.skills);
                intent.putExtra("phone", user.phone);
                intent.putExtra("gender", user.gender);
                // Example: If you have a userId in your User class
                context.startActivity(intent);
            }
        });



        return convertView;
    }


    private static class ViewHolder {
        TextView usernameTextView, genderTextView, phoneTextView, bioTextView, skillsTextView;
        ImageView profileImageView;

        ViewHolder(View itemView) {
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            genderTextView = itemView.findViewById(R.id.genderTextView);
            phoneTextView = itemView.findViewById(R.id.phoneTextView);
            bioTextView = itemView.findViewById(R.id.bioTextView);
            skillsTextView = itemView.findViewById(R.id.skillsTextView);
            profileImageView = itemView.findViewById(R.id.profileImageView);
        }
    }
}