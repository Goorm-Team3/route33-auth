package io.goorm.rout33.route33auth.model.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomResponseDto<T> {
    private String message;
    private T data;
}
