package com.tastsong.crazycar.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class RespDataStatistics {
    public int count;
    public long timestamp;
}
