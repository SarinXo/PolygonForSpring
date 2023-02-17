package com.example.demoPR.service.impl;

import com.example.demoPR.model.Client;
import com.example.demoPR.repository.ClientsRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.demoPR.service.ClientService;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    private final ResourceLoader resourceLoader;

    private final ClientsRepository clientsRepository;

    public ClientServiceImpl(ResourceLoader resourceLoader, ClientsRepository clientsRepository) {
        this.resourceLoader = resourceLoader;
        this.clientsRepository = clientsRepository;
    }

    public List<Client> getAllClients(){

        return clientsRepository.findAllClients();
    }


    @Override
    public List<Client> getClients() {

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

        List<Client> resultList = new ArrayList<>();

        try {
            while (true) {

                assert parser != null;
                if (!parser.hasNext())
                    break;

                int event = parser.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    if (parser.getLocalName().equalsIgnoreCase("client")) {
                        resultList.add(new Client(
                                parser.getAttributeValue(1),
                                parser.getAttributeValue(2),
                                Long.parseLong(parser.getAttributeValue(3))
                        ));
                    }
                }
            }

        } catch (XMLStreamException e) {
            System.out.println(e.getMessage());
        }
        return resultList;
    }
}
