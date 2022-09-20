package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrelloMapperTest {

    TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    void mapToBoards() {
        //Given
        List<TrelloListDto> trelloListDtos1 = List.of(
                new TrelloListDto("1", "test1", true),
                new TrelloListDto("2", "test2", false)
        );
        List<TrelloListDto> trelloListDtos2 = List.of(
                new TrelloListDto("3", "test3", true),
                new TrelloListDto("4", "test4", false)
        );
        List<TrelloBoardDto> trelloBoardDtos = List.of(
                new TrelloBoardDto("1", "Trello board 1", trelloListDtos1),
                new TrelloBoardDto("2", "Trello board 2", trelloListDtos2)
        );

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        //Then
        assertEquals(2, trelloBoards.size());
        assertEquals("Trello board 2", trelloBoards.get(1).getName());
        assertFalse(trelloBoards.get(1).getLists().get(1).isClosed());
    }

    @Test
    void mapToBoardsDto() {
        //Given
        List<TrelloList> trelloList1 = List.of(
                new TrelloList("1", "test1", true),
                new TrelloList("2", "test2", false)
        );
        List<TrelloList> trelloList2 = List.of(
                new TrelloList("3", "test3", true),
                new TrelloList("4", "test4", false)
        );
        List<TrelloBoard> trelloBoard = List.of(
                new TrelloBoard("1", "Trello board 1", trelloList1),
                new TrelloBoard("2", "Trello board 2", trelloList2)
        );

        //When
        List<TrelloBoardDto> mappedBoards = trelloMapper.mapToBoardsDto(trelloBoard);

        //Then
        assertEquals(2, mappedBoards.size());
        assertEquals("Trello board 1", mappedBoards.get(0).getName());
        assertTrue(mappedBoards.get(0).getLists().get(0).isClosed());
    }

    @Test
    void mapToList() {
        //Given
        List<TrelloListDto> trelloListDtos = List.of(
                new TrelloListDto("1", "test1", true),
                new TrelloListDto("2", "test2", false)
        );

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);

        //Then
        assertEquals(2, trelloLists.size());
        assertFalse(trelloLists.get(1).isClosed());
    }

    @Test
    void mapToListDto() {
        //Given
        List<TrelloList> trelloList = List.of(
                new TrelloList("1", "test1", true),
                new TrelloList("2", "test2", false)
        );

        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloList);

        //Then
        assertEquals(2, trelloListsDto.size());
        assertFalse(trelloListsDto.get(1).isClosed());
    }

    @Test
    void mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Name", "Description", "top", "123");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("Name", trelloCard.getName());
        assertEquals("123", trelloCard.getListId());
    }

    @Test
    void mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Name", "Description", "top", "123");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("Name", trelloCardDto.getName());
        assertEquals("123", trelloCardDto.getListId());
    }
}