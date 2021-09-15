package com.nistagram.administrator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// app.post.url from application.properties
@FeignClient(name = "post", url = "${app.post.url}")
public interface PostClient {
    @DeleteMapping(value = "post/delete/{id}")
    ResponseEntity<Boolean> deletePost(@PathVariable("id") Long id, @RequestHeader("Authorization") String bearerToken);
}
