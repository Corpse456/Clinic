package by.gp.clinic.service;

import by.gp.clinic.dto.DictionaryDto;
import by.gp.clinic.dto.DictionaryEntryDto;
import by.gp.clinic.enums.Speciality;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final MessagesService messagesService;

    public DictionaryDto buildDictionaryData() {
        final DictionaryDto dictionary = new DictionaryDto();
        dictionary.setSpecialities(buildSimpleDictionaries(Speciality.class));
        return dictionary;
    }

    private <EnumType extends Enum<EnumType>>
    List<DictionaryEntryDto<EnumType>> buildSimpleDictionaries(final Class<EnumType> enumClass) {
        return stream(enumClass.getEnumConstants())
            .map(this::buildSimpleDictionaryEntry)
            .collect(toList());
    }

    private <EnumType extends Enum<EnumType>>
    DictionaryEntryDto<EnumType> buildSimpleDictionaryEntry(final Enum<EnumType> enumElement) {
        final String localizedValue = messagesService.get(enumElement);
        return new DictionaryEntryDto<>(enumElement, localizedValue);
    }
}
