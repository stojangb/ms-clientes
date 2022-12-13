package com.quiz.msclient.repository;

import com.quiz.msclient.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    @Query("select c.email from Client c")
    List<String> getEmail();
    @Query("select c.name from Client c")
    List<String> getName();
}
