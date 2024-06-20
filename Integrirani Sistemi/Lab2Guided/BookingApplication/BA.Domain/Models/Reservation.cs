using System.ComponentModel.DataAnnotations;
using BA.Domain.Identity;

namespace BA.Domain.Models
{
    public class Reservation : BaseEntity
    {
        
        [Required]
        public DateTime Check_in_date { get; set; }
        public Guid ApartmentId { get; set; }
        public Apartment? Apartment { get; set; }
        public virtual BookingApplicationUser? User { get; set; }
        public string? UserId { get; set; }
    }
}
