package com.example.linkdn;

import androidx.annotation.NonNull;

public class User {
    public String username;
    public String email;
    public String bio;
    public String skills;
    public String phone;
    public String gender;


    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email, String bio, String skills, String phone, String gender) {
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.skills = skills;
        this.phone = phone;
        this.gender = gender;
    }

    @NonNull
    @Override
    public String toString() {
        return "Username: " + username + "\nGender: " + gender + "\nPhone: " + phone + "\nBio: " + bio + "\nSkills: " + skills;
    }


    public boolean getUserId() {
        return false;
    }


}