package com.example.aftas.service.Impl;

import com.example.aftas.DTO.HuntingDTORequest;
import com.example.aftas.DTO.SaveHuntDTO;
import com.example.aftas.model.*;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.repository.FishRepository;
import com.example.aftas.repository.HuntingRepository;
import com.example.aftas.repository.MemberRepository;
import com.example.aftas.service.HuntingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HuntingServiceImpl implements HuntingService {

    private final HuntingRepository huntingRepository;
    private final CompetitionRepository competitionRepository;
    private final ModelMapper mapper;
    private final MemberRepository memberRepository;
    private final FishRepository fishRepository;

    @Override
    public SaveHuntDTO add(SaveHuntDTO hunting) throws Exception {

        Competition competition = competitionRepository.findByCode(hunting.getCompetitionCode()).orElseThrow();
        Member member = memberRepository.findById(hunting.getMemberNum()).orElseThrow();
        Fish fish = fishRepository.findById(hunting.getFishId()).orElseThrow();

        final int[] v = {0} ;

        competition.getRankingList().forEach(ranking -> {
             if (Objects.equals(ranking.getMember().getNum(), member.getNum())){
                 v[0] = 1;
             }
        });

        if(v[0] == 0){
            throw new IllegalArgumentException("the member is not in the competition");
        }

       if ( hunting.getFishWeight() < fish.getAverageWeight()){
           throw new IllegalArgumentException("the weight of this fish is not valid");
       }

        Hunting newHunting = huntingRepository.findByMemberNumAndCompetitionCodeAndFishId(
                member.getNum(),
                competition.getCode(),
                fish.getId()
        ).orElse(null);

       if (newHunting != null){
           newHunting.setNumberOfFish(newHunting.getNumberOfFish() + 1);
           huntingRepository.save(newHunting);
       }else {
           newHunting = Hunting.builder()
                   .member(member)
                   .competition(competition)
                   .numberOfFish(1L)
                   .fish(fish)
                   .build();
           huntingRepository.save(newHunting);
       }

        return SaveHuntDTO.builder()
                .competitionCode(competition.getCode())
                .fishId(fish.getId())
                .memberNum(member.getNum())
                .fishWeight(hunting.getFishWeight())
                .build();
    }

    @Override
    public List<HuntingDTORequest> findAll(Pageable pageable){

        List<Hunting> huntingList = huntingRepository.findAll(pageable).stream().toList();

        return huntingList
                .stream()
                .map(
                        hunting -> {
                            return HuntingDTORequest.builder()
                                    .memberName(hunting.getMember().getName() + " " + hunting.getMember().getFamilyName())
                                    .competitionCode(hunting.getCompetition().getCode())
                                    .numberOfFish(hunting.getNumberOfFish())
                                    .build();
                        }
                )
                .toList();
    }


}
