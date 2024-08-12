using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AdminApplication.Models
{
    public class Polyclinic
    {
        [Key]
        public Guid Id { get; set; }
        public string? PolyclinicName { get; set; }
        public string? PolyclinicAddress { get; set; }
        public int? Reputation { get; set; }
        public int AvailableSlots { get; set; }
        public virtual ICollection<HealthExamination> HealthExaminations { get; set; }

    }
}
