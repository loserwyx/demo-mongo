package com.shenma.demomongo.dao;

import com.shenma.demomongo.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserDao extends MongoRepository<User, Long> {
    /*
     * MongoRepository与HibernateTemplete相似，提供一些基本的方法，
     * 实现的方法有findone(),save(),count(),findAll(),findAll(Pageable),delete(),deleteAll()..etc
     * 要使用Repository的功能，先继承MongoRepository<T, TD>接口
     * 其中T为仓库保存的bean类，TD为该bean的唯一标识的类型，一般为ObjectId。
     * 之后在spring-boot中注入该接口就可以使用，无需实现里面的方法，spring会根据定义的规则自动生成。
     * starter-data-mongodb 支持方法命名约定查询 findBy{User的name属性名}，
     * findBy后面的属性名一定要在User类中存在，否则会报错
     */

    /**
     * 根据name查询User实体集合(name一定是在实体类中包含的属性)
     *
     * @param name
     * @return
     */
    List<User> findByName(String name);

    /**
     * 使用分页查询的方式查询大于age的数据
     *
     * @param age
     * @param page
     * @return
     */
    Page<User> findByAgeGreaterThan(int age, Pageable page);

}
