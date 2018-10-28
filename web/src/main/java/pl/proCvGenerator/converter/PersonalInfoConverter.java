package pl.proCvGenerator.converter;

import pl.proCvGenerator.dto.PersonalInfo;
import pl.proCvGenerator.model.PersonalInfoDto;

public class PersonalInfoConverter {

    public PersonalInfoDto convertToDto(PersonalInfo personalInfo) {
        return new PersonalInfoDto(personalInfo.getName(), personalInfo.getSurname(),
                personalInfo.getEmail(), personalInfo.getPhone(), personalInfo.getCity(),
                personalInfo.getDescription());
    }

    public PersonalInfo convertToPersonalInfo(PersonalInfoDto personalInfoDto) {
        return new PersonalInfo(personalInfoDto.getName(), personalInfoDto.getSurname(), personalInfoDto.getEmail(),
                personalInfoDto.getPhone(), personalInfoDto.getCity(), personalInfoDto.getDescription());
    }
}
