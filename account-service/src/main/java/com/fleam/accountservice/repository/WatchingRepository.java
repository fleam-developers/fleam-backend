package com.fleam.accountservice.repository;

import com.fleam.accountservice.entity.Watching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchingRepository extends JpaRepository<Watching, Long> {

    List<Watching> findAllByUserId(long userId);

}
