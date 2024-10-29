package by.gp.clinic.facade;

import by.gp.clinic.dto.DictionaryDto;
import by.gp.clinic.dto.DictionaryEntryDto;
import by.gp.clinic.enumerated.Gender;
import by.gp.clinic.enumerated.ShiftOrder;
import by.gp.clinic.service.MessagesService;
import by.gp.clinic.service.SpecialityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class DictionaryFacade {

    private final SpecialityService specialityService;
    private final MessagesService messagesService;

    public DictionaryDto buildDictionaryData() {
        final var dictionary = new DictionaryDto();
        dictionary.setGenders(buildSimpleDictionaries(Gender.class));
        dictionary.setShiftOrders(buildSimpleDictionaries(ShiftOrder.class));
        dictionary.setSpecialities(specialityService.findAll());
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
        final var localizedValue = messagesService.get(enumElement);
        return new DictionaryEntryDto<>(enumElement, localizedValue);
    }
}
