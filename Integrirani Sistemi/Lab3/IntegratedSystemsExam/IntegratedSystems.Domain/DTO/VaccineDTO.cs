using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IntegratedSystems.Domain.Domain_Models;

namespace IntegratedSystems.Domain.DTO
{
    public class VaccineDTO
    {
        public List<Patient>? patients { get; set; }

        public List<string>? manufacturers { get; set; }

        public Guid patientId { get; set; }

        public DateTime vaccinationDate { get; set; }

        public Guid vaccCenterId { get; set; }

        public string? manufacturer { get; set; }
    }
}
