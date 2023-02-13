package lordgarrish.kameleoontrialtask.entity;

import javax.persistence.*;
import java.time.Instant;

@Embeddable
public class Vote {
    @Enumerated(EnumType.STRING)
    private VoteType voteType;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User voter;
    @Column(name = "date_of_voting", updatable = false)
    private Instant dateOfVote;

    public Vote() {}

    public Vote(VoteType voteType) {
        this.voteType = voteType;
        dateOfVote = Instant.now();
    }

    public enum VoteType {
        DOWNVOTE,
        UPVOTE
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoter(User voter) {
        this.voter = voter;
    }

    public Instant getDateOfVote() {
        return dateOfVote;
    }
}
