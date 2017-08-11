package com.ptit.trongthien.vnexpress_v1.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ptit.trongthien.vnexpress_v1.R;
import com.ptit.trongthien.vnexpress_v1.view.login_view.LoginFragment;
import com.ptit.trongthien.vnexpress_v1.view.register_view.RegisterFragment;

/**
 * Created by TrongThien on 7/21/2017.
 */

public class MainActivity extends AppCompatActivity {
    private LoginFragment loginFragment = new LoginFragment();
    private RegisterFragment registerFragment = new RegisterFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragmentStack();
    }

    private void initFragmentStack() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.lnPanel, loginFragment);
        transaction.add(R.id.lnPanel, registerFragment);
        transaction.show(loginFragment);
        transaction.hide(registerFragment);
        transaction.commit();
    }

    public void showHomePress(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    public void hideHomePress(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
    }

    public void changeFragmentStack(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.hide(loginFragment);
        transaction.hide(registerFragment);
        transaction.show(fragment);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        changeFragmentStack(getLoginFragment());
        return super.onOptionsItemSelected(item);
    }

    public LoginFragment getLoginFragment() {
        hideHomePress();
        return loginFragment;
    }

    public RegisterFragment getRegisterFragment() {
        showHomePress();
        return registerFragment;
    }
}
