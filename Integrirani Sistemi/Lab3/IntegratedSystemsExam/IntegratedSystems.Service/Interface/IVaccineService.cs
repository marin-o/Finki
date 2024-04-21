using IntegratedSystems.Domain.Domain_Models;
using IntegratedSystems.Domain.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IntegratedSystems.Service.Interface {
    public interface IVaccineService {
        void CreateNew(VaccineDTO dto);
        List<Vaccine> GetVaccinesForPatient(Guid? id);
        List<Vaccine> GetVaccinesForCenter(Guid? id);
    }
}
