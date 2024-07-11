package com.rtm.application.mybatisFlex.demo.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Table("tb_account")
public class Account implements Serializable {

    @Id(keyType = KeyType.Auto)
    private Long id;
    private String userName;
    private Integer age;

    private LocalDateTime birthday;

}