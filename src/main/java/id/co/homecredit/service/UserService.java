package id.co.homecredit.service;

import id.co.homecredit.entity.User;

/**
 * Created by muhammad.nizar01 on 7/13/2019.
 */
public interface UserService {
    User findUserByUsername(String username);
    void save(User user);
}
