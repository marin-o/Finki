using Domain.Domain_Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Service.Interface
{
    public interface IHealthExaminationService
    {
        List<HealthExamination> GetAllHealthExaminations();
        HealthExamination GetDetailsForHealthExamination(Guid? id);
        void CreateNewHealthExamination(HealthExamination p);
        void UpdateExistingHealthExamination(HealthExamination p);
        void DeleteHealthExamination(Guid id);
    }
}
