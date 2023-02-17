package com.example.demoPR.service.impl;

import com.example.demoPR.model.Address;
import com.example.demoPR.repository.AddressRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.demoPR.service.AddressService;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{

    private final ResourceLoader resourceLoader;
    private final AddressRepository repository;

    public AddressServiceImpl(ResourceLoader resourceLoader, AddressRepository repository) {
        this.resourceLoader = resourceLoader;
        this.repository = repository;
    }

    public List<Address> sortedAddresses(){

        return repository.orderByHouse();
    }

    public List<Address> orderByStreet(){

        return repository.findByStreet("Blumenweg");
    }
    @Override
    public List<Address> getConnectionTable(){


        return repository.findAll();
    }
    @Override
    public List<Address> getAddress() {

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser = null;

        try {
            Resource resource = resourceLoader.getResource("classpath:client.xml");
            InputStream inputStream = resource.getInputStream();
            parser = factory.createXMLStreamReader(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("Check file path");
        } catch (XMLStreamException e) {
            System.out.println(e.getMessage());
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

        List<Address> resultList = new ArrayList<>();

        try {
            while (true) {
                assert parser != null;
                if (!parser.hasNext())
                    break;
                int event = parser.next();
                if (event == XMLStreamConstants.START_ELEMENT) {
                    if (parser.getLocalName().equalsIgnoreCase("address")) {
                          resultList.add(new Address(
                                  Long.parseLong(parser.getAttributeValue(0)),
                                  parser.getAttributeValue(1),
                                  parser.getAttributeValue(2),
                                  Short.parseShort(parser.getAttributeValue(3)),
                                  Short.parseShort(parser.getAttributeValue(4)),
                                  Short.parseShort(parser.getAttributeValue(5))
                          ));
                    }
                }
            }

        } catch (XMLStreamException e) {
            System.out.println(e.getMessage());
        }

        return resultList;
    }


    @Override
    public void getNewTable(){

        Address sample1= new Address("Padopur","New_street", (short)1 , (short)1 , (short)1);
        repository.save(sample1);

        List<Address> sampleList2= new ArrayList<Address>();
        sampleList2.add(new Address("RRRR","New_street", (short)2 , (short)1 , (short)3));
        sampleList2.add(new Address("Brown","New_street", (short)5 , (short)5 , (short)5));
        repository.saveAll(sampleList2);
    }



}
