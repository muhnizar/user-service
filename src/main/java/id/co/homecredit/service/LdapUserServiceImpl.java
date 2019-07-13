package id.co.homecredit.service;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.DefaultDirObjectFactory;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 * Created by muhammad.nizar01 on 7/11/2019.a
 */
@Service
public class LdapUserServiceImpl implements LdapUserService {


    @Override
    public boolean authenticate(String username, String password) {
        try {

            String usernameCoid = String.format("%s%s", username,"@homecredit.co.id");
            String usernameNet = String.format("%s%s", username,"@hcg.homecredit.net");

            LdapTemplate templateCoid = setLdapTemplate(usernameCoid, password) ;
            LdapTemplate templateNet = setLdapTemplate(usernameNet, password) ;

            if ( (templateCoid.getContextSource().getContext(usernameCoid, password) != null) ||
                    templateNet.getContextSource().getContext(usernameNet, password) != null){
                return  true;
            }

            return false;
        } catch (AuthenticationException e) {
            return false;
        } catch (Exception e){
            return false;
        }
    }

    private LdapTemplate setLdapTemplate (String username, String password){

        LdapContextSource sourceLdapCtx = new LdapContextSource();
        sourceLdapCtx.setUrl("ldap://id-vw01.hcg.homecredit.net:3268/");
        sourceLdapCtx.setUserDn(username);
        sourceLdapCtx.setPassword(password);
        sourceLdapCtx.setDirObjectFactory(DefaultDirObjectFactory.class);
        sourceLdapCtx.afterPropertiesSet();
        LdapTemplate sourceLdapTemplate = new LdapTemplate(sourceLdapCtx);

        return sourceLdapTemplate;
    }

}
