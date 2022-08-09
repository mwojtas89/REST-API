package com.crud.tasks.domain;

import lombok.Data;

@Data
public class TrelloCardDto {
    private String name;
    private String idList;
    private String pos;
    private String description;
}
