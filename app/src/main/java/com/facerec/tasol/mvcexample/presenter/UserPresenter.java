package com.facerec.tasol.mvcexample.presenter;

import android.content.Context;

import com.facerec.tasol.mvcexample.db.UserOpenHelper;
import com.facerec.tasol.mvcexample.model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tasol on 14/8/18.
 */

public class UserPresenter {
    Context context;
    UserOpenHelper userOpenHelper;

    public UserPresenter(Context context) {
        this.context = context;
        userOpenHelper =new UserOpenHelper(context);
    }

    public void addUser(UserModel userModel){
        userOpenHelper.addUser(userModel);
    }

    public List<UserModel> getUserModel(){
        List<UserModel> userModelList =new ArrayList<>();
        return userOpenHelper.getAllUsers();
    }
}
