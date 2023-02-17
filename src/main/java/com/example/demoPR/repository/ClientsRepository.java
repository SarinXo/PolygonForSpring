package com.example.demoPR.repository;

import com.example.demoPR.model.Client;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientsRepository extends JpaRepository<Client, Long> {

    @Query("SELECT u FROM Client u")
    List<Client> findAllClients();

    @Query(value = "SELECT u FROM Client u")
    List<Client> findAllClientsSortedByName(Sort sort);





}
