using IntegratedSystems.Domain.Domain_Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IntegratedSystems.Repository.Interface
{
    public interface IVaccinationCenterRepository
    {
        List<VaccinationCenter> GetAllCenters();
        VaccinationCenter GetCenter(BaseEntity entity);
    }
}
