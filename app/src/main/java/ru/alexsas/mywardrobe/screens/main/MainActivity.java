package ru.alexsas.mywardrobe.screens.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import java.util.Objects;

import ru.alexsas.mywardrobe.R;
import ru.alexsas.mywardrobe.screens.main.auth.Login.LoginViewModel;


public class MainActivity extends AppCompatActivity {

    LoginViewModel viewModel = new LoginViewModel();

    private NavController navcontroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        navcontroller = Objects.requireNonNull(navHostFragment).getNavController();

        viewModel.authenticationState.observe(this,
                authenticationState -> {
                    if (authenticationState == LoginViewModel.AuthenticationState.AUTHENTICATED) {
                        navcontroller.navigate(R.id.mainFragment);
                    } else {
                        navcontroller.navigate(R.id.loginFragment);
                    }
                });
    }
}