package com.lrm.service;

import com.lrm.po.User;

/**
 * Created by Daniel on 2022/10/15.
 */
public interface UserService {

    User checkUser(String username, String password);
}
