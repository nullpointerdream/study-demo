package com.mycrawler.mycrawler.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 被映射类VO1:比实体类少一个字段
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO2 {
    private Integer id;
    private String name;
    private String createTime;

}