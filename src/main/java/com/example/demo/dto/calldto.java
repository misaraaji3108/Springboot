package com.example.demo.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class calldto {
    long callid;
    private String caller;
    private String Address;
}
