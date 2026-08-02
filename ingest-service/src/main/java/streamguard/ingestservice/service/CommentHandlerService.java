package streamguard.ingestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import streamguard.ingestservice.kafka.dto.CommentEvent;
import streamguard.ingestservice.kafka.mapper.CommentEventMapper;
import streamguard.ingestservice.kafka.producer.CommentProducer;
import streamguard.ingestservice.model.CommentDto;

@Service
@RequiredArgsConstructor
public class CommentHandlerService {

    private final CommentEventMapper commentEventMapper;
    private final CommentProducer commentProducer;

    public String handleComment(CommentDto commentDto) {
        CommentEvent event = commentEventMapper.toEvent(commentDto);
        commentProducer.send(commentDto.userId(), event);
        return "Comment received successfully";
    }


}
