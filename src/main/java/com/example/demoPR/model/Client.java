package com.example.demoPR.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private int id;

    @Column(name = "client_name")
    private String name;

    @Column(name = "personal_name")
    private String personalName;

    @Column(name = "address_id")
    private Long addressId;

    public Client(String name, String personal_name, Long addressId){

        this.name = name;
        this.personalName = personal_name;
        this.addressId = addressId;
    }
}
