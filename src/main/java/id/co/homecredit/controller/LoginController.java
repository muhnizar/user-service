package id.co.homecredit.controller;

import id.co.homecredit.dto.request.LoginRequest;
import id.co.homecredit.entity.User;
import id.co.homecredit.service.LdapUserServiceImpl;
import id.co.homecredit.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by muhammad.nizar01 on 7/12/2019.
 */
@RestController
public class LoginController {

    @Autowired
    LdapUserServiceImpl ldapUserService;

    @Autowired
    UserServiceImpl userService;



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
//    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request, HttpServletRequest httpServletRequest) throws Exception {

        String username = request.getUsername();
        String passward = request.getPassword();

        if (ldapUserService.authenticate(username, passward)){
             User user = userService.findUserByUsername(username);

            if (user != null){
                System.out.println("FOUND in DB");
            }else {
                user = new User();
                user.setUsername(username);
                user.setPassword("$2a$08$qvrzQZ7jJ7oy2p/msL4M0.l83Cd0jNsX6AJUitbgRXGzge4j035ha");
                user.setEnabled(true    );
                 // admin1234 - '$2a$08$qvrzQZ7jJ7oy2p/msL4M0.l83Cd0jNsX6AJUitbgRXGzge4j035ha'
                userService.save(user);
            }


        }else {
            System.out.println("LDAP Authentication is failed");
        }

        return new ResponseEntity(request, HttpStatus.OK);
    }
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
//    @CrossOrigin(origins = "http://localhost:3000")
    public void test() {
        System.out.println("HELLO USERRR");

    }
}
