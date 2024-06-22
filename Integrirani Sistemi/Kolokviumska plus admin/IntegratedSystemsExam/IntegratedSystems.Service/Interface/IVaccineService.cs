using IntegratedSystems.Domain.Domain_Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IntegratedSystems.Service.Interface
{
    public interface IVaccineService
    {
        List<Vaccine> GetAllVaccines();
        Vaccine GetDetailsForVaccine(Guid? id);
        void CreateNewVaccine(Vaccine p);
        void UpdateExistingVaccine(Vaccine p);
        void DeleteVaccine(Guid id);
    }
}
