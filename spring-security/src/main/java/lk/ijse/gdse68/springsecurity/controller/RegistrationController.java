package lk.ijse.gdse68.springsecurity.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse68.springsecurity.dto.CustomerDTO;
import lk.ijse.gdse68.springsecurity.dto.SignInDTO;
import lk.ijse.gdse68.springsecurity.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class RegistrationController {

    private final CustomerService customerService;


    @Autowired
    public RegistrationController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("/sign_up")
    public ResponseEntity<String> singUp(@RequestBody CustomerDTO customerDTO) {

        CustomerDTO dto = customerService.saveCustomer(customerDTO);
        if (dto != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Sign Up Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sign Up Failed");
        }
    }


//    @PostMapping("/sign_in")
//    public ResponseEntity<String> signIn(@RequestBody SignInDTO signInDTO) {
//        boolean customerExist = customerService.verify(signInDTO);
//
//        if (customerExist) {
//            return ResponseEntity.status(HttpStatus.OK).body("Sign In Successfully");
//        } else {
//            System.out.println("Bro i was here");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sign In Failed");
//        }
//
//    }


    @PostMapping("/sign_in")
    public ResponseEntity<String> signIn(@RequestBody SignInDTO signInDTO, HttpServletResponse response) {
        String token = customerService.verify(signInDTO);

        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // if using HTTPS
        cookie.setPath("/"); // Global path
        cookie.setMaxAge(10000); // 1 hour expiry, adjust as needed

        // Add cookie to response
        response.addCookie(cookie);


        if (token!=null) {
            return ResponseEntity.status(HttpStatus.OK).body("Sing In Success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sign In Failed");
        }

    }


}



