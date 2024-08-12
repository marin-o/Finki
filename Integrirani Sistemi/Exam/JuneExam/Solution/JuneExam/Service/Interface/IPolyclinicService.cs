using Domain.Domain_Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Service.Interface
{
    public interface IPolyclinicService
    {
        List<Polyclinic> GetAllPolyclinics();
        Polyclinic GetDetailsForPolyclinic(Guid? id);
        void CreateNewPolyclinic(Polyclinic p);
        void UpdateExistingPolyclinic(Polyclinic p);
        void DeletePolyclinic(Guid id);
    }
}
