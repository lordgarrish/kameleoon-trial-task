package lordgarrish.kameleoontrialtask.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name="quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quote_id")
    private Long id;
    @Column(name = "content")
    private String content;
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Instant createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;
    @ElementCollection
    @CollectionTable(name = "votes")
    private List<Vote> votes = new ArrayList<>();
    @Column(name = "score")
    private int score;

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

    public Long getOwnerId() {
        return owner.getId();
    }

    public List<Vote> getVotes() {
        return new ArrayList<>(votes);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setOwner(User quoteOwner) {
        this.owner = quoteOwner;
    }

    public boolean addVote(Vote vote) {
        return votes.add(vote);
    }

    public void upvote() {
        score++;
    }

    public void downvote() {
        score--;
    }

    public int getScore() {
        return score;
    }
}
