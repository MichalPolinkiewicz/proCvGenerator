package pl.proCvGenerator.converter;

import pl.proCvGenerator.dao.PersonalInfo;
import pl.proCvGenerator.model.PersonalInfoDto;

public class PersonalInfoConverter {

    public PersonalInfoDto convertToDto(PersonalInfo personalInfoDto) {
        return PersonalInfoDto.builder()
                .name(personalInfoDto.getName())
                .surname(personalInfoDto.getSurname())
                .position(personalInfoDto.getPosition())
                .email(personalInfoDto.getEmail())
                .page(personalInfoDto.getPage())
                .phone(personalInfoDto.getPhone())
                .city(personalInfoDto.getCity())
                .description(personalInfoDto.getDescription())
                .build();
    }

    public PersonalInfo convertToPersonalInfo(PersonalInfoDto personalInfoDto) {
        return PersonalInfo.builder()
                .name(personalInfoDto.getName())
                .surname(personalInfoDto.getSurname())
                .position(personalInfoDto.getPosition())
                .email(personalInfoDto.getEmail())
                .page(personalInfoDto.getPage())
                .phone(personalInfoDto.getPhone())
                .city(personalInfoDto.getCity())
                .description(personalInfoDto.getDescription())
                .build();
    }
}
