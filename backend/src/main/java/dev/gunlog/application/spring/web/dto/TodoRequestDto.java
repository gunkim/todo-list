package dev.gunlog.application.spring.web.dto;

public record TodoRequestDto(long id, String text, boolean isCheck) {

}