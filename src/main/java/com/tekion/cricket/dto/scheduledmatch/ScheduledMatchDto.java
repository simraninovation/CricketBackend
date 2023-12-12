package com.tekion.cricket.dto.scheduledmatch;

import lombok.Data;

import java.util.Date;

@Data
public class ScheduledMatchDto {

    private String venue;

    private Date date;

    private String matchStatus;

    private Long teamAId;

    private Long teamBId;
}
