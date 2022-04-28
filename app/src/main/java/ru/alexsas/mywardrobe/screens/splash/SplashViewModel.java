package ru.alexsas.mywardrobe.screens.splash;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;

import ru.alexsas.mywardrobe.services.auntification.FirebaseUserData;


final class SplashViewModelSpecialMap implements Function {
    public final Object apply(Object it) {
        FirebaseUser user = (FirebaseUser) it;
        return user != null ? SplashViewModel.AuthenticationState.AUTHENTICATED : SplashViewModel.AuthenticationState.UNAUTHENTICATED;
    }
}

public class SplashViewModel extends ViewModel {
    public enum AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED, INVALID_AUTHENTICATION
    }

    LiveData<FirebaseUser> mAuth = new FirebaseUserData();

    public LiveData authenticationState = Transformations.map((mAuth), (Function) (new SplashViewModelSpecialMap()));

}