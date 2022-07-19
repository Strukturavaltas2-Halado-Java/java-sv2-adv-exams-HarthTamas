package sportresults.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sportresults.model.Result;
import sportresults.model.Sex;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AthleteDto {

    private Long id;
    private String name;
    private Sex sex;
    private List<ResultDTO> results;

}
