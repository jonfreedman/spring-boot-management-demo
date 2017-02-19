package com.github.jonfreedman;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class GroupController {

    @RequestMapping("/userin/{role}")
    public String checkRole(@PathVariable final String role, final HttpServletRequest request) {
        final boolean member = request.isUserInRole(role);
        return String.format("User %s in role %s", member ? "is" : "isn't", role);
    }

}