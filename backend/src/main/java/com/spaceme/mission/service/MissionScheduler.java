package com.spaceme.mission.service;

import com.spaceme.mission.repository.MissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.spaceme.mission.domain.Status.FAILED;
import static com.spaceme.mission.domain.Status.ON_PROGRESS;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional
public class MissionScheduler {

    private final MissionRepository missionRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkMissionStatusDaily() {
        LocalDate today = LocalDate.now();

        missionRepository.findAllByDate(today)
                .forEach(mission -> mission.setStatus(ON_PROGRESS));
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkMissionStatusYesterday() {
        LocalDate yesterday = LocalDate.now().minusDays(1);

        missionRepository.findAllByMissionStatusAndDate(ON_PROGRESS, yesterday)
                .forEach(mission -> mission.setStatus(FAILED));
    }
}
