using IntegratedSystems.Domain.Domain_Models;
using IntegratedSystems.Repository.Interface;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IntegratedSystems.Repository.Implementation
{
    public class VaccinationCenterRepository : IVaccinationCenterRepository
    {
        private DbSet<VaccinationCenter> entities;
        private readonly ApplicationDbContext context;

        public VaccinationCenterRepository(ApplicationDbContext context)
        {
            this.context = context;
            entities = context.Set<VaccinationCenter>();
        }

        public List<VaccinationCenter> GetAllCenters()
        {
            return entities
                .Include(x => x.Vaccines)
                .ToList();
        }

        public VaccinationCenter GetCenter(BaseEntity entity)
        {
            if(entity == null)
            {
                throw new ArgumentNullException("entity");
            }

            return entities
                .Include(x => x.Vaccines)
                .SingleOrDefaultAsync(x => x.Id == entity.Id).Result;
        }
    }
}
