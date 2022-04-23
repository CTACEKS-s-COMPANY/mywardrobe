package ru.alexsas.mywardrobe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import ru.alexsas.mywardrobe.databinding.ActivityMainBinding;
import ru.alexsas.mywardrobe.fragments.Login.LoginViewModel;
import ru.alexsas.mywardrobe.fragments.MainFragment;
import ru.alexsas.mywardrobe.fragments.RegisterFragment;


public class MainActivity extends AppCompatActivity implements NavigationHost {

    LoginViewModel viewModel = new LoginViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewModel.authenticationState.observe(this,
                authenticationState -> {
                    if (authenticationState == LoginViewModel.AuthenticationState.AUTHENTICATED) {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .add(R.id.container, new MainFragment())
                                .commit();
                    } else {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .add(R.id.container, new RegisterFragment())
                                .commit();
                    }
                });
    }


    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }
}