package streamguard.ingestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import streamguard.ingestservice.kafka.dto.CommentEvent;
import streamguard.ingestservice.kafka.mapper.CommentEventMapper;
import streamguard.ingestservice.kafka.producer.CommentProducer;
import streamguard.ingestservice.model.dto.CommentRequest;
import streamguard.ingestservice.model.dto.CommentResponse;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentHandlerService {

    private final CommentEventMapper commentEventMapper;
    private final CommentProducer commentProducer;

    public CommentResponse handleComment(CommentRequest commentDto) {
        CommentEvent event = commentEventMapper.toEvent(commentDto);
        String commentId = String.valueOf(UUID.randomUUID());
        commentProducer.send(commentId,  event);
        return new CommentResponse(commentId,  event.status());
    }


}
