package com.liuyanzhao.cloud.repository;
import com.liuyanzhao.cloud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 言曌
 * @date 2018/7/16 10:30
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
