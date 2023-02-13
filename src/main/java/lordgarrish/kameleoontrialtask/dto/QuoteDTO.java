package lordgarrish.kameleoontrialtask.dto;

import lordgarrish.kameleoontrialtask.entity.Quote;
import lordgarrish.kameleoontrialtask.entity.Vote;

import java.time.Instant;
import java.util.List;

public class QuoteDTO {
    private Long id;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;
    private long ownerId;
    private List<Vote> votes;
    private int score;

    private QuoteDTO(Long id, String content, Instant createdAt, Instant updatedAt, Long ownerId, List<Vote> votes,
                     int score) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.ownerId = ownerId;
        this.votes = votes;
        this.score = score;
    }

    public static QuoteDTO toDto(Quote quote) {
        return new QuoteDTO(quote.getId(), quote.getContent(), quote.getCreatedAt(), quote.getUpdatedAt(),
                quote.getOwnerId(), quote.getVotes(), quote.getScore());
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public int getScore() {
        return score;
    }
}
