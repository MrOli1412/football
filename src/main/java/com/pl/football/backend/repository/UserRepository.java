package com.pl.football.backend.repository;

import com.pl.football.backend.dto.user.UserQueryDTO;
import com.pl.football.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findById(UUID uuid);

//    @Query("SELECT user.id,club.id FROM User user" +
//            " join Club club on user.club.id = club.id " +
//            "where exists ")

//    select  count(1) from application_user client where client.id = '' and client.club_id = '' for club

    //select count(1) from application_user client join club c on client.club_id = '55a4c4ab-7dd2-4123-a09e-7a5320c8787b'
    //join team t on c.id ='26408500-730f-48d8-9704-bf88e34e90cd' where client.id = '0f3506b6-4682-4fc3-8dd3-4ae0802baf5e'
    //and t.id = 'a35eeb52-ed37-4cb8-a142-dcde1107c18d' for team

//    select count(1) from application_user client join club c on client.club_id = '26408500-730f-48d8-9704-bf88e34e90cd'
//    join team t on c.id ='26408500-730f-48d8-9704-bf88e34e90cd'
//    join player p on t.id = 'a35eeb52-ed37-4cb8-a142-dcde1107c18d'
//    where client.id = '0f3506b6-4682-4fc3-8dd3-4ae0802baf5e'
//    and p.id = 'dc8d3af9-c5a9-4215-bbf2-59b0cf27a90e' for player


//    select count(1) from application_user client join club c on client.club_id = '26408500-730f-48d8-9704-bf88e34e90cd'
//    join team t on c.id ='26408500-730f-48d8-9704-bf88e34e90cd'
//    join  dress_color dc on t.id =  'a35eeb52-ed37-4cb8-a142-dcde1107c18d'
//    where client.id = '0f3506b6-4682-4fc3-8dd3-4ae0802baf5e'
//    and dc.id = 'b30cf0d3-b642-4ccf-a530-31cd782cf54e' for dress

//    select count(1) from application_user client join club c on client.club_id = '26408500-730f-48d8-9704-bf88e34e90cd'
//    join team t on c.id ='26408500-730f-48d8-9704-bf88e34e90cd'
//    join   staff_person sp on t.id  =  'a35eeb52-ed37-4cb8-a142-dcde1107c18d'where
//    client.id = '0f3506b6-4682-4fc3-8dd3-4ae0802baf5e'
//    and sp.id = '5d72b95c-e033-4a52-91d0-adc260b3f379' for staff persson









}
