package sportresults.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sportresults.DTO.ResultDTO;
import sportresults.DTO.UpdateMeasureCommand;
import sportresults.service.SportResultService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/results")

public class ResultController {

    private SportResultService service;

    @PutMapping("/{id}")
    public ResultDTO updateResult(@PathVariable Long id, @Valid @RequestBody UpdateMeasureCommand command) {
        return service.updateResult(id, command);
    }

    @GetMapping
    public List<ResultDTO> getResults (@RequestParam Optional<String> sportType) {
        return service.getResults(sportType);
    }

}
