package lordgarrish.kameleoontrialtask.controller;

import lordgarrish.kameleoontrialtask.dto.QuoteDTO;
import lordgarrish.kameleoontrialtask.entity.Quote;
import lordgarrish.kameleoontrialtask.exception.QuoteNotFoundException;
import lordgarrish.kameleoontrialtask.exception.UserNotFoundException;
import lordgarrish.kameleoontrialtask.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {
    @Autowired
    private QuoteService quoteService;

    @PostMapping
    public ResponseEntity<QuoteDTO> addQuote(@RequestBody Quote quote, @RequestParam Long userId) {
        try {
            return new ResponseEntity<>(quoteService.save(quote, userId), HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<QuoteDTO>> getQuotesList() {
        return new ResponseEntity<>(quoteService.getAll(), HttpStatus.OK);
    }

    @GetMapping(params = "userId") //get all quotes of particular user
    public ResponseEntity<List<QuoteDTO>> getUsersQuotesList(@RequestParam Long userId) {
        try {
            return new ResponseEntity<>(quoteService.getAllUserQuotes(userId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{quoteId}") //get one quote of particular user
    public ResponseEntity<QuoteDTO> getOneQuote(@PathVariable Long quoteId) {
        try {
            return new ResponseEntity<>(quoteService.getOneById(quoteId), HttpStatus.OK);
        } catch (QuoteNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(params="random")
    public ResponseEntity<QuoteDTO> getRandomQuote() {
        try {
            return new ResponseEntity<>(quoteService.getRandom(), HttpStatus.OK);
        } catch (QuoteNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{quoteId}")
    public ResponseEntity<QuoteDTO> modifyQuote(@PathVariable Long quoteId, @RequestBody Quote quote) {
        try {
            return new ResponseEntity<>(quoteService.modify(quoteId, quote), HttpStatus.OK);
        } catch (QuoteNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{quoteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuote(@PathVariable Long quoteId) {
        quoteService.delete(quoteId);
    }

    @PostMapping("/{quoteId}/vote")
    public ResponseEntity<QuoteDTO> voteOnQuote(@PathVariable Long quoteId, @RequestParam Long userId,
                                                @RequestParam boolean upvote) {
        try {
            return new ResponseEntity<>(quoteService.vote(quoteId, userId, upvote), HttpStatus.OK);
        } catch (QuoteNotFoundException | UserNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/top10")
    public ResponseEntity<List<QuoteDTO>> getTopTenQuotes() {
        return new ResponseEntity<>(quoteService.getTopTen(), HttpStatus.OK);
    }

    @GetMapping("/bottom10")
    public ResponseEntity<List<QuoteDTO>> getBottomTenQuotes() {
        return new ResponseEntity<>(quoteService.getBottomTen(), HttpStatus.OK);
    }
}