package com.example.demoPR.model.controller;


import com.example.demoPR.model.Address;
import com.example.demoPR.model.Client;
import com.example.demoPR.repository.AddressRepository;
import com.example.demoPR.repository.ClientsRepository;
import com.example.demoPR.service.ClientService;
import com.example.demoPR.service.AddressService;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
    private final ClientService clientService;
    private final AddressService addressService;

    private final AddressRepository addressRepository;
    private final ClientsRepository clientsRepository;


    public HelloController(ClientService clientService, AddressService addressService,
                           ClientsRepository clientsRepository, AddressRepository addressRepository) {

        this.clientService = clientService;
        this.addressService = addressService;
        this.clientsRepository = clientsRepository;
        this.addressRepository = addressRepository;
    }

    @GetMapping("internal/clients")
    public List<Client> getClients() {

        return clientService.getClients();
    }

    @GetMapping("internal/address")
    public List<Address> getAddress() {

        return addressService.getAddress();
    }

    @GetMapping("internal/ConnectionTable")
    public List<Address> getConnectionTable() {

        return addressService.getConnectionTable();
    }

    @GetMapping("internal/getNewTable")
    public List<Address> getNewTable() {

        addressService.getNewTable();
        return addressService.getConnectionTable();
    }

    @GetMapping("internal/orderByHouse")
    public List<Address> houses() {

        return addressService.sortedAddresses();
    }


    @GetMapping("internal/orderByStreet")
    public List<Address> orderByStreet() {

        return addressService.orderByStreet();
    }

    @GetMapping("internal/findAllClients")
    public List<Client> getClientsList() {

        return clientsRepository.findAllClients();
    }

    @GetMapping("internal/findInverseClients")
    public List<Client> getInverseClientsList() {

        return clientsRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));

    }

    @GetMapping("internal/findNameSortedClients")
    public List<Client> getClientsSortedByName() {

        return clientsRepository.findAllClientsSortedByName(JpaSort.unsafe("LENGTH(name)"));

    }

    @GetMapping("internal/findClientFromThis")
    public List<Address> g() {

        return addressRepository.findAddressFromThisStreet("Blumenweg", JpaSort.unsafe("LENGTH(city)"));

    }

    @GetMapping("internal/findSortedHouses")
    public List<Address> sortByHouse() {

        return addressRepository.findTop3ByFloor("Blumenweg");

    }

    @GetMapping("internal/findMyOwnAddress")
    public Address findByCityAndId(){
        return addressRepository.findAddressesByCityAndId(1L, "Plauen");

    }

    @GetMapping("internal/findMyOwnAddressNative")
    public Address findByCityAndIdNative(){
        return addressRepository.findAddressesByCityAndIdNative(1L, "Plauen");

    }

    //@GetMapping("/hello")
    //public String steepOne() {
    //    return "Привет!";
    //}

    //@GetMapping("/helloClient")
    //public String getHelloMessage(@RequestParam String name) {
      //  return "hello, " + name;
    //}


}
