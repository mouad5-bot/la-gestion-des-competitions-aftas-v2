package com.example.aftas.service;

import com.example.aftas.DTO.MemberDTO;
import com.example.aftas.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {
    Member getMemberById(Long id);
    Member addMember(Member member);
    List<Member> searchMember(String name, Pageable pageable);
    Member updateMember(Member member, Long id);
    void deleteMember(Long id);
    List<MemberDTO> findAll(Pageable pageable);
}
