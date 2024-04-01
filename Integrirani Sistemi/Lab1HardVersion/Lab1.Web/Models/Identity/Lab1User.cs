using Lab1.Web.Models.Domain;
using Microsoft.AspNetCore.Identity;
using System.ComponentModel.DataAnnotations;

namespace Lab1.Web.Models.Identity {
    public class Lab1User : IdentityUser {
        public string? FirstName { get; set; }
        public string? LastName { get; set; }
        public string? HomeAddress { get; set; }
        public virtual ICollection<Ticket> Tickets { get; set; }

    }
}
