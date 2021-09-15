package com.nistagram.administrator.client;

import com.nistagram.administrator.model.dto.UserInfoDTO;
import com.nistagram.administrator.model.dto.UsernameWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "${api.userService}", name = "UserService")
public interface UserClient {
    @GetMapping(value = "getUser/{username}")
    UserInfoDTO getUser(@PathVariable("username") String username);

    @GetMapping(value = "getNotApprovedAgents")
    ResponseEntity<List<UserInfoDTO>> getNotApprovedAgents(@RequestHeader("Authorization") String bearerToken);

    @PostMapping(value = "approveAgent")
    ResponseEntity<UserInfoDTO> approveAgent(@RequestBody() UsernameWrapper dto, @RequestHeader("Authorization") String bearerToken);

    @PostMapping(value = "rejectAgent")
    ResponseEntity<UserInfoDTO> rejectAgent(@RequestBody() UsernameWrapper dto, @RequestHeader("Authorization") String bearerToken);
}
