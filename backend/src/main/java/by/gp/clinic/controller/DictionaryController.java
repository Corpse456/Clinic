package by.gp.clinic.controller;

import by.gp.clinic.dto.DictionaryDto;
import by.gp.clinic.service.DictionaryService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dictionary")
@Api(tags = "Dictionary",
    value = "Dictionary for frontend")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @GetMapping
    public DictionaryDto getDictionaryData() {
        return dictionaryService.buildDictionaryData();
    }
}
