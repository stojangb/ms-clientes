package com.quiz.msclient.controller;

import com.quiz.msclient.model.Client;
import com.quiz.msclient.service.ClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    @Autowired
    private ClientService clientService;

    /**
     * Inserta usuario
     *
     * @return Retorna datos de client
     */
    @ApiOperation(value = "Inserta usuario, retorna datos del cliente.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Found the data"),
            @ApiResponse(code = 400, message = "Invalid data supplied"),
            @ApiResponse(code = 404, message = "Data not found")
    })
    @PostMapping("/insert")
    public ResponseEntity<Client> agregar(@RequestBody Client client){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.agregar(client));
    }
}
