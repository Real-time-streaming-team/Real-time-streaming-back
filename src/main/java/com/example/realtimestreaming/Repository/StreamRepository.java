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


//@Repository
//public class StreamRepository {
//
//    @PersistenceContext
//    EntityManager em;
//
//    public void save (Stream stream) {
//        em.persist(stream);
//    }
//
//
//
//    public Stream findStreamByStreamId(Long streamId) {
//        try {
//            Stream stream = em.createQuery("SELECT s FROM Stream s WHERE s.id = :streamId", Stream.class)
//                    .setParameter("streamId", streamId)
//                    .getSingleResult();
//            return stream;
//        } catch (Exception ex) {
//            System.out.println("???????" + ex.getMessage());
//            throw new UserApplicationException(ErrorCode.NO_MATCHING_STREAM_FOUND_WITH_STREAMID);
//        }
//    }
//}