using IntegratedSystems.Domain.Domain_Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IntegratedSystems.Service.Interface
{
    public interface IVaccinationCenterService
    {
        List<VaccinationCenter> GetAllCenters();
        VaccinationCenter GetDetailsForCenter(Guid? id);
        void CreateNewCenter(VaccinationCenter p);
        void UpdateExistingCenter(VaccinationCenter p);
        void DeleteCenter(Guid id);
    }
}
