package com.nistagram.administrator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "${api.postService}", name = "PostService")
public interface PostClient {
    @DeleteMapping(value = "delete/{id}")
    ResponseEntity<Boolean> deletePost(@PathVariable("id") Long id, @RequestHeader("Authorization") String bearerToken);
}
