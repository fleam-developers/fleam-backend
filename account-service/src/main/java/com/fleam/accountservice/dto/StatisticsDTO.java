package com.fleam.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
public class StatisticsDTO {
    public Long totalWatchCount;
    public Long totalCommentCount;
    public Long totalRatingCount;

}
