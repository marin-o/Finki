using IntegratedSystems.Domain.Domain_Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IntegratedSystems.Service.Interface
{
    public interface IPatientService
    {
        List<Patient> GetAll();
        Patient Get(Guid? id);
        Patient Insert(Patient patient);
        List<Patient> InsertMany(List<Patient> patients);
        Patient Update(Patient patient);
        Patient Delete(Patient patient);
    }
}
