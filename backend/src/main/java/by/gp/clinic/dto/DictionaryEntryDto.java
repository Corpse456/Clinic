package by.gp.clinic.dto;

import lombok.Data;

@Data
public class DictionaryEntryDto<EnumType extends Enum<EnumType>> {

    private final Enum<EnumType> value;
    private final String label;
}
