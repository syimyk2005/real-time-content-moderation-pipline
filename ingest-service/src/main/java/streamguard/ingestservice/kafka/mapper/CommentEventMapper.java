package streamguard.ingestservice.kafka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import streamguard.ingestservice.kafka.dto.CommentEvent;
import streamguard.ingestservice.model.CommentDto;

@Mapper(componentModel = "spring")
public interface CommentEventMapper {

    @Mapping(source = "text", target = "message")
    CommentEvent toEvent(CommentDto dto);
}