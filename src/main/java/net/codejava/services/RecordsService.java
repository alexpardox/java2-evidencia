package net.codejava.services;

import net.codejava.entity.Records;
import net.codejava.repositories.RecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordsService {

    @Autowired
    private RecordsRepository recordsRepository;

    public Records save(Records record) {
        return recordsRepository.save(record);
    }

    public List<Records> findByUserIdOrderByMeasuredAtDesc(Integer userId) {
        return recordsRepository.findByUserIdOrderByMeasuredAtDesc(userId);
    }

    public Records findLatestByUserId(Integer userId) {
        return recordsRepository.findTopByUserIdOrderByMeasuredAtDesc(userId);
    }

    public List<Records> listAll() {
        return recordsRepository.findAll();
    }
}
