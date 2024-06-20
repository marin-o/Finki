using IntegratedSystems.Domain.Domain_Models;
using IntegratedSystems.Domain.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IntegratedSystems.Service.Interface
{
    public interface IVaccinationCenterService
    {
        List<VaccinationCenter> GetAll();
        VaccinationCenter Get(Guid? id);
        VaccinationCenter Insert(VaccinationCenter center);
        List<VaccinationCenter> InsertMany(List<VaccinationCenter> centers);
        VaccinationCenter Update(VaccinationCenter center);
        VaccinationCenter Delete(VaccinationCenter center);
    }
}
