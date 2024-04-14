package com.lms.domain.member.repository;
import com.lms.domain.member.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    public Optional<Member> findByOauth2Id(String oauth2Id);
    public Optional<Member> findByName(String name);

    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m where m.email = :email")
    Optional<Member> getWithRoles(String email);

    @EntityGraph(attributePaths = "roleSet")
    Member findByEmail(String email);

    @Query("select m from Member m where m.email = :email")
    Optional<Member> findByEmail2(String email);

    boolean existsByEmail(String email);

    @Query("select m from Member m where m.email = :email")
    Member getEmail(@Param("email") String email);

    @Query("select m from Member m where m.name = :name")
    Member getName(@Param("name") String name);


    Integer countByEmail(@Param("email") String email);

    @Query("select m from Member m where m.email = :email")
    Optional<Member> getMember(@Param("email") String email);


}