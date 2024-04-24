package com.example.realtimestreaming.Repository;

import com.example.realtimestreaming.Common.ErrorCode;
import com.example.realtimestreaming.Common.UserApplicationException;
import com.example.realtimestreaming.Domain.Stream;
import com.example.realtimestreaming.Domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreamRepository extends JpaRepository<Stream, Long> {
    List<Stream> findByStreamId(Long streamId);


}