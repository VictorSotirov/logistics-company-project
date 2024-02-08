package nbu.bg.logisticscompany.service.impl;

import lombok.AllArgsConstructor;
import nbu.bg.logisticscompany.exceptions.InvalidRoleException;
import nbu.bg.logisticscompany.model.dto.StaffDto;
import nbu.bg.logisticscompany.model.entity.Role;
import nbu.bg.logisticscompany.model.entity.Staff;
import nbu.bg.logisticscompany.model.entity.UserRole;
import nbu.bg.logisticscompany.repository.RoleRepository;
import nbu.bg.logisticscompany.repository.StaffRepository;
import nbu.bg.logisticscompany.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * The type Admin service.
 */
@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final StaffRepository staffRepository;
    private final RoleRepository roleRepository;

    @Override
    public void updateEmployeeRole(StaffDto staffDto) throws Exception {
        //checks the role that it's not client
        if (staffDto.getRole() == null || staffDto.getRole().equals(UserRole.CLIENT.toString())) {
            throw new InvalidRoleException("Role must not be client or null");
        }
        UserRole updateRole = UserRole.valueOf(staffDto.getRole());
        //updates the existing staff
        Staff updatedStaff = staffRepository.findById(staffDto.getId()).map(staff -> {
            Role existingRole = roleRepository.findByName(updateRole).orElseThrow();
            HashSet<Role> roles = new HashSet<>();
            roles.add(existingRole);
            staff.setRoles(roles);
            return staff;
        }).orElseThrow();

        staffRepository.save(updatedStaff);
    }

    @Override
    public void deleteEmployee(long id) {
        //finds the existing staff by id
        Staff existingStaff = staffRepository.findById(id).orElseThrow();

        staffRepository.delete(existingStaff);
    }

}
