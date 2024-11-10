using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain.Domain_Models
{
    public class Polyclinic : BaseEntity
    {
        public string? PolyclinicName { get; set; }
        public string? PolyclinicAddress { get; set; }
        public int? Reputation {  get; set; }
        public int AvailableSlots { get; set; }
        public virtual ICollection<HealthExamination> HealthExaminations { get; set; }

    }
}
