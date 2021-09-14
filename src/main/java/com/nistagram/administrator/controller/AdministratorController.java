package com.nistagram.administrator.controller;

import com.nistagram.administrator.model.dto.*;
import com.nistagram.administrator.model.entity.ReportedPost;
import com.nistagram.administrator.service.AdministratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "admin")
public class AdministratorController {

    private final AdministratorService administratorService;

    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @GetMapping(value = "notAcceptedAgents")
    public ResponseEntity<List<UserInfoDTO>> getNotAcceptedAgents() {
        List<UserInfoDTO> userInfoDTOList = administratorService.getNotAcceptedAgents();
        return new ResponseEntity<>(userInfoDTOList, HttpStatus.OK);
    }

    @PostMapping(value = "acceptAgent")
    public ResponseEntity<UserInfoDTO> acceptAgent(@RequestBody UsernameWrapper usernameWrapper) {
        UserInfoDTO userInfoDTO = administratorService.acceptAgents(usernameWrapper);
        return new ResponseEntity<>(userInfoDTO, HttpStatus.OK);
    }

    @PostMapping(value = "rejectAgent")
    public ResponseEntity<UserInfoDTO> rejectAgent(@RequestBody UsernameWrapper usernameWrapper) {
        UserInfoDTO userInfoDTO = administratorService.rejectAgent(usernameWrapper);
        return new ResponseEntity<>(userInfoDTO, HttpStatus.OK);
    }
}
