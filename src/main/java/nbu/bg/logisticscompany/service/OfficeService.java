package nbu.bg.logisticscompany.service;

import nbu.bg.logisticscompany.model.dto.OfficeDto;

import java.util.List;

/**
 * The interface Office service.
 */
public interface OfficeService {

    /**
     * Gets all offices.
     *
     * @return the all offices
     */
    List<OfficeDto> getAllOffices();

    /**
     * Gets offices by company id.
     *
     * @param companyId the company id
     * @return the offices by company id
     */
    List<OfficeDto> getOfficesByCompanyId(Long companyId);

    /**
     * Add office.
     *
     * @param office the office
     */
    void addOffice(OfficeDto office);

    /**
     * Gets office by id.
     *
     * @param id the id
     * @return the office by id
     */
    OfficeDto getOfficeById(Long id);

    /**
     * Update office.
     *
     * @param id           the id
     * @param updateOffice the update office
     */
    void updateOffice(Long id, OfficeDto updateOffice);

    /**
     * Delete office.
     *
     * @param id the id
     */
    void deleteOffice(Long id);
}
