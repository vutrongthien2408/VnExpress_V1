package com.ptit.trongthien.vnexpress_v1.presenter.register_presenter;

import android.content.Context;

import com.ptit.trongthien.vnexpress_v1.model.entity.Account;
import com.ptit.trongthien.vnexpress_v1.model.register_model.RegisterModel;
import com.ptit.trongthien.vnexpress_v1.view.register_view.RegisterView;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by TrongThien on 7/21/2017.
 */

public class RegisterPresenterImp implements RegisterPresenter {
    private RegisterView registerView;
    private Context context;

    public RegisterPresenterImp(RegisterView registerView, Context context) {
        this.registerView = registerView;
        this.context = context;
    }

    @Override
    public void checkRegister(String username, String password) {
        // lay du lieu tu model
        RegisterModel registerModel = new RegisterModel();
        Set<Account> accounts = registerModel.getAccount(context);
        Iterator<Account> accountIterator = accounts.iterator();
        while (accountIterator.hasNext()) {
            // kiem tra ten dang ky
            Account account = accountIterator.next();

            if (account.getUsername().equals(username)) {
                //dang ky that bai do ten trung
                registerView.registerFail(username);
                return;
            }
        }
        // dang ky account
        registerModel.insertAccount(new Account(username,password),context);
        registerView.registerSuccess(username);
    }
}
