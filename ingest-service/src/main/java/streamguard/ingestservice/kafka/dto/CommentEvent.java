package streamguard.ingestservice.kafka.dto;

public record CommentEvent (
        String userId,
        String message,
        String status
) {}
