using IntegratedSystems.Domain.Domain_Models;
using IntegratedSystems.Domain.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IntegratedSystems.Service.Interface
{
    public interface IVaccineService
    {
        List<Vaccine> GetAll();
        Vaccine Get(Guid? id);
        Vaccine Insert(Vaccine vaccine);
        List<Vaccine> InsertMany(List<Vaccine> vaccines);
        Vaccine Update(Vaccine vaccine);
        Vaccine Delete(Vaccine vaccine);
        public List<Vaccine> GetVaccinesCenter(Guid centerId);
        Vaccine AddVaccineToVaccinationCenter(VaccineDTO dto);

    }
}
