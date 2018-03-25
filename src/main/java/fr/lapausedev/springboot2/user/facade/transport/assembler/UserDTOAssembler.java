package fr.lapausedev.springboot2.user.facade.transport.assembler;

import fr.lapausedev.springboot2.user.domain.model.UserModel;
import fr.lapausedev.springboot2.user.facade.transport.object.UserDTO;
import fr.lapausedev.springboot2.user.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDTOAssembler {

	public UserDTO fromModel(UserModel model) {
		UserDTO dto = new UserDTO();
		dto.setId(model.getId());
		dto.setFirstName(model.getFirstName());
		dto.setLastName(model.getLastName());
		return dto;
	}

	public UserModel toModel(UserDTO dto) {
		UserModel model = new UserModel();
		model.setId(dto.getId());
		model.setFirstName(dto.getFirstName());
		model.setLastName(dto.getLastName());
		return model;
	}
}
