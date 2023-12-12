package com.tekion.cricket.dto.team;

import lombok.Data;

import javax.persistence.Column;

@Data
public class TeamDto {
    private Long Id;
    private String teamName;
    private String Country;

}
