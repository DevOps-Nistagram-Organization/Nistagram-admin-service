package com.nistagram.administrator.service;

import com.nistagram.administrator.client.UserClient;
import com.nistagram.administrator.config.JwtTokenUtil;
import com.nistagram.administrator.model.dto.UserInfoDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserClient userClient;

    public UserService(JwtTokenUtil jwtTokenUtil, UserClient userClient) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userClient = userClient;
    }

    public String getUsername() {
        String token = SecurityContextHolder.getContext().getAuthentication().getDetails().toString();
        String jwtToken = token.substring(7);
        return jwtTokenUtil.getUsernameFromToken(jwtToken);
    }
    public UserInfoDTO getUserInfo(String username) {
        UserInfoDTO userInfo = userClient.getUser(username);
        return userInfo;
    }

    public String getTokenString() {
        return SecurityContextHolder.getContext().getAuthentication().getDetails().toString();
    }
}
