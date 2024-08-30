package com.froi.users.user.infrastructure.outputadapters;

import com.froi.users.user.common.PersistenceAdapter;
import com.froi.users.user.domain.User;
import com.froi.users.user.infrastructure.outputports.CreateUserOutputPort;
import com.froi.users.user.infrastructure.outputports.FindUserOutputPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@PersistenceAdapter
public class UserDbOutputAdapter implements CreateUserOutputPort, FindUserOutputPort {

    private UserDbEntityRepository userDbEntityRepository;

    @Autowired
    public UserDbOutputAdapter(UserDbEntityRepository userDbEntityRepository) {
        this.userDbEntityRepository = userDbEntityRepository;
    }

    @Override
    public User create(User user) {
        UserDbEntity userDbEntity = UserDbEntity.fromDomain(user);
        userDbEntity = userDbEntityRepository.save(userDbEntity);
        return userDbEntity.toDomain();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userDbEntityRepository.findById(username)
                .map(UserDbEntity::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDbEntityRepository.findByEmail(email)
                .map(UserDbEntity::toDomain);
    }
}
