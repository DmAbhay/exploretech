package in.exploretech.logasup.repository;

import in.exploretech.logasup.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, String> {
}
