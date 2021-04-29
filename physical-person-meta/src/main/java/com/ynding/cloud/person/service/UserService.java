package com.ynding.cloud.person.service;

import com.ynding.cloud.person.data.UserRepository;
import com.ynding.cloud.person.entity.User;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dyn
 * @version 2020/1/20
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;

    public UserService(UserRepository userRepository, MongoTemplate mongoTemplate) {
        this.userRepository = userRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public List<User> findList(com.ynding.cloud.common.model.bo.Query query) {
        List<User> users = mongoTemplate.find(condition(query),User.class);
        return users;
    }

    /**
     * 查询条件
     * @param gquery
     * @return
     */
    private Query condition(com.ynding.cloud.common.model.bo.Query gquery) {
        Query query = new Query();
        if (gquery.get("age") != null){
            query.addCriteria(Criteria.where("age").is(gquery.get("age")));
        }
        if (gquery.get("username") != null) {
            query.addCriteria(Criteria.where("username").regex(".*?" + gquery.get("username") + ".*"));
        }
        return query;
    }
}
