using Domain.Domain_Models;
using Domain.Identity_Models;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Reflection.PortableExecutable;

namespace Repository
{
    public class ApplicationDbContext : IdentityDbContext<IntegratedSystemsUser>
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }

        public virtual DbSet<Company> Companies { get; set; }
        public virtual DbSet<Employee> Employees { get; set; }
        public virtual DbSet<HealthExamination> HealthExaminations { get; set; }
        public virtual DbSet<Polyclinic> Polyclinics { get; set; }
    }
}
