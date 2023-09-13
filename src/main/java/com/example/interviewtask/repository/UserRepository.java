package com.example.interviewtask.repository;

import com.example.interviewtask.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User>findFirstById(Long id);

    @Query("SELECT u from  User u order by u.lastName asc , u.dateOfBirth asc")
    List<User>findAllUsers ();

    void deleteUserByFirstName(String firstName);

    @Modifying
    @Query("update User u SET u.emailAddress = :replacer where u.firstName = :firstName")
    void updateAllByFirstName(@Param("firstName") String firstName,@Param("replacer") String replacer);

    @Query("SELECT u.firstName from User u where u.firstName like :username")
    String getFirstName (@Param("username") String firstName);


    Optional<User>findFirstByFirstName(String firstName);

    Optional<User>findFirstByEmailAddress(String emailAddress);
}
