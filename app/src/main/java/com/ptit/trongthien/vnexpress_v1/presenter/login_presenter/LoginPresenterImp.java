package com.ptit.trongthien.vnexpress_v1.presenter.login_presenter;

import android.content.Context;

import com.ptit.trongthien.vnexpress_v1.model.entity.Account;
import com.ptit.trongthien.vnexpress_v1.model.login_model.LoginModel;
import com.ptit.trongthien.vnexpress_v1.view.login_view.LoginView;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by TrongThien on 7/21/2017.
 */

public class LoginPresenterImp implements LoginPresenter {

    private LoginView loginView;
    private Context context;

    public LoginPresenterImp(LoginView loginView, Context context) {
        this.loginView = loginView;
        this.context = context;
    }

    @Override
    public void checkLogin(String username, String password) {
        //goi toi model de lay du lieu
        LoginModel loginModel = new LoginModel();
        Set<Account> accounts = loginModel.getAccountData(context);
        Iterator<Account> accountIterator = accounts.iterator();
        while (accountIterator.hasNext()) {
            // kiem tra en va pass
            Account account = accountIterator.next();
            if (username.equals(account.getUsername()) && password.equals(account.getPassword())){
                // dang nhap thanh cong
                loginView.loginSuccess();
                return;
            }
        }
        // tra ra view dang nhap that bai
        loginView.loginFail();
    }
}
