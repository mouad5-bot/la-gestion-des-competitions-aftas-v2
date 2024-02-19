package com.example.aftas.service.Impl;

import com.example.aftas.model.Competition;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.service.CompetitionService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompetitionServiceImplTest {

    private CompetitionService competitionService;

    @Mock
    private CompetitionRepository competitionRepository;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        competitionService = new CompetitionServiceImpl(competitionRepository);
    }

    @Test
    void add_CompetitionOnSameDay() {
        LocalDate currentDate = LocalDate.now();
        Competition competition = new Competition();
        competition.setDate(currentDate);
        competition.setLocation("Location");

        when(competitionRepository.findByDate(currentDate)).thenReturn(Optional.of(competition));

        assertThrows(IllegalArgumentException.class, () -> competitionService.add(competition));
        verify(competitionRepository, never()).save(competition);
    }

    @Test
    void add_CompetitionInPast() {
        LocalDate pastDate = LocalDate.now().minusDays(1);
        Competition competition = new Competition();
        competition.setDate(pastDate);
        competition.setLocation("Location");

        assertThrows(IllegalArgumentException.class, () -> competitionService.add(competition));
        verify(competitionRepository, never()).save(competition);
    }

    @Test
    void add_CompetitionBeforeTwoDays() {
        LocalDate currentDate = LocalDate.now().plusDays(1);
        Competition competition = new Competition();
        competition.setDate(currentDate);
        competition.setLocation("Location");

        assertThrows(IllegalArgumentException.class, () -> competitionService.add(competition));
        verify(competitionRepository, never()).save(competition);
    }

    @AfterAll
    static void afterAllMsg(){
        System.out.println(" ------------------------------------------------------------------------------");
        System.out.println(" ----------- All the tests of competition are passed successfully -------------");
        System.out.println(" ------------------------------------------------------------------------------");
    }

}