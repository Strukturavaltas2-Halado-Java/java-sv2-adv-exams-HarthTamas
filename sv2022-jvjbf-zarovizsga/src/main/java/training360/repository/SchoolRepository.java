package training360.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import training360.DTOs.SchoolDto;
import training360.model.School;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolRepository  extends JpaRepository<School,Long> {


    @Query("select distinct s from School s where (:city is null or s.address.city = :city)")
    List<School> findByParams(@Param("city") Optional<String> city);

}
