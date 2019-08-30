package com.cyh.collector.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReceiveBo {

    private String receiveTime;

    private BigDecimal receiveAmount;

}
