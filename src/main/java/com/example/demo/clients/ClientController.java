package com.example.demo.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("client")
public class ClientController {

    private final ClientRepository repository;


    @PostMapping
    public void insert(@RequestBody Client client){
        repository.save(client);
    }

    @GetMapping
    public List<Client> list(){
        return repository.findAll();
    }

}
