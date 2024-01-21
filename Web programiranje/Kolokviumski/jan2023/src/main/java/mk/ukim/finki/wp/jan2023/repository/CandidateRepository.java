package mk.ukim.finki.wp.jan2023.repository;

import mk.ukim.finki.wp.jan2023.model.Candidate;
import mk.ukim.finki.wp.jan2023.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

//    @Query("SELECT COUNT(s) FROM ShoppingCart s INNER JOIN s.user u WHERE u.username = :username AND s.status = 'FINISHED'")
//    @Query("select u from User u where u.gender=:gender and ")
    @Query("SELECT c FROM Candidate c WHERE c.gender = :gender AND YEAR(CURRENT DATE)-YEAR(c.dateOfBirth)>:yearsMoreThan ")
    List<Candidate> findAllByDateOfBirthGreaterThanAndGenderEquals(Integer yearsMoreThan, Gender gender);
    @Query("SELECT c FROM Candidate c WHERE YEAR(CURRENT DATE)-YEAR(c.dateOfBirth)>:yearsMoreThan ")
    List<Candidate> findAllByDateOfBirthGreaterThan(Integer yearsMoreThan);
//    @Query("SELECT c FROM Candidate c WHERE c.gender = :gender AND YEAR(CURRENT DATE)-YEAR(c.dateOfBirth)>:yearsMoreThan ")
    List<Candidate> findAllByGenderEquals(Gender gender);
}
