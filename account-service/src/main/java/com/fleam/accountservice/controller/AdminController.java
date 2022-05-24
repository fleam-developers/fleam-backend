package com.fleam.accountservice.controller;

import com.fleam.accountservice.dto.StatisticsDTO;
import com.fleam.accountservice.dto.UserDTO;
import com.fleam.accountservice.entity.User;
import com.fleam.accountservice.mapper.Mapper;
import com.fleam.accountservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private Mapper mapper;

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsDTO> getStatistics(){
        if (!adminService.isAdmin()) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        StatisticsDTO statisticsDTO = new StatisticsDTO(
                adminService.getTotalWatchCount(),
                adminService.getTotalCommentCount(),
                adminService.getTotalRatingCount());
        return new ResponseEntity<>(statisticsDTO, HttpStatus.OK);
    }

    @GetMapping("/creators")
    public ResponseEntity<List<UserDTO>> get(){
        if (!adminService.isAdmin()) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(mapper.objectsToDTOs(adminService.getAllCreators(), UserDTO.class), HttpStatus.OK);
    }

    @PostMapping("/revertCreator/{userId}")
    public ResponseEntity<UserDTO> revertCreator(@PathVariable long userId){
        if (!adminService.isAdmin()) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        User user = adminService.revertCreator(userId);
        if (user == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mapper.objectToDTO(user, UserDTO.class), HttpStatus.OK);
    }


}
