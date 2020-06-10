package fr.lapausedev.spring.user.persistence.repository;

import fr.lapausedev.spring.user.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<UserEntity, Integer> {

}
