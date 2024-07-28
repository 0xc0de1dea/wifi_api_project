package com.ljh.dto;

import lombok.Data;

import java.util.List;

@Data
public class WifiDTO {
    private int list_total_count;
    private ResultDTO RESULT;
    private List<RowDTO> row;
}
