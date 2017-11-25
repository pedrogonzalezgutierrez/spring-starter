package com.kiesoft.sstarter.controller.rest.user;

import com.kiesoft.sstarter.dto.user.UserDTO;
import com.kiesoft.sstarter.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static com.kiesoft.sstarter.controller.rest.AbstractRestController.ROUTING_REST_CONTROLLER;
import static com.kiesoft.sstarter.controller.rest.user.AbstractRestUserController.ROUTING_REST_USER_CONTROLLER;

@RestController
@RequestMapping(ROUTING_REST_CONTROLLER + ROUTING_REST_USER_CONTROLLER)
public class RestUserController extends AbstractRestUserController {

    protected final UserService userService;

    @Autowired
    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = ROUTING_FIND_ONE, method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findOne(@PathVariable Long id) {
        UserDTO userDTO = userService.findOne(id);
        if (Objects.isNull(userDTO)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @RequestMapping(value = ROUTING_FIND_BY_USERNAME, method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findByUsername(@PathVariable String username) {
        UserDTO userDTO = userService.findByUsername(username);
        if (Objects.isNull(userDTO)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

}
