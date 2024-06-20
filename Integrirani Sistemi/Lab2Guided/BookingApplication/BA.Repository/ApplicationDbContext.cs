using BA.Domain.Identity;
using BA.Domain.Models;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BA.Repository
{
    public class ApplicationDbContext : IdentityDbContext<BookingApplicationUser>
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }

        public virtual DbSet<BA.Domain.Models.Apartment> Apartments { get; set; }
        public virtual DbSet<Reservation> Reservations { get; set; }
        public DbSet<BookReservation> BookReservation { get; set; } = default!;
        public DbSet<BookingList> BookingList { get; set; } = default!;

    }
}
