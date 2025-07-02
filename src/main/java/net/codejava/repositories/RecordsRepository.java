package net.codejava.repositories;

import net.codejava.entity.Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordsRepository extends JpaRepository<Records, Long> {
    
    List<Records> findByUserIdOrderByMeasuredAtDesc(Integer userId);
    
    Records findTopByUserIdOrderByMeasuredAtDesc(Integer userId);
}
