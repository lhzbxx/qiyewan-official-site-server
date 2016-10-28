package com.qiyewan.repository;

import com.qiyewan.domain.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lhzbxx on 2016/10/19.
 *
 * 用户-身份认证
 */

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {

    UserAuth findFirstByIdentifierAndCredential(String identifier, String credential);

    UserAuth findFirstByIdentifier(String identifier);

    UserAuth findByUserId(Long userId);

}
