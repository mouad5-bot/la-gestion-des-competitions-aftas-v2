package com.example.aftas.service.Impl;

import com.example.aftas.DTO.RankingDTO;
import com.example.aftas.DTO.mapper.RankingMapper;
import com.example.aftas.model.Competition;
import com.example.aftas.model.Member;
import com.example.aftas.model.Ranking;
import com.example.aftas.model.embeddedKey.RankingKey;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.repository.MemberRepository;
import com.example.aftas.repository.RankingRepository;
import com.example.aftas.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;
    private final CompetitionRepository competitionRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper mapper;

    @Override
    public RankingDTO add(RankingDTO rankingDTO) throws Exception {
        checkDateOfParticipation(rankingDTO);
        Competition competition = competitionRepository.findCompetitionByCode(rankingDTO.getCompetition_code());
        Member member = memberRepository.findById(rankingDTO.getMember_id()).orElseThrow(() -> new Exception(" no member with this number "));

        RankingKey rankingKey = RankingKey.builder()
                .code(rankingDTO.getCompetition_code())
                .num(rankingDTO.getMember_id())
                .build();
        Ranking ranking = Ranking.builder()
                .id(rankingKey)
                .rank(0L)
                .score(0L)
                .competition(competition)
                .member(member)
                .build();

        Ranking saved = rankingRepository.save(ranking);
        return RankingDTO.builder()
                .member_id(saved.getMember().getNum())
                .competition_code(saved.getCompetition().getCode())
                .score(saved.getScore())
                .rank(saved.getRank())
                .build();
    }

    private void checkDateOfParticipation(RankingDTO ranking) {

        LocalDate today = LocalDate.now();
        LocalDate competitionDate;

        if (ranking != null) {

            String code = ranking.getCompetition_code();

            Competition competition = competitionRepository.findCompetitionByCode(code);

            if (competition != null) {
                competitionDate = competition.getDate();
            } else {
                throw new IllegalStateException("Competition is null");
            }
        } else {
            throw new IllegalArgumentException("Ranking is null or invalid");
        }

        if (!today.plusDays(1).isBefore(competitionDate)) {
            throw new IllegalArgumentException("Member cannot participate within 24 hours before the competition starts.");
        }
    }


    @Override
    public List<RankingDTO> getAll(Pageable pageable) {
        List<Ranking> rankingList = rankingRepository.findAll();
        return rankingList.
                    stream().
                    map((element) -> RankingMapper.toDto(element))
                    .toList();
    }
}
