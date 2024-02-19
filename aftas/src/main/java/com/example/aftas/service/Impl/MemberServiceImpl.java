package com.example.aftas.service.Impl;

import com.example.aftas.DTO.MemberDTO;
import com.example.aftas.model.Member;
import com.example.aftas.repository.MemberRepository;
import com.example.aftas.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public MemberServiceImpl(MemberRepository memberRepository,
                             ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MemberDTO> findAll(Pageable pageable){

        List<Member> members = memberRepository.findAll(pageable).stream().toList();

        return members
                .stream()
                .map((element) -> modelMapper.map(element, MemberDTO.class))
                .toList();
    }
    @Override
    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public List<Member> searchMember(String name, Pageable pageable) {
        return memberRepository.findByName(name, pageable);
    }

    @Override
    public Member updateMember(Member member, Long id) {
        Member existingMember = getMemberById(id);

        existingMember.setName(member.getName());
        existingMember.setFamilyName(member.getFamilyName());
        existingMember.setAccessDate(member.getAccessDate());
        existingMember.setNationality(member.getNationality());
        existingMember.setIdentityDocumentType(member.getIdentityDocumentType());
        existingMember.setIdentityNumber(member.getIdentityNumber());
        return memberRepository.save(existingMember);
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
