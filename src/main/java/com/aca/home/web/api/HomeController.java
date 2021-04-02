package com.aca.home.web.api;

import java.security.Principal;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
    private static Log log = LogFactory.getLog(HomeController.class);

    @GetMapping(path = "/login")
    public Object login() {
        return new ModelAndView("login.html");
    }

    @GetMapping(path = { "/", "/index" })
    public Object indexCommon(HttpServletRequest request, Principal principal) {
        String tip = String.format("welcome : %s", getUser());
        log.info(tip);
        request.setAttribute("loginName", getUser());
        return new ModelAndView("index.html");
    }

    public String getUser() {
        // 获取用户信息
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userName;
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    //@PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/admin")
    @RolesAllowed("ROLE_ADMIN")
    public Object testAdmin(Principal principal) {
        return ResponseEntity.ok().body("hello admin: " + principal.getName());
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/user")
    public Object testUser(Principal principal) {
        return ResponseEntity.ok().body("hello user: " + principal.getName());
    }

}