using Lab1.Web.Models.Domain;
using Microsoft.AspNetCore.Identity;

namespace Lab1.Web.Models.Identity
{
    public class TheaterUser : IdentityUser
    {
        public string? FirstName { get; set; }
        public string? LastName { get; set; }
        public string? Address { get; set; }

        public virtual ICollection<Ticket>? Tickets { get; set; }

    }
}
