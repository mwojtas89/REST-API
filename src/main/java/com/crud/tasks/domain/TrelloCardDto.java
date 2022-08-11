package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrelloCardDto {
    private String name;
    private String idList;
    private String pos;
    private String description;
}
