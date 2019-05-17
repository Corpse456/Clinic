package by.gp.clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryEntryDto<EnumType extends Enum<EnumType>> {

    private Enum<EnumType> value;
    private String label;
}
