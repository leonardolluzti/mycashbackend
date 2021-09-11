package com.mycash.mycash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycash.mycash.model.UserInformation;

@Repository
public interface UserInformationRepository  extends JpaRepository<UserInformation, Long>  {

}
