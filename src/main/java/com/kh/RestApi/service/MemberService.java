package com.kh.RestApi.service;

import com.kh.RestApi.dao.MemberRepository;
import com.kh.RestApi.entity.Member;
import com.kh.RestApi.vo.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MemberService {
    private MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public List<MemberDTO> getMemberList() {
        List<MemberDTO> memberDTOS = new ArrayList<>();
        List<Member> memberList = memberRepository.findAll();
        for (Member e : memberList) {
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setUser(e.getUserId());
            memberDTO.setPwd(e.getPwd());
            memberDTO.setName(e.getName());
            memberDTO.setEmail(e.getEmail());
            memberDTO.setGrade("VIP");
            memberDTOS.add(memberDTO);
        }
        return memberDTOS;
    }

    public List<MemberDTO> getMemberList(String user) {
        List<MemberDTO> memberDTOS = new ArrayList<>();
        List<Member> memberList = memberRepository.findByUserId(user);
        for (Member e : memberList) {
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setUser(e.getUserId());
            memberDTO.setPwd(e.getPwd());
            memberDTO.setName(e.getName());
            memberDTO.setEmail(e.getEmail());
            memberDTO.setGrade("VIP");
            memberDTOS.add(memberDTO);
        }
        return memberDTOS;
    }
    public boolean regMember(String userId, String pwd, String name, String mail) {
        Member member = new Member();
        member.setUserId(userId);
        member.setPwd(pwd);
        member.setName(name);
        member.setEmail(mail);
        member.setRegData(LocalDateTime.now());
        Member rst = memberRepository.save(member);
        log.warn(rst.toString());
        return  true;
    }
    public boolean loginCheck(String user, String pwd) {
        List<Member> memberList = memberRepository.findByUserIdAndPwd(user, pwd);
        for(Member e : memberList) {
            return  true;
        }
        return false;
    }
}
