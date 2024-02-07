package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.model.dto.StaffDto;

public interface AdminService {
    void updateEmployeeRole(StaffDto staffDto) throws Exception;

    void deleteEmployee(long id);
}
