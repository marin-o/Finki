using System.ComponentModel.DataAnnotations;
using BA.Domain.Identity;

namespace BA.Domain.Models
{
    public class BookingList : BaseEntity
    {
        
        [Required]
        public ICollection<BookReservation>? BookReservations { get; set; }
        [Required]
        public int Full_Price { get; set; }
        public BookingApplicationUser? User { get; set; }
        public string? UserId { get; set; }
    }
}
