package by.gp.clinic.dto;

import by.gp.clinic.enums.Speciality;
import lombok.Data;

import java.util.List;

@Data
public class DictionaryDto {

    private List<DictionaryEntryDto<Speciality>> specialities;
}
