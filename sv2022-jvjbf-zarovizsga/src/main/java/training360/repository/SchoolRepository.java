package training360.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training360.model.School;

@Repository
public interface SchoolRepository  extends JpaRepository<School,Long> {


}
