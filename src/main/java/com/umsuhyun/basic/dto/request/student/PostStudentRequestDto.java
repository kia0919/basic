package com.umsuhyun.basic.dto.request.student;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostStudentRequestDto {
    @NotBlank // 공백지정X
    private String name;

    @NotNull // INT타입으로 @NotNull 은 XX
    @Min(0) //최소
    private Integer age;

    @NotBlank
    private String address;
    
    @NotNull
    private Boolean graduation;
}
