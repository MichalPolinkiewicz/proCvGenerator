package pl.proCvGenerator.converter;

import lombok.extern.slf4j.Slf4j;
import pl.proCvGenerator.dao.PersonalInfo;
import pl.proCvGenerator.model.PersonalInfoDto;

@Slf4j
public class PersonalInfoConverter {

    public PersonalInfoDto convertToDto(PersonalInfo personalInfo) {
        String methodName = "convertToDto()...";
        log.info(methodName + "Input = {}", personalInfo);

        PersonalInfoDto personalInfoDto = new PersonalInfoDto(
                personalInfo.getName() != null ? personalInfo.getName() : "",
                personalInfo.getSurname() != null ? personalInfo.getSurname() : "",
                personalInfo.getPosition() != null ? personalInfo.getPosition() : "",
                personalInfo.getEmail() != null ? personalInfo.getEmail() : "",
                personalInfo.getPage() != null ? personalInfo.getPage() : "",
                personalInfo.getPhone() != null ? personalInfo.getPhone() : "",
                personalInfo.getCity() != null ? personalInfo.getCity() : "",
                personalInfo.getDescription() != null ? personalInfo.getDescription() : "");

        log.info(methodName + "Output = {}", personalInfoDto);

        return personalInfoDto;
    }

    public PersonalInfo convertToPersonalInfo(PersonalInfoDto personalInfoDto) {
        String methodName = "convertToPersonalInfo()...";
        log.info(methodName + "Input = {}", personalInfoDto);

        PersonalInfo personalInfo = new PersonalInfo(
                personalInfoDto.getName() != null ? personalInfoDto.getName() : "",
                personalInfoDto.getSurname() != null ? personalInfoDto.getSurname() : "",
                personalInfoDto.getPosition() != null ? personalInfoDto.getPosition() : "",
                personalInfoDto.getEmail() != null ? personalInfoDto.getEmail() : "",
                personalInfoDto.getPage() != null ? personalInfoDto.getPage() : "",
                personalInfoDto.getPhone() != null ? personalInfoDto.getPhone() : "",
                personalInfoDto.getCity() != null ? personalInfoDto.getCity() : "",
                personalInfoDto.getDescription() != null ? personalInfoDto.getDescription() : "");

        log.info(methodName + "Output = {}", personalInfo);

        return personalInfo;
    }
}
