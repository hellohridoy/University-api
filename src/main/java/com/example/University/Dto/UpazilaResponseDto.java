package com.example.University.Dto;

import lombok.*;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UpazilaResponseDto {
   public String upazilaName;
   private String zilaName;
   private String divisionName;
}
