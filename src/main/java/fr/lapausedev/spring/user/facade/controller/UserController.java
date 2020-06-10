package fr.lapausedev.spring.user.facade.controller;


import fr.lapausedev.spring.exception.ResourceNotFoundException;
import fr.lapausedev.spring.user.facade.transport.assembler.UserDTOAssembler;
import fr.lapausedev.spring.user.facade.transport.object.UserDTO;
import fr.lapausedev.spring.user.persistence.repository.UserRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "User API", description = "the user API", tags = "Users")
@Controller
@RequestMapping(value = "${webservice.root.path}/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserDTOAssembler assembler;

    @ApiOperation(value = "find a user by Id", response = UserDTO.class, tags = "Users")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User found", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Request rejected", response = Error.class)
    })
    @RequestMapping(method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            path = "/{userId}")
    @ResponseBody
    public UserDTO findById(@ApiParam(value = "userId", required = true) @PathVariable(value = "userId") int id) {
        return assembler.fromEntity(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException()));
    }

    @ApiOperation(value = "find all users", response = UserDTO.class, tags = "Users")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User found", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Request rejected", response = Error.class)
    })
    @RequestMapping(method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            path = "/")
    @ResponseBody
    public List<UserDTO> list() {
        List<UserDTO> list = new ArrayList<>();
        repository.findAll().forEach(entity -> list.add(assembler.fromEntity(entity)));
        return list;
    }

    @ApiOperation(value = "save a user", response = UserDTO.class, tags = "Users")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User saved", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Request rejected", response = Error.class)
    })
    @RequestMapping(method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            name = "/")
    @ResponseBody
    public UserDTO save(@ApiParam(value = "userRequest", required = true) @RequestBody UserDTO request) {
        return assembler.fromEntity(repository.save(assembler.toEntity(request)));
    }

    @ApiOperation(value = "delete a user", response = UserDTO.class, tags = "Users")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User deleted", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Request rejected", response = Error.class)
    })
    @RequestMapping(method = RequestMethod.DELETE,
            path = "/{userId}")
    public ResponseEntity<Void> delete(@ApiParam(value = "userId", required = true) @PathVariable(value = "userId") int id) {
        repository.delete(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException()));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}

