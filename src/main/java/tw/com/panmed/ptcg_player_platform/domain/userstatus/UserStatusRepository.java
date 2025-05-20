package tw.com.panmed.ptcg_player_platform.domain.userstatus;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserStatusRepository
                extends MongoRepository<UserStatus, String>, QuerydslPredicateExecutor<UserStatus> {
}
