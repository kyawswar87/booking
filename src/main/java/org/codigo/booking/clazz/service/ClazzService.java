package org.codigo.booking.clazz.service;

import org.codigo.booking.clazz.model.Clazz;
import org.codigo.booking.clazz.repository.ClazzRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.CancellationException;

@Service
public class ClazzService {

    @Value("${cancellation.valid.hours:4}")
    private int cancellationValidHours;

    private final ClazzRepository clazzRepository;

    public ClazzService(ClazzRepository clazzRepository) {
        this.clazzRepository = clazzRepository;
    }

    public boolean isCancellable(Long id) {
        var clazz = findById(id);
        return LocalDateTime.now().plusHours(cancellationValidHours).isBefore(clazz.getStartTime());
    }

    public Clazz findById(Long id) {
        return clazzRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id not found"));
    }

    public Clazz addMembers(Long classId, int noOfBookings) {
        var clazz = findById(classId);
        if(clazz.getMaxOfBookings() < noOfBookings+clazz.getNoOfBookings()) {
            throw new IllegalArgumentException("Bookings are already filled");
        }
        clazz.setNoOfBookings(clazz.getNoOfBookings() + noOfBookings);
        return clazzRepository.save(clazz);
    }

    public Clazz removeMembers(Long classId, int noOfBookings) {
        var clazz = findById(classId);
        if(clazz.getNoOfBookings() - noOfBookings < 0) {
            throw new IllegalArgumentException("Cannot remove becuase there no members for this class");
        }
        clazz.setNoOfBookings(clazz.getNoOfBookings() - noOfBookings);
        return clazzRepository.save(clazz);
    }

    public List<Clazz> findAll() {
        return clazzRepository.findAll();
    }

    public Clazz save(Clazz clazz) {
        clazz.setId(null);
        return clazzRepository.save(clazz);
    }

    public void delete(Clazz clazz) {
        clazzRepository.delete(clazz);
    }

    public Clazz update(Clazz clazz) {
        return clazzRepository.save(clazz);
    }
}
