package com.nistagram.administrator.controller;

import com.nistagram.administrator.model.dto.*;
import com.nistagram.administrator.model.entity.ReportedPost;
import com.nistagram.administrator.service.AdministratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "admin")
public class AdministratorController {

    private final AdministratorService administratorService;

    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @PostMapping(value = "acceptReport", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> acceptReport(@RequestBody ReportAnswerDTO reportAnswerDTO) {
        administratorService.acceptReport(reportAnswerDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "rejectReport", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> rejectReport(@RequestBody ReportAnswerDTO reportAnswerDTO) {
        administratorService.rejectReport(reportAnswerDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "reports")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ReportedPostDTO>> getReportedPosts() {
        List<ReportedPostDTO> reportedPostDTOS = administratorService.getReportedPosts();
        return new ResponseEntity<>(reportedPostDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "reportPost")
    public ResponseEntity<ReportedPost> reportPost(@RequestBody ReportPostDTO reportPostDTO) {
        ReportedPost reportedPost = administratorService.reportPost(reportPostDTO);
        return new ResponseEntity<>(reportedPost, HttpStatus.OK);
    }

    @GetMapping(value = "notAcceptedAgents")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserInfoDTO>> getNotAcceptedAgents() {
        List<UserInfoDTO> userInfoDTOList = administratorService.getNotAcceptedAgents();
        return new ResponseEntity<>(userInfoDTOList, HttpStatus.OK);
    }

    @PostMapping(value = "acceptAgent")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserInfoDTO> acceptAgent(@RequestBody UsernameWrapper usernameWrapper) {
        UserInfoDTO userInfoDTO = administratorService.acceptAgents(usernameWrapper);
        return new ResponseEntity<>(userInfoDTO, HttpStatus.OK);
    }

    @PostMapping(value = "rejectAgent")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserInfoDTO> rejectAgent(@RequestBody UsernameWrapper usernameWrapper) {
        UserInfoDTO userInfoDTO = administratorService.rejectAgent(usernameWrapper);
        return new ResponseEntity<>(userInfoDTO, HttpStatus.OK);
    }
}
