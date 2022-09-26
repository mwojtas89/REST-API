package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloValidatorTest {

    @Autowired
    TrelloValidator trelloValidator;

    @Test
    void validateCard() {
        //Given
        TrelloCard trelloCard1 = new TrelloCard("name", "desc", "top", "123");
        TrelloCard trelloCard2 = new TrelloCard("test", "desc", "top", "234");

        //When & Then
        trelloValidator.validateCard(trelloCard1);
        trelloValidator.validateCard(trelloCard2);
    }

    @Test
    void validateTrelloBoards() {
        //Given
        List<TrelloBoard> trelloBoards = List.of(
                new TrelloBoard("1", "name", new ArrayList<>()),
                new TrelloBoard("2", "test", new ArrayList<>()),
                new TrelloBoard("3", "name2", new ArrayList<>()),
                new TrelloBoard("4", "test", new ArrayList<>())
        );

        //When
        List<TrelloBoard> filteredList = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        Assertions.assertTrue(trelloBoards.size()>filteredList.size());

    }
}