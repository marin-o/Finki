using Domain.Domain_Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain.DTO
{
    public class HealthExaminationDTO
    {
        public string? Description { get; set; }
        public DateTime? DateTaken { get; set; }
        public Guid? EmployeeId { get; set; }
        public Guid? PolyclinicId { get; set; }
    }
}
