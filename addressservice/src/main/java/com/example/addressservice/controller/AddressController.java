package com.example.addressservice.controller;

import com.example.addressservice.entity.Address;
import com.example.addressservice.repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {


    @Autowired
    private AddressRepo addressRepo;

    @PostMapping
    public String createAddress(@RequestBody Address address){
        addressRepo.save(address);
        return " Address Added";
    }


   @GetMapping
   public ResponseEntity<List<Address>>getAllAddress(){
        List<Address>addList=new ArrayList<>();
        addressRepo.findAll().forEach(addList::add);
        return new ResponseEntity<List<Address>>(addList,HttpStatus.OK);

   }
   @GetMapping("/{id}")
    public ResponseEntity<Address>getAddressById(@PathVariable int id){
       Optional<Address> address=addressRepo.findById(id);
       if (address.isPresent()){
           return new ResponseEntity<Address>(address.get(),HttpStatus.FOUND);
       }else {
           return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
       }

   }
}