package com.example.demoPR.service;


import com.example.demoPR.model.Address;

import java.util.List;

public interface AddressService {

    public List<Address> getAddress();
    public List<Address> getConnectionTable();
    public void getNewTable();

    public List<Address> sortedAddresses();

    public List<Address> orderByStreet();
}
