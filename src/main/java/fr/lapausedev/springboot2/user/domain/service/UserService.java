package fr.lapausedev.springboot2.user.domain.service;

import fr.lapausedev.springboot2.user.domain.assembler.UserModelAssembler;
import fr.lapausedev.springboot2.user.domain.model.UserModel;
import fr.lapausedev.springboot2.user.persistence.entity.UserEntity;
import fr.lapausedev.springboot2.user.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserModelAssembler assembler;

    public UserModel save(UserModel model) {
        UserEntity entity = assembler.toEntity(model);
        repository.save(entity);
        return assembler.fromEntity(entity);
    }

    public Stream<UserModel> findAll() {
        return assembler.fromEntities(repository.findAll());
    }

    public Optional<UserModel> findById(int id) {
        return repository.findById(id)
                .map(entity -> assembler.fromEntity(entity));
    }

    public void delete(UserModel model) {
        repository.delete(assembler.toEntity(model));
    }
}
