package com.thisishkf.springcucumber.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SetLuckNumberRequest {
    @NotNull
    private Integer number;
}
