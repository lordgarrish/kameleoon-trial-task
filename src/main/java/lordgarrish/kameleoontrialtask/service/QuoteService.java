package lordgarrish.kameleoontrialtask.service;

import lordgarrish.kameleoontrialtask.dto.QuoteDTO;
import lordgarrish.kameleoontrialtask.entity.Quote;
import lordgarrish.kameleoontrialtask.entity.User;
import lordgarrish.kameleoontrialtask.entity.Vote;
import lordgarrish.kameleoontrialtask.exception.QuoteNotFoundException;
import lordgarrish.kameleoontrialtask.exception.UserNotFoundException;
import lordgarrish.kameleoontrialtask.repository.QuoteRepository;
import lordgarrish.kameleoontrialtask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class QuoteService {
    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private UserRepository userRepository;

    public QuoteDTO save(Quote quote, Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new UserNotFoundException("Requested user not found");
        }
        quote.setOwner(user.get());
        return QuoteDTO.toDto(quoteRepository.save(quote));
    }

    public List<QuoteDTO> getAll() {
        return quoteRepository.findAll().stream().map(QuoteDTO::toDto).toList();
    }

    public List<QuoteDTO> getAllUserQuotes(Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new UserNotFoundException("Requested user not found");
        }
        return quoteRepository.findAllByUserId(userId).stream().map(QuoteDTO::toDto).toList();
    }

    public QuoteDTO getOneById(Long quoteId) throws QuoteNotFoundException {
        Optional<Quote> quote = quoteRepository.findById(quoteId);
        if(quote.isEmpty()) {
            throw new QuoteNotFoundException("Requested quote not found");
        }
        return QuoteDTO.toDto(quote.get());
    }

    public QuoteDTO getRandom() throws QuoteNotFoundException {
        Optional<Quote> quote = quoteRepository.getRandom();
        if(quote.isEmpty()) {
            throw new QuoteNotFoundException("Requested quote not found");
        }
        return QuoteDTO.toDto(quote.get());
    }

    public QuoteDTO modify(Long quoteId, Quote patchedQuote) throws QuoteNotFoundException {
        Optional<Quote> quote = quoteRepository.findById(quoteId);
        if(quote.isEmpty()) {
            throw new QuoteNotFoundException("Requested quote not found");
        }
        Quote patchable = quote.get();
        if(patchedQuote.getContent() != null) {
            patchable.setContent(patchedQuote.getContent());
        }
        return QuoteDTO.toDto(quoteRepository.save(patchable));
    }

    public void delete(Long quoteId) {
        if (quoteRepository.existsById(quoteId)) {
            try {
                quoteRepository.deleteById(quoteId);
            } catch (EmptyResultDataAccessException e) {
            }
        }
    }

    public QuoteDTO vote(Long quoteId, Long userId, boolean upvote) throws QuoteNotFoundException, UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new UserNotFoundException("Requested user not found");
        }
        Optional<Quote> quoteOptional = quoteRepository.findById(quoteId);
        if(quoteOptional.isEmpty()) {
            throw new QuoteNotFoundException("Requested quote not found");
        }
        Quote quote = quoteOptional.get();
        Vote vote;
        if (upvote) {
            vote = new Vote(Vote.VoteType.UPVOTE);
            quote.upvote();
        } else {
            vote = new Vote(Vote.VoteType.DOWNVOTE);
            quote.downvote();
        }
        vote.setVoter(user.get());
        quote.addVote(vote);
        return QuoteDTO.toDto(quoteRepository.save(quote));
    }

    public List<QuoteDTO> getTen(PageRequest page) {
        return quoteRepository.findAll(page).getContent().stream()
                .map(QuoteDTO::toDto)
                .toList();
    }
}
