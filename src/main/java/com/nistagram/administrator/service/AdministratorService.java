package com.nistagram.administrator.service;

import com.nistagram.administrator.client.PostClient;
import com.nistagram.administrator.client.UserClient;
import com.nistagram.administrator.model.dto.*;
import com.nistagram.administrator.model.entity.ReportedPost;
import com.nistagram.administrator.repository.ReportedPostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdministratorService {

    private final PostClient postClient;
    private final ReportedPostRepository reportedPostRepository;
    private final UserService userService;
    private final UserClient userClient;

    public AdministratorService(PostClient postClient, ReportedPostRepository reportedPostRepository, UserService userService, UserClient userClient) {
        this.postClient = postClient;
        this.reportedPostRepository = reportedPostRepository;
        this.userService = userService;
        this.userClient = userClient;
    }

    public List<UserInfoDTO> getNotAcceptedAgents() {
        String token = userService.getTokenString();
        ResponseEntity<List<UserInfoDTO>> userInfoDTOS = userClient.getNotApprovedAgents(token);
        if(userInfoDTOS.hasBody()) {
            return userInfoDTOS.getBody();
        }
        return null;
    }

    public UserInfoDTO acceptAgents(UsernameWrapper username) {
        String token = userService.getTokenString();
        ResponseEntity<UserInfoDTO> userInfoDTOResponseEntity = userClient.approveAgent(username, token);
        return userInfoDTOResponseEntity.getBody();
    }

    public UserInfoDTO rejectAgent(UsernameWrapper username) {
        String token = userService.getTokenString();
        ResponseEntity<UserInfoDTO> userInfoDTOResponseEntity = userClient.rejectAgent(username, token);
        return userInfoDTOResponseEntity.getBody();
    }
}
