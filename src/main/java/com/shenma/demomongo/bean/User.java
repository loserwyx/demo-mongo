package com.shenma.demomongo.bean;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

//@Document(collection = "collection_user")
@Document(collection = "user")
@Getter
@Setter
@AllArgsConstructor
public class User implements Serializable {

    @Id
    private Long id;
    private String name;
    private int age;
    private String sex;

}
