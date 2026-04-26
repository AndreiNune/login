package com.fateczl.repository;

import com.fateczl.repository.adapter.UserRepositoryImplAdapter;
import com.fateczl.repository.client.UserRepositoryWithMongodb;
import com.fateczl.entity.User;
import com.fateczl.repository.orm.UserOrm;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final PasswordEncoder encoder;
    private final UserRepositoryWithMongodb repository;

    public UserRepositoryImpl(
            PasswordEncoder encoder,
            UserRepositoryWithMongodb repository
    ){
        this.encoder = encoder;
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        try {
            UserOrm orm = repository.save(UserRepositoryImplAdapter.cast(user));
            return UserRepositoryImplAdapter.cast(orm, encoder);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User findByUsername(String username){
        try {
            Optional<UserOrm> optional = repository.findByUsername(username);
            if (optional.isEmpty()){
                throw new UsernameNotFoundException("Usuário não encontrado");
            }
            return UserRepositoryImplAdapter.cast(optional.get(), encoder);

        } catch (UsernameNotFoundException e){
            throw e;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
