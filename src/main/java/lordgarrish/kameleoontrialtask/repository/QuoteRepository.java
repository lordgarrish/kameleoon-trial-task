package lordgarrish.kameleoontrialtask.repository;

import lordgarrish.kameleoontrialtask.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Query(value = "SELECT * FROM quotes WHERE user_id = :user_id", nativeQuery = true)
    List<Quote> findAllByUserId(@Param("user_id") Long UserId);

    @Query(value = "SELECT * FROM quotes ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Quote> getRandom();
}
