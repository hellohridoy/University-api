package com.example.University.Dto;

import lombok.*;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UpazilaDetailsDto {
   private Long id;
   private String upazilaName;
   private String upazilaCode;
   private String upazilaNumber;

}
