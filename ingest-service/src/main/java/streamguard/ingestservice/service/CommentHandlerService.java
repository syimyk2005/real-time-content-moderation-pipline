package streamguard.ingestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import streamguard.ingestservice.model.CommentDto;

@Service
@RequiredArgsConstructor
public class CommentHandlerService {

    public String handleComment(CommentDto comment) {
        return "Comment received successfully";
    }


}
