using Lab1.Web.Models;
using Lab1.Web.Models.Domain;
using Lab1.Web.Models.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

namespace Lab1.Web.Data
{
    public class ApplicationDbContext : IdentityDbContext<TheaterUser>
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }

        public virtual DbSet<Ticket> Tickets { get; set; }
        public virtual DbSet<TheaterShow> TheaterShows { get; set; }
    }
}
