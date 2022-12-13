package com.quiz.msclient.service;

import com.quiz.msclient.exception.EmailAlreadyExitsException;
import com.quiz.msclient.exception.InvalidEmailException;
import com.quiz.msclient.exception.InvalidPasswordException;
import com.quiz.msclient.functions.EmailValidator;
import com.quiz.msclient.functions.PasswordValidator;
import com.quiz.msclient.model.Client;
import com.quiz.msclient.model.Phone;
import com.quiz.msclient.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BCryptPasswordEncoder bcrypt;
    @Autowired
    private PasswordValidator passwordValidator;
    List<String> listEmail = new ArrayList<>();
    public Client agregar(Client client) {
        List<Phone> phone =
                client.getPhones().stream()
                .map(phones -> phones
                        .builder()
                        .client(client)
                        .cityCode(phones.getCityCode())
                        .countryCode(phones.getCountryCode())
                        .number(phones.getNumber())
                        .build()).collect(Collectors.toList());
        client.setPhones(phone);
        if (passwordValidator.isValid(client.getPassword().toString()) == false){
            throw new InvalidPasswordException("La contraseña es inválida.");
        }
        client.setPassword(bcrypt.encode(client.getPassword()));
        List<String> correos = clientRepository.getEmail();
        listEmail.addAll(correos);
        if (listEmail.contains(client.getEmail()) == true) {
            throw new EmailAlreadyExitsException("El correo ingresado ya existe.");
        }
        if (EmailValidator.isValid(client.getEmail()) == false) {
            throw new InvalidEmailException("El correo esta mal ingresado.");
        }
        return clientRepository.save(client);
    }
}