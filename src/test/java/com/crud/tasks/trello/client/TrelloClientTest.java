package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrelloClientTest {

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloConfig;

    @Test
    public void shouldFetchTrelloBoards () throws URISyntaxException {
        //Given
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
        when(trelloConfig.getUserName()).thenReturn("test");

        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_ID", "kodilla", new ArrayList<>());

        URI url =trelloClient.buildTrelloUrlGetBoards();

        when(restTemplate.getForObject(url, TrelloBoardDto[].class)).thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> fetchTrelloBoards = trelloClient.getTrelloBoards();

        //Then
        assertEquals(1, fetchTrelloBoards.size());
        assertEquals("test_ID", fetchTrelloBoards.get(0).getId());
        assertEquals("kodilla", fetchTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(), fetchTrelloBoards.get(0).getLists());

    }

    @Test
    public void shouldCreateCard () throws URISyntaxException {
        //Given

        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");


        TrelloCardDto trelloCardDto = new TrelloCardDto("Test task", "test_ID", "top", "test");

        URI url = trelloClient.buildTrelloUrlCardPost(trelloCardDto);

        CreatedTrelloCardDto createdTrelloCard = new CreatedTrelloCardDto("1", "test task", "http://test.com");

        when(restTemplate.postForObject(url, null, CreatedTrelloCardDto.class)).thenReturn(createdTrelloCard);
        //When

        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);

        //Then

        assertEquals("1", newCard.getId());
        assertEquals("test task", newCard.getName());
        assertEquals("http://test.com", newCard.getShortUrl());
    }

    @Test
    public void shouldReturnEmptyList() throws URISyntaxException {

        //Given
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
        when(trelloConfig.getUserName()).thenReturn("test");

        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[0];

        URI url =trelloClient.buildTrelloUrlGetBoards();

        when(restTemplate.getForObject(url, TrelloBoardDto[].class)).thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> fetchTrelloBoards = trelloClient.getTrelloBoards();

        //Then
        assertEquals(0, fetchTrelloBoards.size());


    }

}
