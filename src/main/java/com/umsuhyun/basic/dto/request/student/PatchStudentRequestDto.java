package com.umsuhyun.basic.dto.request.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter //데이터를 가져오기 위해.
@Setter
@NoArgsConstructor //초기화하기 위함.
public class PatchStudentRequestDto {
    @NotNull
    private Integer studentNumber;
    @NotBlank
    private String address;
}
