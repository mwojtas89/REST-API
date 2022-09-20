package com.crud.tasks.trello.client;

//@ExtendWith(MockitoExtension.class)
//class TrelloClientTest {
//
//    @InjectMocks
//    private TrelloClient trelloClient;
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @Mock
//    private TrelloConfig trelloConfig;
//
//    @Test
//    public void shouldFetchTrelloBoards () throws URISyntaxException {
//        //Given
//        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
//        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
//        when(trelloConfig.getTrelloToken()).thenReturn("test");
//        when(trelloConfig.getUserName()).thenReturn("test");
//
//        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
//        trelloBoards[0] = new TrelloBoardDto("test_ID", "kodilla", new ArrayList<>());
//
//        URI url =trelloClient.buildTrelloUrlGetBoards();
//
//        when(restTemplate.getForObject(url, TrelloBoardDto[].class)).thenReturn(trelloBoards);
//
//        //When
//        List<TrelloBoardDto> fetchTrelloBoards = trelloClient.getTrelloBoards();
//
//        //Then
//        assertEquals(1, fetchTrelloBoards.size());
//        assertEquals("test_ID", fetchTrelloBoards.get(0).getId());
//        assertEquals("kodilla", fetchTrelloBoards.get(0).getName());
//        assertEquals(new ArrayList<>(), fetchTrelloBoards.get(0).getLists());
//
//    }
//
//    @Test
//    public void shouldCreateCard () throws URISyntaxException {
//        //Given
//
//        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
//        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
//        when(trelloConfig.getTrelloToken()).thenReturn("test");
//
//
//        TrelloCardDto trelloCardDto = new TrelloCardDto("Test task", "test_ID", "top", "test");
//
//        URI url = trelloClient.buildTrelloUrlCardPost(trelloCardDto);
//
//        CreatedTrelloCard createdTrelloCard = new CreatedTrelloCard(
//                "1",
//                "test task",
//                "http://test.com"
//        );
//
//        when(restTemplate.postForObject(url, null, CreatedTrelloCard.class)).thenReturn(createdTrelloCard);
//        //When
//
//        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);
//
//        //Then
//
//        assertEquals("1", newCard.getId());
//        assertEquals("test task", newCard.getName());
//        assertEquals("http://test.com", newCard.getShortUrl());
//    }
//
//    @Test
//    public void shouldReturnEmptyList() throws URISyntaxException {
//
//        //Given
//        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
//        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
//        when(trelloConfig.getTrelloToken()).thenReturn("test");
//        when(trelloConfig.getUserName()).thenReturn("test");
//
//        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[0];
//
//        URI url =trelloClient.buildTrelloUrlGetBoards();
//
//        when(restTemplate.getForObject(url, TrelloBoardDto[].class)).thenReturn(trelloBoards);
//
//        //When
//        List<TrelloBoardDto> fetchTrelloBoards = trelloClient.getTrelloBoards();
//
//        //Then
//        assertEquals(0, fetchTrelloBoards.size());
//
//
//    }
//
//}