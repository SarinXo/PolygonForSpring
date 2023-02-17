package com.example.demoPR.repository;

import com.example.demoPR.model.Address;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByStreet(String street);

    @Query(value = "SELECT u FROM Address u ORDER BY u.house DESC ")
    List<Address> orderByHouse();

    @Query("SELECT u FROM Address u WHERE u.street = ?1")
    List<Address> findAddressFromThisStreet( String street, Sort sort);

    @Query(
            value = "SELECT * FROM CLIENTS_DATA.Address u WHERE u.street = ?1",
            nativeQuery = true)
    List<Address> findTop3ByFloor(String id);
    //не учитывается то что в названии функции так как аннотация @Query

    @Query("SELECT u FROM Address u WHERE u.id = :id and u.city = :POP")
    Address findAddressesByCityAndId(
            @Param("id") Long id,
            @Param("POP") String city
    );

    @Query(value = "SELECT * FROM CLIENTS_DATA.Address u WHERE u.id = :id and u.city = :city",
            nativeQuery = true)
    Address findAddressesByCityAndIdNative(
            @Param("id") Long id,
            @Param("city") String city
    );

}
