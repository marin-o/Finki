using Lab1Guided.Models.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

namespace Lab1Guided.Data
{
    public class ApplicationDbContext : IdentityDbContext<ApplicationUser>
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }
        public DbSet<Lab1Guided.Models.Domain.ConcertPlay> ConcertPlay { get; set; }
        public DbSet<Lab1Guided.Models.Domain.Ticket> Ticket { get; set; }
    }
}
