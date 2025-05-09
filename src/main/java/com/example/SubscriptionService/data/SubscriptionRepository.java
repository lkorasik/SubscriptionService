package com.example.SubscriptionService.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Query(value = """
        WITH
            top_data AS (
                SELECT
                    s.link AS link,
                    COUNT(DISTINCT s.user_id) AS user_count
                FROM subscriptions s
                GROUP BY s.link
                ORDER BY user_count DESC
                LIMIT :topLength
            )
        SELECT
            td.link
        FROM top_data td
    """, nativeQuery = true)
    public List<String> getTopK(int topLength);
}
