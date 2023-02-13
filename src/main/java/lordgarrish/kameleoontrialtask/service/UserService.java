package lordgarrish.kameleoontrialtask.service;

import lordgarrish.kameleoontrialtask.entity.User;
import lordgarrish.kameleoontrialtask.exception.UserAlreadyExistException;
import lordgarrish.kameleoontrialtask.dto.UserDTO;
import lordgarrish.kameleoontrialtask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserDTO registerUser(User user) throws UserAlreadyExistException {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("User already exists");
        }
        return UserDTO.toDto(userRepository.save(user));
    }
}
