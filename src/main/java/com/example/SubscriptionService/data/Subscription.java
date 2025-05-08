package com.example.SubscriptionService.data;

import jakarta.persistence.*;

@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    private long id;
    @ManyToOne
//    @JoinColumn(name = "user_id")
    private User user;
}
