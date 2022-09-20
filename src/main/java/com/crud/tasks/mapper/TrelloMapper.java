package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class TrelloMapper {

    public List<TrelloBoard> mapToBoards (final List<TrelloBoardDto> trelloBoardDto) {
        return trelloBoardDto.stream()
                .map(t -> new TrelloBoard(t.getId(), t.getName(), mapToList(t.getLists())))
                .collect(Collectors.toList());
    }

    public List<TrelloBoardDto> mapToBoardsDto (final List<TrelloBoard> trelloBoards) {
        return trelloBoards.stream()
                .map(t -> new TrelloBoardDto(t.getId(), t.getName(), mapToListDto(t.getLists())))
                .collect(Collectors.toList());
    }

    public List<TrelloList> mapToList (List<TrelloListDto> trelloListDto) {
        return trelloListDto.stream()
                .map(t -> new TrelloList(t.getId(), t.getName(), t.isClosed()))
                .collect(Collectors.toList());
    }

    public List<TrelloListDto> mapToListDto (List<TrelloList> trelloLists) {
        return trelloLists.stream()
                .map(t -> new TrelloListDto(t.getId(), t.getName(), t.isClosed()))
                .collect(Collectors.toList());
    }

    public TrelloCard mapToCard (final TrelloCardDto trelloCardDto) {
        return new TrelloCard(trelloCardDto.getName(), trelloCardDto.getDescription(), trelloCardDto.getPos(), trelloCardDto.getListId());
    }

    public TrelloCardDto mapToCardDto (final TrelloCard trelloCard) {
        return new TrelloCardDto(trelloCard.getName(), trelloCard.getDescription(), trelloCard.getPos(), trelloCard.getListId());
    }
}
