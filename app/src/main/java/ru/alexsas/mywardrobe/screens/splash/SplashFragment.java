package ru.alexsas.mywardrobe.screens.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavArgs;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

import ru.alexsas.mywardrobe.R;
import ru.alexsas.mywardrobe.screens.main.MainActivity;


public class SplashFragment extends Fragment {

//    FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mAuth = FirebaseAuth.getInstance();
        launchMainScreen();
    }

    private void launchMainScreen() {
        Intent intent = new Intent(this.requireContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra("PPP", (Parcelable) mAuth);

//        NavDirections darection = SplashFragmentDirections.actionSplashFragmentToMainActivity(mAuth);

        this.startActivity(intent);
    }


}