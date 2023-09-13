package com.example.interviewtask.service.repo;

import com.example.interviewtask.model.dto.ExportAllDto;
import com.example.interviewtask.model.dto.ExportUserByIdDto;
import com.example.interviewtask.model.dto.ImportDto;
import com.example.interviewtask.model.entity.User;

import java.util.List;

public interface UserService {

    ExportUserByIdDto findFirstById(Long id);

    List<ExportUserByIdDto>findAllUsers ();

    void deleteUserByFirstName(String firstName);

    void createUser(ImportDto dto);

    void updateByFirstName(String firstName, String replacer);

   <E> String validate(E dto,String command);


}
