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
    @NotNull    // null이 아니어야 한다.
    private Integer studentNumber;  //학생 번호를 나타내는 필드이므로 null이 아니어야 하므로, @NotNull 지정.
    @NotBlank   //밑의 해당 필드의 값이 null이 아니고, 빈 문자열이 아닌지를 검사하기 위함.
    private String address; //주소를 나타내는 것이므로, null 과 빈 문자열이면 안되므로 @NotBlank로 검사를 한다.
}
