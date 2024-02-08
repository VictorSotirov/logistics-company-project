package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.model.dto.StaffDto;

/**
 * The interface Admin service.
 */
public interface AdminService {
    /**
     * Update employee role.
     *
     * @param staffDto the staff dto
     * @throws Exception the exception
     */
    void updateEmployeeRole(StaffDto staffDto) throws Exception;

    /**
     * Delete employee.
     *
     * @param id the id
     */
    void deleteEmployee(long id);
}
