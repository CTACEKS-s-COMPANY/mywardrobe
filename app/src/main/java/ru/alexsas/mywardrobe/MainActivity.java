package ru.alexsas.mywardrobe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import java.util.Objects;

import ru.alexsas.mywardrobe.fragments.auth.Login.LoginFragment;
import ru.alexsas.mywardrobe.fragments.auth.Login.LoginViewModel;
import ru.alexsas.mywardrobe.fragments.MainFragment;
import ru.alexsas.mywardrobe.fragments.auth.RegisterFragment;


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