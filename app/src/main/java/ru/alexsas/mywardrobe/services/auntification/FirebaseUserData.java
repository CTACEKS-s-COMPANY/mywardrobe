package ru.alexsas.mywardrobe.services.auntification;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseUserData extends LiveData<FirebaseUser> {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private FirebaseAuth.AuthStateListener authStateListener =
            firebaseAuth -> {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                setValue(firebaseUser);
            };
    @Override
    public void onActive(){
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onInactive(){
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}
