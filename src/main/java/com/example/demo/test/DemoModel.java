package com.example.demo.test;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;

@Builder
@RequiredArgsConstructor
public class DemoModel {
    @NotNull
    private String Id;
}
