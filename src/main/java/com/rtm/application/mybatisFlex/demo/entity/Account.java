package com.rtm.application.mybatisFlex.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(value = "tb_account",dataSource = "mysql")
public class Account implements Serializable {

    @Id(keyType = KeyType.Auto)
    private Long id;
    private String userName;
    private Integer age;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonProperty("birthday")
    private Date birthday;

}