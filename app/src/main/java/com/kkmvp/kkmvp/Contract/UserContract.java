package com.kkmvp.kkmvp.Contract;

import com.kkmvp.kkmvp.BasePresenter;
import com.kkmvp.kkmvp.BaseView;
import com.kkmvp.kkmvp.Model.UserResponse;

import java.util.List;

/**
 * Created by KK00120638 on 9/8/2017.
 */

public interface UserContract {

    interface View extends BaseView<Presenter>{
        // set data for adaptor
        void addUserData(List<UserResponse> resultUser);

    }

    interface Presenter extends BasePresenter{

    }
}
