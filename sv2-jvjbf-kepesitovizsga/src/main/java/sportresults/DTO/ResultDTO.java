package sportresults.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sportresults.model.SportType;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO {
    private Long id;
    private String place;
    private LocalDate resultDate;
    private String sportType;
    private double measure;
    private char measureUnit;
}
