package ru.alexsas.mywardrobe.screens.main.auth.Login;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import com.google.firebase.auth.FirebaseUser;
import ru.alexsas.mywardrobe.services.auntification.FirebaseUserData;



final class LoginViewModel2$$special$$inlined$map$1 implements Function {
    public final Object apply(Object it) {
        FirebaseUser user = (FirebaseUser)it;
        return user != null ? LoginViewModel.AuthenticationState.AUTHENTICATED : LoginViewModel.AuthenticationState.UNAUTHENTICATED;
    }
}


public class LoginViewModel extends ViewModel {
    public enum AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED, INVALID_AUTHENTICATION
    }

     public LiveData authenticationState = Transformations.map((LiveData)(new FirebaseUserData()), (Function)(new LoginViewModel2$$special$$inlined$map$1()));

}