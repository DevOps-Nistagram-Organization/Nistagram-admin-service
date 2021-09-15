package com.nistagram.administrator.client;

import com.nistagram.administrator.model.dto.UserInfoDTO;
import com.nistagram.administrator.model.dto.UsernameWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// app.user.url from application.properties
@FeignClient(name = "user", url = "${app.user.url}")
public interface UserClient {
    @GetMapping(value = "user/getUser/{username}")
    UserInfoDTO getUser(@PathVariable("username") String username);

    @GetMapping(value = "user/getNotApprovedAgents")
    ResponseEntity<List<UserInfoDTO>> getNotApprovedAgents(@RequestHeader("Authorization") String bearerToken);

    @PostMapping(value = "user/approveAgent")
    ResponseEntity<UserInfoDTO> approveAgent(@RequestBody() UsernameWrapper dto, @RequestHeader("Authorization") String bearerToken);

    @PostMapping(value = "user/rejectAgent")
    ResponseEntity<UserInfoDTO> rejectAgent(@RequestBody() UsernameWrapper dto, @RequestHeader("Authorization") String bearerToken);
}
