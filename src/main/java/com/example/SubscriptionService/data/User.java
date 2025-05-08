package com.example.SubscriptionService.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "`users`")
public class User {
    @Id
    private long id;
    @OneToMany(mappedBy = "user")
    private List<Subscription> subscriptions;
}
