package by.gp.clinic.controller;

import by.gp.clinic.dto.DictionaryDto;
import by.gp.clinic.facade.DictionaryFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dictionary")
@Tag(name = "Dictionary",
    description = "Dictionary for frontend")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryFacade dictionaryFacade;

    @GetMapping
    public DictionaryDto getDictionaryData() {
        return dictionaryFacade.buildDictionaryData();
    }
}
