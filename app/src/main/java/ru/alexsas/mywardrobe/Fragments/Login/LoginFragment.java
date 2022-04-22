package ru.alexsas.mywardrobe.Fragments.Login;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ru.alexsas.mywardrobe.Fragments.RegisterFragment;
import ru.alexsas.mywardrobe.NavigationHost;
import ru.alexsas.mywardrobe.R;
import ru.alexsas.mywardrobe.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FragmentLoginBinding mBinding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentLoginBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Buttons

        mBinding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mBinding.emailEditText.getText().toString();
                String password = mBinding.passwordEditText.getText().toString();
                signIn(email, password);
            }
        });

        mBinding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) getActivity()).navigateTo(new RegisterFragment(), false);
            }
        });

//        mBinding.passwordEditText.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if (isPasswordValid(mBinding.passwordEditText.getText())) {
//                    mBinding.passwordTextInput.setError(null); //Clear the error
//                }
//                return false;
//            }
//        });

        mAuth = FirebaseAuth.getInstance();

    }

//    private boolean isPasswordValid(@Nullable Editable text) {
//        return text != null && text.length() >= 8;
//    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            reload();
//        }
//    }

//    private void reload() {
//        mAuth.getCurrentUser().reload().addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
//                    Toast.makeText(getContext(),
//                            "Reload successful!",
//                            Toast.LENGTH_SHORT).show();
//                } else {
//                    Log.e(TAG, "reload", task.getException());
//                    Toast.makeText(getContext(),
//                            "Failed to reload user.",
//                            Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }


    private boolean validateForm() {
        boolean valid = true;

        String email = mBinding.emailEditText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mBinding.emailTextInput.setError(getString(R.string.email_error_msg));
            valid = false;
        } else {
            mBinding.emailTextInput.setError(null);
        }

        String password = mBinding.passwordEditText.getText().toString();
        if (TextUtils.isEmpty(password) || password.length() <= 8) {
            mBinding.passwordTextInput.setError(getString(R.string.password_error_msg));
            valid = false;
        } else {
            mBinding.passwordTextInput.setError(null);
        }

        return valid;
    }

    private void signIn(String email, String password) {

        Log.d(TAG, "signIn:" + email);

        if (!validateForm()) {
            return;
        }

        Log.d(TAG, "Validate OK! " + email + " " + password);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getContext(), "Authentication Successful",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

}


// Clear the error once more than 8 characters are typed.
//        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if (isPasswordValid(passwordEditText.getText())) {
//                    passwordTextInput.setError(null); //Clear the error
//                }
//                return false;
//            }
//        });
//        return view;

