package fr.lapausedev.springboot2.user.domain.assembler;

import fr.lapausedev.springboot2.user.domain.model.UserModel;
import fr.lapausedev.springboot2.user.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UserModelAssembler {

	public Stream<UserModel> fromEntities(Iterable<UserEntity> entities) {
		List<UserModel> list = new ArrayList<>();
		entities.forEach(entity -> list.add(fromEntity(entity)));
		return list.stream();
	}

	public UserModel fromEntity(UserEntity entity) {
		UserModel model = new UserModel();
		model.setId(entity.getId());
		model.setFirstName(entity.getFirstName());
		model.setLastName(entity.getLastName());
		return model;
	}

	public UserEntity toEntity(UserModel model) {
		UserEntity entity = new UserEntity();
		entity.setId(model.getId());
		entity.setFirstName(model.getFirstName());
		entity.setLastName(model.getLastName());
		return entity;
	}
}
