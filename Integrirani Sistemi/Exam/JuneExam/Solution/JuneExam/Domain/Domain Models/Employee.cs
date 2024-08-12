using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain.Domain_Models
{
    public class Employee : BaseEntity
    {
        public string? FullName { get; set; }
        public DateTime? DateOfBirth { get; set; }
        public string? Title { get; set; }
        public Guid CompanyId { get; set; }
        public virtual Company? Company { get; set; }
        public virtual ICollection<HealthExamination>? HealthExaminations { get; set; }
    }
}
