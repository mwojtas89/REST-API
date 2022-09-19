package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TrelloFacade {

    private static final Logger LOOGER = LoggerFactory.getLogger(TrelloFacade.class);

    private final TrelloService trelloService;
    private final TrelloMapper trelloMapper;

    public List<TrelloBoard> fetchTrelloBoards() {
        return trelloMapper.mapToBoards(trelloService.fetchTrelloBoards());
    }

}
