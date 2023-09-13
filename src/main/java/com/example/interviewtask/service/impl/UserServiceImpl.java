package com.example.interviewtask.service.impl;

import com.example.interviewtask.Util.ValidationUtil;
import com.example.interviewtask.model.dto.*;
import com.example.interviewtask.model.entity.User;
import com.example.interviewtask.repository.UserRepository;
import com.example.interviewtask.service.repo.UserService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@OpenAPIDefinition (
        info = @Info(
                title = "Application for testing CRUD operations",
                description =
               "Basic application for making CRUD operations. Application is using " +
                "thymeleaf and basic html and css files to make connection with the client. " +
                "We have User entity which is connected to the base " +
                "different types of dto's which import and export " +
                "the information. " +
                "Service layer is the core layer which does every command " +
                "the main dependency of service layer is other core layer " +
                "which is repository layer - it makes the connection with the base" +
                "service layer also has other dependencies which are important to transfer," +
                "validate the data before recording it.",
                version = "1.0.0"
        )
)

@Service


public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;


    public UserServiceImpl(UserRepository userRepository, ValidationUtil validationUtil, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }


    @Override
    @Operation(description = "accept id parameter for retrieving user,map the user to dto" +
            " and return if user exist or else null")
    public ExportUserByIdDto findFirstById(Long id) {
        Optional<User> firstById = userRepository.findFirstById(id);

        return firstById.map(user -> mapper.map(user, ExportUserByIdDto.class)).orElse(null);
    }

    @Override
    @Operation(description = "export all users from using dto")
    public List<ExportUserByIdDto> findAllUsers() {
        return userRepository.findAllUsers().stream()
                .map(user -> mapper.map(user, ExportUserByIdDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @Operation(description = "delete all user by firstName using dto")
    public void deleteUserByFirstName(String firstName) {
        userRepository.deleteUserByFirstName(firstName);
    }

    @Override
    @Operation(description = "create user using dto")
    public void createUser(ImportDto dto) {
        User user = mapper.map(dto, User.class);
        try {
            user.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        } catch (DateTimeParseException e) {
            System.out.println("Date won't be enrolled to the base");
        }

        userRepository.save(user);
    }

    @Override
    @Transactional
    @Operation(description = "update user by firstName and replace it's email")
    public void updateByFirstName(String firstName, String replacer) {
        userRepository.updateAllByFirstName(firstName, replacer);
    }

    @Override
    @Operation(description = "additional validation using Base Class dto and returning appropriate messages")
    public <E> String validate(E dto,String command) {
        StringBuilder sb = new StringBuilder();
        Set<ConstraintViolation<E>> valid = validationUtil.isValid(dto);

        Optional<User> firstByFirstName = userRepository.findFirstByFirstName(((FirstNameBaseDto) dto).getFirstName());


        if(command.contains("create")){
            if(firstByFirstName.isPresent()) {
                return "There is user with such first name";
            }
        }else if(command.contains("delete") || command.contains("update")) {
            if(firstByFirstName.isEmpty()) {
                return "No such user";
            } else if(command.contains("update")) {
                Optional<User> firstByEmailAddress = userRepository.findFirstByEmailAddress(((UpdateUserByFirstNameDto) dto).getEmail());
                if(firstByEmailAddress.isPresent()){
                    return "User with the given email already exists";
                }
            }
        }

        if (valid.isEmpty()) {
            sb.append("Successfully added");
        } else {
            valid.forEach(dtoMessage -> sb.append(dtoMessage.getMessage())
                    .append(System.lineSeparator()));
        }
        return sb.toString();
    }



}
