package streamguard.ingestservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import streamguard.ingestservice.model.dto.CommentRequest;
import streamguard.ingestservice.model.dto.CommentResponse;
import streamguard.ingestservice.service.CommentHandlerService;

@RestController
@RequiredArgsConstructor
public class CommentHandlerController {

    private final CommentHandlerService commentHandlerService;

    @PostMapping("v1/comments")
    ResponseEntity<CommentResponse> handleComments(@Valid @RequestBody CommentRequest comment) {
        return ResponseEntity.accepted().body(commentHandlerService.handleComment(comment));
    }


}
