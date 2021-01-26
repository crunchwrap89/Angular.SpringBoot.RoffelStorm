package com.teamroffel.roffelstormAUTHAPI.repository;

import org.springframework.data.repository.CrudRepository;

import com.teamroffel.roffelstormAUTHAPI.models.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
