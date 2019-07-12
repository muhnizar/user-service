package id.co.homecredit.service;

/**
 * Created by muhammad.nizar01 on 7/11/2019.
 */
public interface LdapUserService {
    boolean authenticate(String username, String password);
}
