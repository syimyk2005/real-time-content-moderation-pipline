package streamguard.ingestservice.kafka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import streamguard.ingestservice.kafka.dto.CommentEvent;
import streamguard.ingestservice.model.dto.CommentRequest;

@Mapper(componentModel = "spring")
public interface CommentEventMapper {

    @Mapping(target = "status", constant = "PENDING")
    @Mapping(source = "text", target = "message")
    CommentEvent toEvent(CommentRequest dto);
}