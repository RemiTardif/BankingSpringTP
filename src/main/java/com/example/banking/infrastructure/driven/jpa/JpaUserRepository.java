package com.example.banking.infrastructure.driven.jpa;

import com.example.banking.domain.model.User;
import com.example.banking.domain.port.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class JpaUserRepository implements UserRepository {

    private final JpaUserSpringRepository jpaUserSpringRepository;

    public JpaUserRepository(JpaUserSpringRepository jpaUserSpringRepository) {
        this.jpaUserSpringRepository = jpaUserSpringRepository;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return jpaUserSpringRepository.findByLogin(login)
                .map(this::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity entity = toEntity(user);
        UserEntity savedEntity = jpaUserSpringRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public boolean existsByLogin(String login) {
        return jpaUserSpringRepository.existsByLogin(login);
    }

    @Override
    public boolean existsByClientId(String clientId) {
        return jpaUserSpringRepository.existsByClientId(clientId);
    }

    //Convertit une UserEntity (JPA) en User (domain)
    private User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getLogin(),
                entity.getPassword(),
                entity.getClientId()
        );
    }

    //Convertit un User (domain) en UserEntity (JPA)
    private UserEntity toEntity(User user) {
        return new UserEntity(
                user.id(),
                user.login(),
                user.password(),
                user.clientId()
        );
    }
}