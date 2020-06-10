package fr.lapausedev.spring.user.facade.transport.assembler;

import fr.lapausedev.spring.user.facade.transport.object.UserDTO;
import fr.lapausedev.spring.user.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDTOAssembler {

	public UserDTO fromEntity(UserEntity entity) {
		UserDTO dto = new UserDTO();
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		return dto;
	}

	public UserEntity toEntity(UserDTO dto) {
		UserEntity entity = new UserEntity();
		entity.setId(dto.getId());
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		return entity;
	}
}
