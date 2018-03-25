package fr.lapausedev.springboot2.user.facade.controller;


import fr.lapausedev.springboot2.exception.ResourceNotFoundException;
import fr.lapausedev.springboot2.user.facade.transport.assembler.UserDTOAssembler;
import fr.lapausedev.springboot2.user.facade.transport.object.UserDTO;
import fr.lapausedev.springboot2.user.domain.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@Api(value = "User API", description = "the user API")
@Controller
@RequestMapping(value = "/webService/user")
public class UserSpringWebRestController {

    @Autowired
    private UserService service;

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
        return assembler.fromModel(service.findById(id).orElseThrow(() -> new ResourceNotFoundException()));
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
    public Stream<UserDTO> list() {
        return service.findAll()
                .map(entity -> assembler.fromModel(entity));
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
        return assembler.fromModel(service.save(assembler.toModel(request)));
    }

    @ApiOperation(value = "delete a user", response = UserDTO.class, tags = "Users")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User deleted", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Request rejected", response = Error.class)
    })
    @RequestMapping(method = RequestMethod.DELETE,
            path = "/{userId}")
    public ResponseEntity<Void> delete(@ApiParam(value = "userId", required = true) @PathVariable(value = "userId") int id) {
        service.delete(service.findById(id).orElseThrow(() -> new ResourceNotFoundException()));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}

