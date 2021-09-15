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

    public void acceptReport(ReportAnswerDTO reportAnswerDTO) {
        String token = userService.getTokenString();
        postClient.deletePost(reportAnswerDTO.getPostId(), token);
        reportedPostRepository.deleteAllByPostId(reportAnswerDTO.getPostId());
    }

    public void rejectReport(ReportAnswerDTO reportAnswerDTO) {
        reportedPostRepository.deleteAllByPostId(reportAnswerDTO.getPostId());
    }

    public ArrayList<ReportedPostDTO> getReportedPosts() {
        List<ReportedPost> reportedPostList = reportedPostRepository.findAll();
        Map<Long, Long> postReportMap = new HashMap<>();
        for (ReportedPost reportedPost : reportedPostList) {
            Long key = reportedPost.getPostId();
            if (postReportMap.containsKey(key)) {
                Long currentValue = postReportMap.get(key);
                postReportMap.put(key, currentValue + 1);
            } else {
                postReportMap.put(key, 1L);
            }
        }
        ArrayList<ReportedPostDTO> reportPostDTOS = new ArrayList<>();
        postReportMap.forEach((key, value) -> reportPostDTOS.add(new ReportedPostDTO(key, value)));
        return reportPostDTOS;
    }

    public ReportedPost reportPost(ReportPostDTO reportPostDTO) {
        String username = userService.getUsername();
        ReportedPost reportedPost = new ReportedPost(null,reportPostDTO.getPostId(),username,new Date());
        return reportedPostRepository.save(reportedPost);
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
