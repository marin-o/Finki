using Microsoft.AspNetCore.Identity;

namespace Lab1Guided.Models.Identity
{
    public class ApplicationUser : IdentityUser
    {
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string UserAddress { get; set; }

    }
}
