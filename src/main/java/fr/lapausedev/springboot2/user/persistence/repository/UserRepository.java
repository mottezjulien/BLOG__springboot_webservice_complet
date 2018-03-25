package fr.lapausedev.springboot2.user.persistence.repository;

import fr.lapausedev.springboot2.user.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<UserEntity, Integer> {

}
