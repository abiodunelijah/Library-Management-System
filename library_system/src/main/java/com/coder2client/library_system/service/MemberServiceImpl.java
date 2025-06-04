package com.coder2client.library_system.service;

import com.coder2client.library_system.dto.MemberDTO;
import com.coder2client.library_system.dto.PostalAddressDTO;
import com.coder2client.library_system.entity.Member;
import com.coder2client.library_system.entity.PostalAddress;
import com.coder2client.library_system.mapper.MemberMapper;
import com.coder2client.library_system.mapper.PostalAddressMapper;
import com.coder2client.library_system.repository.MemberRepository;
import com.coder2client.library_system.repository.PostalAddressRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final PostalAddressRepository postalAddressRepository;
    private final MemberRepository memberRepository;
    private final PostalAddressServiceImpl postalAddressService;

    @PersistenceContext
    private final EntityManager entityManager;


    @Override
    @Transactional
    public MemberDTO addMember(MemberDTO memberDTO) {

        /*
            first step: deal postal address
            second step: convert memberDTO to entity
            third step: add the address in the entity
            fourth step: save the entity in the Database
            fifth step: convert the entity back to DTO and return it

         */

        PostalAddress postalAddress = new PostalAddress();

        //first step
        PostalAddressDTO postalAddressDTO = memberDTO.getPostalAddress();

        if (postalAddressDTO !=null) {
            postalAddress = PostalAddressMapper.mapToPostAddressEntity(postalAddressDTO);
            postalAddress = postalAddressRepository.save(postalAddress);
        }

        //second step
        Member member = MemberMapper.mapMemberDTOToEntity(memberDTO);

        //third step
        member.setPostalAddress(postalAddress);

        //fourth step
        Member savedMember = memberRepository.save(member);

        //fifth step
        return MemberMapper.mapMemberEntityToDTO(savedMember);
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(MemberMapper::mapMemberEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MemberDTO getMemberById(Long id) {

        Optional<Member> memberById = memberRepository.findById(id);
        Member member = memberById.get();

        return MemberMapper.mapMemberEntityToDTO(member);
    }

    @Override
    @Transactional
    public MemberDTO updateMember(MemberDTO memberDTO) {
        //find an existing member by id
        Optional<Member> optionalMember = memberRepository.findById(memberDTO.getId());
        Member memberToUpdate = optionalMember.get();

        // do partial update of the member (only non-null field)
        updateMemberEntityFromDTO(memberToUpdate, memberDTO);

        //save the updated member
        Member savedMember = memberRepository.save(memberToUpdate);

        //return the updated (DTO)
        return MemberMapper.mapMemberEntityToDTO(savedMember);
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public List<MemberDTO> findMembersByCriteria(Long id, String firstName, String lastName, String barcodeNumber) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Member.class);
        Root<Member> memberRoot = criteriaQuery.from(Member.class);
        List<Predicate> predicateList = new ArrayList<>();

        if (id != null){
            predicateList.add(criteriaBuilder.equal(memberRoot.get("id"), id));
        }

        if (firstName != null){
            predicateList.add(criteriaBuilder.like(criteriaBuilder.lower(memberRoot.get("firstName")), "%"+ firstName.toLowerCase() + "%"));
        }

        if (lastName != null){
            predicateList.add(criteriaBuilder.like(criteriaBuilder.lower(memberRoot.get("lastName")), "%"+ lastName.toLowerCase() + "%"));
        }

        if (barcodeNumber != null){
            predicateList.add(criteriaBuilder.like(criteriaBuilder.lower(memberRoot.get("barcodeNumber")), "%"+ barcodeNumber.toLowerCase() + "%"));
        }

        criteriaQuery.where(criteriaBuilder.and(predicateList.toArray(new Predicate[0])));
        List<Member> resultList = entityManager.createQuery(criteriaQuery).getResultList();

        return resultList.stream()
                .map(MemberMapper::mapMemberEntityToDTO)
                .collect(Collectors.toList());
    }

    private void updateMemberEntityFromDTO(Member memberToUpdate, MemberDTO memberDTO) {
        if (memberDTO.getFirstName() != null){
            memberToUpdate.setFirstName(memberDTO.getFirstName());
        }
        if (memberDTO.getLastName() != null){
            memberToUpdate.setLastName(memberDTO.getLastName());
        }
        if (memberDTO.getDateOfBirth() != null){
            memberToUpdate.setDateOfBirth(LocalDate.parse(memberDTO.getDateOfBirth()));
        }
        if (memberDTO.getEmail() != null){
            memberToUpdate.setEmail(memberDTO.getEmail());
        }
        if (memberDTO.getPhoneNumber() != null){
            memberToUpdate.setPhoneNumber(memberDTO.getPhoneNumber());
        }

        //updating the address
        if (memberDTO.getPostalAddress() != null){
            //if the member already has an address update
            //otherwise create a new PostalAddress entity
            PostalAddress postalAddressToUpdate;
            if(memberToUpdate.getPostalAddress() != null){
                postalAddressToUpdate= memberToUpdate.getPostalAddress();
            }else{
                postalAddressToUpdate = new PostalAddress();
            }
            //update postal address
            postalAddressService.updatePostalAddressEntityFromDTO(postalAddressToUpdate, memberDTO.getPostalAddress());
            //save the updated postal address
            postalAddressRepository.save(postalAddressToUpdate);
            //associate the updated postal address to member
            memberToUpdate.setPostalAddress(postalAddressToUpdate);
        }


        if (memberDTO.getBarcodeNumber() != null){
            memberToUpdate.setBarcodeNumber(memberDTO.getBarcodeNumber());
        }
        if (memberDTO.getMembershipStarted() != null){
            memberToUpdate.setMembershipStarted(LocalDate.parse(memberDTO.getMembershipStarted()));
        }

        // member is active if membershipEnded = null
        if (memberDTO.getMembershipEnded() != null){
            if (memberDTO.getMembershipEnded().isEmpty()){
                memberToUpdate.setMembershipEnd(null);
                memberToUpdate.setIsActive(true);
            }else {
                memberToUpdate.setMembershipEnd(LocalDate.parse(memberDTO.getMembershipEnded()));
                memberToUpdate.setIsActive(false);
        }
       
    }
  }
}
