package com.quiz.msclient.service;

import com.quiz.msclient.exception.EmailAlreadyExitsException;
import com.quiz.msclient.exception.InvalidEmailExecption;
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
    List<String> listaCorreo = new ArrayList<>();
    boolean correoYaIngresado = false;
    boolean contrasenaSegura = false;


    public Client agregar(Client client) {
        List<Phone> phones =
                client.getPhones().stream()
                .map(phone1 -> {
                    return phone1
                            .builder()
                            .client(client)
                            .cityCode(phone1.getCityCode())
                            .countryCode(phone1.getCountryCode())
                            .number(phone1.getNumber())
                            .build();

                }).collect(Collectors.toList());

        client.setPhones(phones);


        if (passwordValidator.isValid(client.getPassword().toString()) == false){
            throw new InvalidPasswordException("La password es invalida");
        }


        client.setPassword(bcrypt.encode(client.getPassword()));
        List<String> correos = clientRepository.getEmail();
        listaCorreo.addAll(correos);

        if (listaCorreo.contains(client.getEmail()) == true) {
            throw new EmailAlreadyExitsException("El correo ingresado ya existe");
        }

        if (EmailValidator.isValid(client.getEmail()) == false) {
            throw new InvalidEmailExecption("El correo esta mal ingresado.");
        }

        return clientRepository.save(client);
    }

}