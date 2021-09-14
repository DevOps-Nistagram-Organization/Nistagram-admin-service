package com.nistagram.administrator.model.dto;

public class ReportedPostDTO {
    private Long postId;
    private Long totalReports;

    public ReportedPostDTO() {
    }

    public ReportedPostDTO(Long postId, Long totalReports) {
        this.postId = postId;
        this.totalReports = totalReports;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getTotalReports() {
        return totalReports;
    }

    public void setTotalReports(Long totalReports) {
        this.totalReports = totalReports;
    }
}
