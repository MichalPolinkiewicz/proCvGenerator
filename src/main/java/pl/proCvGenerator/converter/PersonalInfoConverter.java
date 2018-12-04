package pl.proCvGenerator.converter;

import pl.proCvGenerator.dto.PersonalInfo;
import pl.proCvGenerator.model.PersonalInfoDto;

public class PersonalInfoConverter {

    public PersonalInfoDto convertToDto(PersonalInfo personalInfo) {
        return new PersonalInfoDto(personalInfo.getName(), personalInfo.getSurname(), personalInfo.getPosition(),
                personalInfo.getEmail(), personalInfo.getPage(), personalInfo.getPhone(), personalInfo.getCity(),
                personalInfo.getDescription());
    }

    public PersonalInfo convertToPersonalInfo(PersonalInfoDto personalInfoDto) {
        return new PersonalInfo(personalInfoDto.getName(), personalInfoDto.getSurname(), personalInfoDto.getPosition(), personalInfoDto.getEmail(),
                personalInfoDto.getPage(), personalInfoDto.getPhone(), personalInfoDto.getCity(), personalInfoDto.getDescription());
    }
}
