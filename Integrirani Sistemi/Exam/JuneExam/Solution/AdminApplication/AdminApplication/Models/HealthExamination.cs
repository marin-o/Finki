﻿using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AdminApplication.Models
{
    public class HealthExamination
    {
        [Key]
        public Guid Id { get; set; }
        public string? Description { get; set; }
        public DateTime DateTaken { get; set; }
        public Guid EmployeeId { get; set; }
        public virtual Employee? Employee { get; set; }
        public Guid PolyclinicId { get; set; }
        public virtual Polyclinic? Polyclinic { get; set; }

    }
}
