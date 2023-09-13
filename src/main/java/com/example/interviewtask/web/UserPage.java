package com.example.interviewtask.web;

import com.example.interviewtask.model.dto.*;
import com.example.interviewtask.service.repo.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserPage {

    /**      Pay attention that this is:
     * Basic application for making CRUD operations
     * Most of the things can be done a lot better
     * security could be added, and user could have
     * password for authentication and not
     * allowing some of the operations
     *   THE APP SHOULD BE TESTED ON LOCALHOST
     */

    private final UserService userService;

    public UserPage(UserService userService) {
        this.userService = userService;
    }


    @ApiResponse(description = "method which accept get request from http")
    @Operation(description = "html file is returned to show appropriate visualization")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getUser(Model model) {
        if (!model.containsAttribute("importDto")) {
            model.addAttribute("importDto", new ImportDto());
        }
        return "create";
    }

    @Operation(description = "mapping the dto and validate it and if is valid record it in the base," +
            "html file is returned to show appropriate visualization")
    @ApiResponse(description = "method for accepting post request from http")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createUser(ImportDto importDto, BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("importDto", importDto);

            redirectAttributes.addFlashAttribute("org.springframework" +
                    ".validation.BindingResult.userRegisterBindingModel", bindingResult);
        }

        String message = userService.validate(importDto,"create");

        ModelAndView modelAndView = new ModelAndView();


        if (message.contains("added")) {
            modelAndView.setViewName("success");
            modelAndView.addObject("success", message);
            userService.createUser(importDto);
            return modelAndView;
        } else {
            modelAndView.setViewName("invalid");
            modelAndView.addObject("invalid", message);
            return modelAndView;
        }
    }

    @Operation(description = "mapping the dto and checking if given first name is valid," +
            " if it is delete operation is made")
    @ApiResponse(description = "method for accepting post request from http")
    @PostMapping(value = "/delete")
    public ModelAndView deleteUser(DeleteDtoByFirstName deleteDtoByFirstName, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        String message = userService.validate(deleteDtoByFirstName, "delete");

        userService.deleteUserByFirstName(deleteDtoByFirstName.getFirstName());
        ModelAndView modelAndView = new ModelAndView();
        if(message.equals("No such user")){
            modelAndView.setViewName("invalid");
            modelAndView.addObject("invalid","No such user");
            return modelAndView;
        }else {
            modelAndView.setViewName("success");
            modelAndView.addObject("success", "Successfully deleted "
                    + deleteDtoByFirstName.getFirstName());
            return modelAndView;
        }
    }

    @Operation(description = "html file with appropriate message is returned")
    @ApiResponse(description = "method for accepting get request")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getFirstName(Model model) {
        if (!model.containsAttribute("deleteDtoByFirstName")) {
            model.addAttribute("deleteDtoByFirstName", new DeleteDtoByFirstName());
        }
        return "delete";
    }
    @Operation(description = "html file with appropriate message is returned")
    @ApiResponse(description = "method for accepting get request")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String viewUpdateUser(Model model) {
        if (!model.containsAttribute("updateUserByFirstNameDto")) {
            model.addAttribute("updateUserByFirstNameDto", new UpdateUserByFirstNameDto());
        }
        return "update";
    }

    @Operation(description = "checking if there is errors ,validating the dto," +
            " if firstName exist saving the changes if not appropriate message is returned")
    @ApiResponse(description = "method for accepting post request")
    @PostMapping(value = "/update")
    public ModelAndView updateUser(UpdateUserByFirstNameDto updateUserByFirstNameDto,BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("updateUserByFirstNameDto", updateUserByFirstNameDto);

            redirectAttributes.addFlashAttribute("org.springframework" +
                    ".validation.BindingResult.userRegisterBindingModel", bindingResult);
        }

        String message = userService.validate(updateUserByFirstNameDto,"update");

        ModelAndView modelAndView = new ModelAndView();

        if(message.contains("added")){
            modelAndView.setViewName("success");
            userService.updateByFirstName(updateUserByFirstNameDto.getFirstName(),updateUserByFirstNameDto.getEmail());
            modelAndView.addObject("success",message);
            return modelAndView;
        }else {
            modelAndView.setViewName("invalid");
            modelAndView.addObject("invalid",message);
            return modelAndView;
        }

    }


    @ApiResponse(description = "method for accepting get request")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getUserById(Model model) {
        if (!model.containsAttribute("idDto")) {
            model.addAttribute("idDto", new IdDto());
        }
        return "get";
    }

    @Operation(description = "validating and check if user exist, appropriate messages are returned")
    @ApiResponse(description = "method for accepting post request")
    @PostMapping(value = "/get")
    public ModelAndView postUserById(IdDto idDto, BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("idDto", idDto);

            redirectAttributes.addFlashAttribute("org.springframework" +
                    ".validation.BindingResult.userRegisterBindingModel", bindingResult);
        }
        ExportUserByIdDto firstById = userService.findFirstById(idDto.getId());
        ModelAndView modelAndView = new ModelAndView();
        if (firstById == null) {
            modelAndView.setViewName("invalid");
            modelAndView.addObject("invalid", "User does not exist");
            return modelAndView;
        } else {
            modelAndView.setViewName("success");
            modelAndView.addObject("success", firstById.toString());
            return modelAndView;
        }
    }

    @Operation(description = "when this get request is called the operation for retrieving all users is immediately done" +
            "and every user shown")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ModelAndView getAllUsers() {

        List<ExportUserByIdDto> allUsers = userService.findAllUsers();
        ModelAndView modelAndView = new ModelAndView();
        if(!allUsers.isEmpty()) {
            modelAndView.setViewName("success");
            modelAndView.addObject("success", allUsers.stream().map(dto -> "User: " + dto.toString())
                    .collect(Collectors.joining("\n")));
            return modelAndView;
        }else {
            modelAndView.setViewName("invalid");
            modelAndView.addObject("invalid", "No users in the base");
            return modelAndView;
        }

    }

}
