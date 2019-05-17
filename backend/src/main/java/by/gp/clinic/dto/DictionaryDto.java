package by.gp.clinic.dto;

import by.gp.clinic.enumerated.Gender;
import by.gp.clinic.enumerated.ShiftOrder;
import lombok.Data;

import java.util.List;

@Data
public class DictionaryDto {

    private List<DictionaryEntryDto<Gender>> genders;
    private List<DictionaryEntryDto<ShiftOrder>> shiftOrders;
    private List<SpecialityDto> specialities;
}
