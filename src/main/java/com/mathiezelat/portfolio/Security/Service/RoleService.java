package com.mathiezelat.portfolio.Security.Service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathiezelat.portfolio.Security.Entity.Role;
import com.mathiezelat.portfolio.Security.Enums.RoleName;
import com.mathiezelat.portfolio.Security.Repository.IRoleRepository;

@Service
@Transactional
public class RoleService {
    @Autowired
    IRoleRepository iRoleRepository;

    public Optional<Role> getByRoleName(RoleName roleName) {
        return iRoleRepository.findByRoleName(roleName);
    }

    public void save(Role role) {
        iRoleRepository.save(role);
    }
}
