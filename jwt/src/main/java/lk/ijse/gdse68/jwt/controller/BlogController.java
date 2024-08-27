package lk.ijse.gdse68.jwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/blog")
public class BlogController {
    @GetMapping("/newMethod")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String newMethod(){
        return "HI";
    }
}

