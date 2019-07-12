package id.co.homecredit.controller;

import id.co.homecredit.dto.request.LoginRequest;
import id.co.homecredit.service.LdapUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
//    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request, HttpServletRequest httpServletRequest) throws Exception {

        ldapUserService.authenticate(request.getUsername(), request.getPassword());

        return new ResponseEntity(request, HttpStatus.OK);
    }
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
//    @CrossOrigin(origins = "http://localhost:3000")
    public void test() {
        System.out.println("HELLO USERRR");

    }
}
